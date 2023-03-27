package org.antrain.app;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.antrain.app.util.JwtUtil;
import org.antrain.app.vo.TopicPageVo;
import org.antrain.common.core.domain.AjaxResult;
import org.antrain.community.domain.Topic;
import org.antrain.community.service.ITopicService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/app/topic")
public class AppTopicController {

    @Resource
    private ITopicService topicService;

    /**
     * 显示在首页的加精帖子
     * @return
     */
    @GetMapping("/refine")
    public AjaxResult getIndexRefine() {
        QueryWrapper<Topic> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id","title");
        queryWrapper.eq("status", "Y" ).eq("refine","Y");
        queryWrapper.last("limit 3");
        return AjaxResult.success(topicService.list(queryWrapper));
    }

    @GetMapping("/list")
    public AjaxResult getList(TopicPageVo topicPageVo) {
        topicPageVo.check();
        QueryWrapper<Topic> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id","title","cover","create_time");
        queryWrapper.eq("status", "Y" );
        if (StrUtil.isNotBlank(topicPageVo.getTitle())) {
            queryWrapper.like("title",topicPageVo.getTitle());
        }
        IPage<Topic> page = new Page<>(topicPageVo.getPage(),6);
        return  AjaxResult.success(topicService.page(page,queryWrapper));
    }

    @GetMapping("/my")
    public AjaxResult<Topic> getUserTopic(HttpServletRequest request) {
        QueryWrapper<Topic> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id","title","cover","description","file_url","reason","remark","status");
        queryWrapper.eq("user_id", JwtUtil.getId(request));
        return  AjaxResult.success(topicService.getOne(queryWrapper,false));
    }

    @GetMapping("/info/{id}")
    public AjaxResult getInfo(@PathVariable Long id) {
        QueryWrapper<Topic> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("title","cover","description");
        queryWrapper.eq("id", id);
        return AjaxResult.success(topicService.getOne(queryWrapper,false));
    }

    @PostMapping
    public AjaxResult save(@RequestBody Topic topic, HttpServletRequest request){
        topic.setUserId(JwtUtil.getId(request));
        topic.setNickname(JwtUtil.getUsername(request));
        topic.setStatus("A");
        topicService.save(topic);
        return AjaxResult.success();
    }
}
