package org.antrain.app;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.antrain.app.util.JwtUtil;
import org.antrain.app.vo.ApplyVo;
import org.antrain.app.vo.FriendVo;
import org.antrain.common.core.domain.AjaxResult;
import org.antrain.user.domain.Apply;
import org.antrain.user.domain.User;
import org.antrain.user.service.IApplyService;
import org.antrain.user.service.IUserService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/app/apply")
public class AppApplyController {

    @Resource
    private IApplyService applyService;

    @Resource
    private IUserService userService;

    @GetMapping("/my/friend")
    public AjaxResult getMyFriend(HttpServletRequest request) {
        QueryWrapper<Apply> applyQueryWrapper = new QueryWrapper<>();
        Long userId = JwtUtil.getId(request);
        applyQueryWrapper.select("send_id","accept_id");
        applyQueryWrapper.eq("status","Y").and(i->i.eq("send_id",userId)
            .or().eq("accept_id",userId));
        List<Apply> applyList = applyService.list(applyQueryWrapper);
        if (applyList==null || applyList.size() == 0 ) {
            return AjaxResult.error("尚无好友");
        }
        Set<Long> ids = new HashSet<>();
        for (Apply apply:applyList) {
            if (apply.getSendId() == userId) {
                ids.add(apply.getAcceptId());
            } else {
                ids.add(apply.getSendId());
            }
        }
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.select("id","avatar","nickname");
        userQueryWrapper.eq("status","Y").in("id",ids);
        List<FriendVo> list = new ArrayList<>();
        List<User> list1 = userService.list(userQueryWrapper);
        for ( User user : list1) {
            FriendVo friendVo = new FriendVo();
            friendVo.setId(user.getId());
            friendVo.setImg(user.getAvatar());
            friendVo.setName(user.getNickname());
            list.add(friendVo);
        }
        return AjaxResult.success(list);
    }

    @GetMapping("/my/list")
    public AjaxResult getApplyList(HttpServletRequest request) {
        QueryWrapper<Apply> applyQueryWrapper = new QueryWrapper<>();
        Long userId = JwtUtil.getId(request);
        applyQueryWrapper.select("send_id","send_name","accept_id","accept_name","id","content","create_time");
        applyQueryWrapper.eq("status","N").and(i->i.eq("send_id",userId)
                .or().eq("accept_id",userId));
        return AjaxResult.success(applyService.list(applyQueryWrapper));
    }

    /**
     * 申请好友
     * @return
     */
    @PostMapping
    public AjaxResult applyFriend(@RequestBody ApplyVo applyVo, HttpServletRequest request) {
        if (applyVo.getId() == JwtUtil.getId(request)) {
            return AjaxResult.error("不能向自己发送申请");
        }
        QueryWrapper<Apply> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("send_id", JwtUtil.getId(request))
                .eq("accept_id",applyVo.getId());
        Apply apply = applyService.getOne(queryWrapper,false);
        if (apply != null) {
            if (apply.getStatus().equals("N")) {
                return AjaxResult.error("申请信息已发送，对方还未确定");
            }else if (apply.getStatus().equals("Y")) {
                return AjaxResult.error("你们已经是好友了");
            }else if (apply.getStatus().equals("R")) {
                return AjaxResult.error("对方已拒绝你的申请");
            }
        }
        apply = new Apply();
        apply.setAcceptId(applyVo.getId());
        apply.setAcceptName(applyVo.getAcceptName());
        apply.setContent(applyVo.getContent());
        apply.setSendId(JwtUtil.getId(request));
        apply.setSendName(JwtUtil.getUsername(request));
        applyService.save(apply);
        return AjaxResult.success("已发送申请信息，等待对方确认");
    }

    @PutMapping("/accept/{id}")
    public AjaxResult accept(@PathVariable Long id,HttpServletRequest request) {
        applyService.accept(id,JwtUtil.getId(request));
        return  AjaxResult.success();
    }

    @PutMapping("/reject/{id}")
    public AjaxResult reject(@PathVariable Long id,HttpServletRequest request) {
        applyService.reject(id,JwtUtil.getId(request));
        return  AjaxResult.success();
    }

    @DeleteMapping("/{id}")
    public AjaxResult deleteById(@PathVariable Long id,HttpServletRequest request) {
        applyService.deleteById(id,JwtUtil.getId(request));
        return  AjaxResult.success();
    }
}
