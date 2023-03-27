package org.antrain.app;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.antrain.app.util.JwtUtil;
import org.antrain.app.vo.CommentVo;
import org.antrain.app.wordfilter.FilterStopWord;
import org.antrain.app.wordfilter.WordFilter;
import org.antrain.common.core.domain.AjaxResult;
import org.antrain.community.domain.InvitationComment;
import org.antrain.community.service.IInvitationCommentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/app/post/comment")
public class AppPostCommentController {
    @Resource
    private IInvitationCommentService commentService;

    @Resource
    private WordFilter wordFilter;

    @Resource
    private FilterStopWord filterStopWord;

    @Value("${stopWordFilter}")
    private Boolean stopWordFilter;

    @Value("${skip}")
    private Integer skip;


    @GetMapping("/list")
    public AjaxResult getPostList(CommentVo vo) {
        vo.check();
        QueryWrapper<InvitationComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id","content","create_time","nickname");
        queryWrapper.eq("status","Y").eq("invitation_id",vo.getId());
        IPage<InvitationComment>  page = new Page<>(vo.getPage(),vo.getLimit());
        return AjaxResult.success(commentService.page(page,queryWrapper));
    }

    @GetMapping("/my/list")
    public AjaxResult getMyList(CommentVo vo, HttpServletRequest request) {
        vo.check();
        QueryWrapper<InvitationComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id","invitation_title","content","create_time","invitation_id");
        queryWrapper.eq("status","Y").eq("user_id",JwtUtil.getId(request));
        IPage<InvitationComment>  page = new Page<>(vo.getPage(),vo.getLimit());
        return AjaxResult.success(commentService.page(page,queryWrapper));
    }

    @PostMapping
    public AjaxResult add(@RequestBody InvitationComment comment, HttpServletRequest request) {
        String content = comment.getContent();
        if (stopWordFilter) {
            content = filterStopWord.RemoveStopWords(content);
        }
        if ( wordFilter.include(content,skip) ) {
            return AjaxResult.error("请遵守社区条例，文明发言");
        }
        comment.setUserId(JwtUtil.getId(request));
        comment.setNickname(JwtUtil.getUsername(request));
        commentService.save(comment);
        return AjaxResult.success();
    }

    @DeleteMapping("/{id}")
    public AjaxResult deleteById(@PathVariable Long id) {
        commentService.removeById(id);
        return AjaxResult.success();
    }
}
