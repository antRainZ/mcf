package org.antrain.app;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.antrain.app.util.JwtUtil;
import org.antrain.common.core.domain.AjaxResult;
import org.antrain.user.domain.Integral;
import org.antrain.user.domain.User;
import org.antrain.user.service.IIntegralService;
import org.antrain.user.service.IUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/app/integral")
public class AppIntegralController {
    @Resource
    private IIntegralService integralService;

    @Resource
    private IUserService userService;

    @GetMapping("/check")
    public AjaxResult checkSignIn(HttpServletRequest request) {
        Long id = integralService.checkSignIn(JwtUtil.getId(request));
        if (id != null) {
            return AjaxResult.error("你已今天完成签到");
        }
        return AjaxResult.success();
    }

    @GetMapping("/my/list")
    public AjaxResult getMyList(@RequestParam(required = false,defaultValue = "1") Integer page,
                                @RequestParam(required = false,defaultValue = "5") Integer limit,
                                HttpServletRequest request)
    {
        QueryWrapper<Integral> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("add_value","per_value","remark","create_time");
        queryWrapper.eq("user_id",JwtUtil.getId(request)).eq("status","Y");
        IPage<Integral> integralIPage = new Page<>(page,limit);
        return AjaxResult.success(integralService.page(integralIPage,queryWrapper));
    }

    @PostMapping
    public AjaxResult signIn(HttpServletRequest request) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.select("integral","id","username");
        userQueryWrapper.eq("id",JwtUtil.getId(request));
        User user = userService.getOne(userQueryWrapper);
        Integral integral = new Integral();
        integral.setAddValue(1L);
        integral.setNickname(user.getUsername());
        integral.setUserId(user.getId());
        integral.setPerValue(user.getIntegral());
        integral.setRemark("signIn");
        integralService.save(integral);
        user.setIntegral(user.getIntegral()+1);
        userService.updateById(user);
        return AjaxResult.success();
    }
}