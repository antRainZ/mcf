package org.antrain.app;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import org.antrain.app.util.JwtUtil;
import org.antrain.app.vo.LoginVo;
import org.antrain.app.vo.RegisterVO;
import org.antrain.common.core.domain.AjaxResult;
import org.antrain.user.domain.User;
import org.antrain.user.service.IUserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.DigestUtil;

@RestController
@Slf4j
@RequestMapping("/app")
public class AppCommonController {

    /**
     创建线程池对象
     */
    ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
    @Resource
    private JavaMailSenderImpl mailSender;

    @Resource
    private IUserService userService;

    @Value("${spring.mail.username}")
    private String sendEmail;

    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginVo loginVo) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("password", DigestUtil.sha256Hex(loginVo.getPassword()))
            .eq("username",loginVo.getUsername());
        User user =  userService.getOne(wrapper,false);
        JSONObject jsonObject = new JSONObject();
        if (user == null){
            return AjaxResult.error("用户名或者密码错误");
        }
        if (user.getStatus().equals("N")) {
            return AjaxResult.error("请联系管理员激活");
        }
        jsonObject.put("token", JwtUtil.create(user));
        jsonObject.put("avatar",user.getAvatar());
        jsonObject.put("nickname",user.getNickname());
        jsonObject.put("username",user.getUsername());
        return AjaxResult.success(jsonObject);
    }

    @SuppressWarnings("all")
    @GetMapping("/code")
    public AjaxResult sendEmail(String email, HttpServletRequest request) {
        HttpSession session = request.getSession();
        //验证码
        String verCode = RandomUtil.randomString(6);
        //发送时间
        String time = DateUtil.formatDateTime(new Date());
        Map<String, String> map = new HashMap<>(16);
        map.put("code", verCode);
        map.put("email", email);
        session.setAttribute("verCode", map);
        Map<String, String> codeMap = (Map<String, String>) session.getAttribute("verCode");
        //设置验证码有效时间，并放入session
        try {
            scheduledExecutorService.schedule(new Thread(() -> {
                if (email.equals(codeMap.get("email"))) {
                    // 时间到了将 验证码从 session 移除
                    session.removeAttribute("verCode");
                }
            }), 5 * 60, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String title = "【课后邂逅校园交友平台】 邮箱验证码";
        MimeMessage mimeMessage = null;
        MimeMessageHelper helper = null;
        try {
            //发送复杂的邮件
            mimeMessage = mailSender.createMimeMessage();
            //组装
            helper = new MimeMessageHelper(mimeMessage, true);
            //正文
            helper.setSubject(title);
            helper.setText("<h3>\n" +
                    "\t<span style=\"font-size:16px;\">亲爱的用户：</span> \n" +
                    "</h3>\n" +
                    "<p>\n" +
                    "\t<span style=\"font-size:14px;\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=\"font-size:14px;\">&nbsp; <span style=\"font-size:16px;\">&nbsp;&nbsp;您好！您正在进行邮箱验证，本次请求的验证码为：<span style=\"font-size:24px;color:#FFE500;\"> " + verCode + "</span>,本验证码5分钟内有效，请在5分钟内完成验证。（请勿泄露此验证码）如非本人操作，请忽略该邮件。(这是一封自动发送的邮件，请不要直接回复）</span></span>\n" +
                    "</p>\n" +
                    "<p style=\"text-align:right;\">\n" +
                    "\t<span style=\"background-color:#FFFFFF;font-size:16px;color:#000000;\"><span style=\"color:#000000;font-size:16px;background-color:#FFFFFF;\"><span class=\"token string\" style=\"font-family:&quot;font-size:16px;color:#000000;line-height:normal !important;background-color:#FFFFFF;\">课后邂逅校园交友平台</span></span></span> \n" +
                    "</p>\n" +
                    "<p style=\"text-align:right;\">\n" +
                    "\t<span style=\"background-color:#FFFFFF;font-size:14px;\"><span style=\"color:#FF9900;font-size:18px;\"><span class=\"token string\" style=\"font-family:&quot;font-size:16px;color:#000000;line-height:normal !important;\"><span style=\"font-size:16px;color:#000000;background-color:#FFFFFF;\">" + time + "</span><span style=\"font-size:18px;color:#000000;background-color:#FFFFFF;\"></span></span></span></span> \n" +
                    "</p>", true);
            //收件人
            helper.setTo(email);
            //发送方
            helper.setFrom(sendEmail);
            try {
                mailSender.send(mimeMessage);
            } catch (MailException e) {
                e.printStackTrace();
                return AjaxResult.error("请输入有效的邮箱地址");
            }
        } catch (MessagingException e) {
            //发送失败--服务器繁忙
            e.printStackTrace();
            return AjaxResult.error("服务器繁忙，请稍后重试");
        }
        // System.out.println(verCode);
        return AjaxResult.success();
    }

    @PostMapping("/register")
    @SuppressWarnings("all")
    public AjaxResult register(@RequestBody RegisterVO registerVO, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Map<String, String> codeMap = (Map<String, String>) session.getAttribute("verCode");
        System.out.println(codeMap);
        String code = null;
        String email = null;
        try {
            code = codeMap.get("code");
            email = codeMap.get("email");
        } catch (Exception e) {
            //验证码过期，或未找到  ---验证码无效
            e.printStackTrace();
            return AjaxResult.error("验证码已过期，请重新发送");
        }
        //验证码判断
        if (!registerVO.getCode().toUpperCase().equals(code.toUpperCase())) {
            return  AjaxResult.error("验证码错误");
        }
        //验证码使用完后session删除
        session.removeAttribute("verCode");
        //用户名是否可用
        User user = new User();
        user.setEmail(registerVO.getEmail());
        user.setUsername(registerVO.getUsername());
        user.setPassword( DigestUtil.sha256Hex( registerVO.getPassword()));
        user.setAvatar("");
        user.setName(registerVO.getUsername());
        user.setNickname(registerVO.getUsername());
        userService.save(user);
        return AjaxResult.success("注册成功");
    }

    @PostMapping("/retrieve")
    @SuppressWarnings("all")
    public AjaxResult retrieve(@RequestBody RegisterVO registerVO, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Map<String, String> codeMap = (Map<String, String>) session.getAttribute("verCode");
        System.out.println(codeMap);
        String code = null;
        String email = null;
        try {
            code = codeMap.get("code");
            email = codeMap.get("email");
        } catch (Exception e) {
            //验证码过期，或未找到  ---验证码无效
            e.printStackTrace();
            return AjaxResult.error("验证码已过期，请重新发送");
        }
        //验证码判断
        if (!registerVO.getCode().toUpperCase().equals(code.toUpperCase())) {
            return  AjaxResult.error("验证码错误");
        }
        //验证码使用完后session删除
        session.removeAttribute("verCode");
        //用户名是否可用
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.set("password", DigestUtil.sha256Hex( registerVO.getPassword()));
        userUpdateWrapper.eq("email",registerVO.getEmail());
        userService.update(userUpdateWrapper);
        return AjaxResult.success("修改密码成功");
    }
}
