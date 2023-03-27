package org.antrain.app;


import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.antrain.app.util.JwtUtil;
import org.antrain.app.vo.CommentVo;
import org.antrain.app.wordfilter.FilterStopWord;
import org.antrain.app.wordfilter.WordFilter;
import org.antrain.article.domain.Article;
import org.antrain.article.domain.ArticleComment;
import org.antrain.article.service.IArticleCommentService;
import org.antrain.article.service.IArticleService;
import org.antrain.article.service.ILableService;
import org.antrain.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/app/article")
public class AppArticleController {
    @Resource
    private IArticleService articleService;

    @Resource
    private ILableService labelService;

    @Resource
    private IArticleCommentService articleCommentService;

    @Resource
    private WordFilter wordFilter;

    @Resource
    private FilterStopWord filterStopWord;

    @Value("${stopWordFilter}")
    private Boolean stopWordFilter;

    @Value("${skip}")
    private Integer skip;



    /**
     * 获取加精文章, 并填充文章标签
     */
    @GetMapping("/refine")
    private AjaxResult getRefine() {
        JSONObject all = labelService.getAll();
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id,title,labels,nickname,create_time");
        queryWrapper.eq("status","Y").eq("refine","Y");
        queryWrapper.orderByDesc("id");
        queryWrapper.last("limit 5");
        List<Article> list = articleService.list(queryWrapper);
        return  AjaxResult.success(Article.setListOfLabelName(list,all));
    }

    /**
     * 获取有效的文章列表
     */
    @GetMapping("/list")
    public AjaxResult getList(@RequestParam(required = false, defaultValue = "1") Integer page,
                              @RequestParam(required = false, defaultValue = "5") Integer limit){
        JSONObject all = labelService.getAll();
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id,title,labels,nickname,create_time");
        queryWrapper.eq("status","Y").orderByDesc("id");
        IPage<Article> articleIPage = new Page<>(page,limit);
        IPage<Article> page1 = articleService.page(articleIPage, queryWrapper);
        Article.setListOfLabelName(page1.getRecords(),all);
        return AjaxResult.success(page1);
    }

    /**
     * 查看文章详情
     */
    @GetMapping("/info/{id}")
    public AjaxResult getArticle(@PathVariable Integer id){
        return AjaxResult.success(articleService.getById(id));
    }

    /**
     * 获取文章所有的评论
     */
    @GetMapping("/comment/list")
    public AjaxResult getArticleCommentList(CommentVo vo) {
        vo.check();
        QueryWrapper<ArticleComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id","content","create_time","nickname");
        queryWrapper.eq("status","Y").eq("article_id",vo.getId());
        IPage<ArticleComment> page = new Page<>(vo.getPage(),vo.getLimit());
        return AjaxResult.success(articleCommentService.page(page,queryWrapper));
    }

    /**
     * 添加
     */
    @PostMapping("/comment")
    public AjaxResult addArticleCommentList(@RequestBody ArticleComment comment, HttpServletRequest request) {
        String content = comment.getContent();
        if (StrUtil.isBlank(content)) {
            return AjaxResult.error("请输入评论内容");
        }
        if (stopWordFilter) {
            content = filterStopWord.RemoveStopWords(content);
        }
        if ( wordFilter.include(content,skip) ) {
            return AjaxResult.error("请遵守社区条例，文明发言");
        }
        comment.setUserId(JwtUtil.getId(request));
        comment.setNickname(JwtUtil.getUsername(request));
        articleCommentService.save(comment);
        return AjaxResult.success();
    }
}
