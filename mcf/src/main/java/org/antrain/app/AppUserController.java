package org.antrain.app;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.antrain.app.util.JwtUtil;
import org.antrain.app.util.Similarity;
import org.antrain.app.vo.PasswordVo;
import org.antrain.app.vo.UserPageVo;
import org.antrain.app.vo.UserRecommend;
import org.antrain.app.wordfilter.FilterStopWord;
import org.antrain.common.core.domain.AjaxResult;
import org.antrain.user.domain.User;
import org.antrain.user.service.IUserService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/app/user")
public class AppUserController {
    @Resource
    private IUserService userService;

    @Resource
    private FilterStopWord filterStopWord;

    @GetMapping("/list")
    public AjaxResult getList(UserPageVo userPageVo) {
        userPageVo.check();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id","nickname","avatar","update_time","username","interest");
        queryWrapper.eq("status","Y");
        if (StrUtil.isNotBlank(userPageVo.getName())) {
            queryWrapper.like("nickname",userPageVo.getName());
        }
        IPage<User> page = new Page<>(userPageVo.getPage(),6);
        return AjaxResult.success(userService.page(page,queryWrapper)) ;
    }

    /**
     * Java实现余弦定理计算文本相似度 https://blog.csdn.net/chengwangbaiko/article/details/78742237
     * 计算两个字符串之间的文本相似度 https://zhuanlan.zhihu.com/p/91645988
     * NLP——计算文本相似度的常用算法 https://blog.csdn.net/weixin_42572826/article/details/107107659
     * @param request
     * @return
     */
    @GetMapping("/smart/list")
    public AjaxResult getList(UserPageVo userPageVo, HttpServletRequest request) {
        userPageVo.check();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id","nickname","avatar","update_time","username","interest");
        queryWrapper.eq("status","Y").ne("id",JwtUtil.getId(request));
        if (StrUtil.isNotBlank(userPageVo.getName())) {
            queryWrapper.like("nickname",userPageVo.getName());
        }
        IPage<User> page = new Page<>(userPageVo.getPage(),6);
        IPage<User> page1 = userService.page(page, queryWrapper);
        User my = userService.getById(JwtUtil.getId(request));
        if (StrUtil.isBlank(my.getInterest())) {
            return AjaxResult.success("填写兴趣爱好信息，更好找到自己的志同道合的朋友",page1);
        }
        List<String> myInterestList = filterStopWord.getWordList(my.getInterest());
        List<User> userList = page1.getRecords();
        List<UserRecommend> userRecommendList = new ArrayList<>();
        for (User user : userList) {
            UserRecommend userRecommend = new UserRecommend();
            userRecommend.setUserRecommend(user);
            if (StrUtil.isNotBlank(user.getInterest())) {
                userRecommend.setInterest(user.getInterest());
                userRecommend.setScore(Similarity.
                        getSimilarity(myInterestList,filterStopWord.getWordList(user.getInterest())));
            } else {
                userRecommend.setInterest("用户尚未填写");
                userRecommend.setScore("0.0");
            }
            userRecommendList.add(userRecommend);
        }
        Collections.sort(userRecommendList);
        IPage<UserRecommend> result = new Page<>();
        result.setTotal(page1.getTotal());
        result.setRecords(userRecommendList);
        return AjaxResult.success(result) ;
    }

    @GetMapping("/info")
    public AjaxResult<User> getInfo(HttpServletRequest request) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("username","nickname","name","sex","telephone","email","school",
                "dept","major","address","contact","introduce","integral","interest");
        queryWrapper.eq("id",JwtUtil.getId(request));
        return AjaxResult.success(userService.getOne(queryWrapper)) ;
    }

    @GetMapping("/check/username")
    public AjaxResult checkName(@RequestParam String value){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("id");
        wrapper.eq("username", value);
        if (userService.getOne(wrapper,false) != null){
            return AjaxResult.error("改用户名已存在");
        }
        return AjaxResult.success();
    }

    @GetMapping("/check/email")
    public AjaxResult checkEmail(@RequestParam String value){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("id");
        wrapper.eq("email", value);
        if (userService.getOne(wrapper,false) != null){
            return AjaxResult.error("该邮箱已被注册");
        }
        return AjaxResult.success();
    }

    @PutMapping("/updateInfo")
    public AjaxResult update(@RequestBody User user, HttpServletRequest request) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("name",user.getName()).set("nickname",user.getNickname())
                .set("sex",user.getSex()).set("telephone",user.getTelephone())
                .set("school",user.getSchool()).set("dept",user.getDept())
                .set("major",user.getMajor()).set("address",user.getAddress())
                .set("contact",user.getContact()).set("introduce",user.getIntroduce());
        updateWrapper.eq("id",JwtUtil.getId(request));
        userService.update(updateWrapper);
        return AjaxResult.success();
    }

    @PutMapping("/avatar")
    public AjaxResult updateAvatar(@RequestBody User user, HttpServletRequest request) {
        user.setId(JwtUtil.getId(request));
        userService.updateById(user);
        return AjaxResult.success();
    }

    @PostMapping("/updatePwd")
    public AjaxResult updatePwd(@RequestBody PasswordVo passwordVo, HttpServletRequest request) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id");
        queryWrapper.eq("password", DigestUtil.sha256Hex(passwordVo.getOldPwd()))
                    .eq("id",JwtUtil.getId(request) );
        User user = userService.getOne(queryWrapper,false);
        if ( user == null) {
            return AjaxResult.error("原密码错误");
        }
        user.setPassword(DigestUtil.sha256Hex(passwordVo.getNewPwd()));
        userService.updateById(user);
        return AjaxResult.success("修改密码成功");
    }

    @GetMapping("/participle")
    public AjaxResult participle() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id","nickname","avatar","update_time","username","interest");
        List<User> list = userService.list(queryWrapper);
        List<UserRecommend> userRecommendList = new ArrayList<>();
        for (User user : list) {
            UserRecommend userRecommend = new UserRecommend();
            userRecommend.setUserRecommend(user);
            if (StrUtil.isNotBlank(user.getInterest())) {
                userRecommend.setInterest(user.getInterest());
            }
        }
        return AjaxResult.success();
    }

}
