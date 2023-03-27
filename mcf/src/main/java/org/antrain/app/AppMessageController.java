package org.antrain.app;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.antrain.app.util.JwtUtil;
import org.antrain.app.vo.MessagePageVo;
import org.antrain.app.wordfilter.FilterStopWord;
import org.antrain.app.wordfilter.WordFilter;
import org.antrain.common.core.domain.AjaxResult;
import org.antrain.user.domain.Message;
import org.antrain.user.service.IMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/app/message")
public  class AppMessageController {

    @Resource
    private IMessageService messageService;

    @Resource
    private WordFilter wordFilter;

    @Resource
    private FilterStopWord filterStopWord;

    @Value("${stopWordFilter}")
    private Boolean stopWordFilter;

    @Value("${skip}")
    private Integer skip;

    /**
     * 这里为了简化聊天的一些情况，第一次用户访问时聊天的消息置为已读
     * @param id
     * @param request
     * @return
     */
    @GetMapping("/my/reset/{id}")
    public AjaxResult reset(@PathVariable Integer id,HttpServletRequest request) {
        UpdateWrapper<Message> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("type","R");
        updateWrapper.eq("send_id",id).eq("accept_id",JwtUtil.getId(request))
                .eq("type","C");
        messageService.update(updateWrapper);
        return AjaxResult.success();
    }

    @GetMapping("/my/list")
    public AjaxResult getMyList(MessagePageVo pageVo, HttpServletRequest request) {
        pageVo.check();
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("send_id,content,create_time,type,id");
        Long userId = JwtUtil.getId(request);
        queryWrapper.and(i->i.eq("send_id",userId).eq("accept_id",pageVo.getId()))
                .or(i->i.eq("send_id",pageVo.getId()).eq("accept_id",userId))
                .orderByDesc("id");
        IPage<Message> page = new Page<>(pageVo.getPage(),pageVo.getLimit());
        return AjaxResult.success(messageService.page(page, queryWrapper));
    }

    @GetMapping("/my/latest/{id}")
    public AjaxResult getLatest(@PathVariable Long id,HttpServletRequest request) {
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("content,create_time","id","type");
        queryWrapper.eq("send_id",id).eq("accept_id",JwtUtil.getId(request))
                .eq("type","C");
        List<Message> list = messageService.list(queryWrapper);
        for (Message message: list) {
            message.setType("R");
        }
        messageService.updateBatchById(list);
        return AjaxResult.success(list);
    }

    @PostMapping
    public AjaxResult add(@RequestBody Message message, HttpServletRequest request){
        message.setSendId(JwtUtil.getId(request));
        message.setSendName(JwtUtil.getUsername(request));
        String content = message.getContent();
        if (stopWordFilter) {
            content = filterStopWord.RemoveStopWords(content);
        }
        if ( wordFilter.include(content,skip) ) {
            return AjaxResult.error("请遵守社区条例，文明交流");
        }
        messageService.save(message);
        return AjaxResult.success();
    }

}