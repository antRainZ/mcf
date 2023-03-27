package org.antrain.app;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.antrain.app.util.JwtUtil;
import org.antrain.app.vo.PostPageVo;
import org.antrain.app.wordfilter.FilterStopWord;
import org.antrain.app.wordfilter.WordFilter;
import org.antrain.common.core.domain.AjaxResult;
import org.antrain.community.domain.Invitation;
import org.antrain.community.service.IInvitationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/app/invitation")
public class AppInvitationController {

    @Resource
    private IInvitationService invitationService;

    @Resource
    private WordFilter wordFilter;

    @Resource
    private FilterStopWord filterStopWord;

    @Value("${stopWordFilter}")
    private Boolean stopWordFilter;

    @Value("${skip}")
    private Integer skip;

    /**
     * 在首页显示精贴
     * @return
     */
    @Deprecated
    @GetMapping("/index/refine")
    public AjaxResult getRefine() {
        QueryWrapper<Invitation> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id,title,topic_title,nickname,create_time");
        queryWrapper.eq("refine","Y").eq("status","Y");
        queryWrapper.orderByDesc("id");
        queryWrapper.last("limit 3");
        return  AjaxResult.success(invitationService.list(queryWrapper));
    }

    /**
     * 在首页显示刚发布的帖子
     * @return
     */
    @Deprecated
    @GetMapping("/index/latest")
    public AjaxResult getNew() {
        QueryWrapper<Invitation> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id,title,topic_title,nickname,create_time");
        queryWrapper.eq("status","Y");
        queryWrapper.orderByDesc("id");
        queryWrapper.last("limit 5");
        return  AjaxResult.success(invitationService.list(queryWrapper));
    }

    /**
     * 在社区显示精贴
     * @param postPageVo
     * @return
     */
    @GetMapping("/topic/refine")
    public AjaxResult getTopicRefine(PostPageVo postPageVo) {
        QueryWrapper<Invitation> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id,title,topic_title,nickname,create_time");
        queryWrapper.eq("refine","Y").eq("status","Y")
                .eq("topic_id",postPageVo.getId()).orderByDesc("id");
        IPage<Invitation> page = new Page<>(postPageVo.getPage(),postPageVo.getLimit());
        return  AjaxResult.success(invitationService.page(page,queryWrapper));
    }

    /**
     * 在前台社区里显示最新的帖子
     * @param postPageVo
     * @return
     */
    @GetMapping("/topic/latest")
    public AjaxResult getTopicNew(PostPageVo postPageVo) {
        QueryWrapper<Invitation> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id,title,topic_title,nickname,create_time");
        queryWrapper.eq("status","Y").eq("topic_id",postPageVo.getId());
        queryWrapper.orderByDesc("id");
        IPage<Invitation> page = new Page<>(postPageVo.getPage(),postPageVo.getLimit());
        return  AjaxResult.success(invitationService.page(page,queryWrapper));
    }

    /**
     * 获取某条帖子详细信息
     * @param id
     * @return
     */
    @GetMapping("/info/{id}")
    public AjaxResult getById(@PathVariable Long id) {
        return  AjaxResult.success(invitationService.getById(id));
    }

    /**
     * 在社区管理者管理帖子内容
     * @param postPageVo
     * @return
     */
    @GetMapping("/topic/list")
    public AjaxResult getTopicList(PostPageVo postPageVo) {
        postPageVo.check();
        QueryWrapper<Invitation> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id,title,topic_title,nickname,create_time,status,refine");
        queryWrapper.eq("topic_id",postPageVo.getId());
        if (StrUtil.isNotBlank(postPageVo.getTitle())) {
            queryWrapper.like("title",postPageVo.getTitle());
        }
        IPage<Invitation> page = new Page<>(postPageVo.getPage(),postPageVo.getLimit());
        return  AjaxResult.success(invitationService.page(page,queryWrapper));
    }

    /**
     * 获取自己发布的帖子
     * @param postPageVo
     * @param request
     * @return
     */
    @GetMapping("/my/list")
    public AjaxResult getMyList(PostPageVo postPageVo, HttpServletRequest request) {
        postPageVo.check();
        QueryWrapper<Invitation> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id,title,topic_title,nickname,create_time,status");
        queryWrapper.eq("user_id", JwtUtil.getId(request));
        if (StrUtil.isNotBlank(postPageVo.getTitle())) {
            queryWrapper.like("title",postPageVo.getTitle());
        }
        IPage<Invitation> page = new Page<>(postPageVo.getPage(),postPageVo.getLimit());
        return  AjaxResult.success(invitationService.page(page,queryWrapper));
    }

    /**
     * 添加一条帖子内容，并对帖子内容和标题进行敏感词检测
     * @param invitation
     * @param request
     * @return
     */
    @PostMapping
    public AjaxResult add(@RequestBody Invitation invitation,HttpServletRequest request) {
        String title = invitation.getTitle();
        String content = invitation.getContent();
        if (stopWordFilter) {
            content = filterStopWord.RemoveStopWords(content);
            title = filterStopWord.RemoveStopWords(title);
        }
        if ( wordFilter.include(title,skip) ||  wordFilter.include(content,skip) ) {
            return AjaxResult.error("请遵守社区条例，文明发言");
        }
        invitation.setUserId(JwtUtil.getId(request));
        invitation.setNickname(JwtUtil.getUsername(request));
        // 对于社区管理员的发布的帖子默认通过
        if (StrUtil.isBlank(invitation.getStatus())) {
            invitation.setStatus("N");
        }
        invitationService.save(invitation);
        return AjaxResult.success();
    }

    /**
     * 修改帖子信息
     * @param invitation
     * @return
     */
    @PutMapping
    public AjaxResult update(@RequestBody Invitation invitation) {
        invitationService.updateById(invitation);
        return AjaxResult.success();
    }

    /**
     * 删除一条帖子
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public AjaxResult delete(@PathVariable Long id) {
        invitationService.removeById(id);
        return AjaxResult.success();
    }
}
