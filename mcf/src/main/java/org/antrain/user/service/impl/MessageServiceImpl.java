package org.antrain.user.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.Page;
import org.antrain.user.bo.MessageAddBo;
import org.antrain.user.bo.MessageQueryBo;
import org.antrain.user.bo.MessageEditBo;
import org.antrain.user.domain.Message;
import org.antrain.user.mapper.MessageMapper;
import org.antrain.user.vo.MessageVo;
import org.antrain.user.service.IMessageService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 消息Service业务层处理
 *
 * @author antrain
 * @date 2021-04-11
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements IMessageService {

    @Override
    public MessageVo queryById(Long id){
        Message db = this.baseMapper.selectById(id);
        return BeanUtil.toBean(db, MessageVo.class);
    }

    @Override
    public List<MessageVo> queryList(MessageQueryBo bo) {
        LambdaQueryWrapper<Message> lqw = Wrappers.lambdaQuery();
        lqw.like(StrUtil.isNotBlank(bo.getSendName()), Message::getSendName, bo.getSendName());
        lqw.like(StrUtil.isNotBlank(bo.getAcceptName()), Message::getAcceptName, bo.getAcceptName());
        lqw.like(StrUtil.isNotBlank(bo.getContent()), Message::getContent, bo.getContent());
        return entity2Vo(this.list(lqw));
    }

    /**
    * 实体类转化成视图对象
    *
    * @param collection 实体类集合
    * @return
    */
    private List<MessageVo> entity2Vo(Collection<Message> collection) {
        List<MessageVo> voList = collection.stream()
                .map(any -> BeanUtil.toBean(any, MessageVo.class))
                .collect(Collectors.toList());
        if (collection instanceof Page) {
            Page<Message> page = (Page<Message>)collection;
            Page<MessageVo> pageVo = new Page<>();
            BeanUtil.copyProperties(page,pageVo);
            pageVo.addAll(voList);
            voList = pageVo;
        }
        return voList;
    }

    @Override
    public Boolean insertByAddBo(MessageAddBo bo) {
        Message add = BeanUtil.toBean(bo, Message.class);
        validEntityBeforeSave(add);
        return this.save(add);
    }

    @Override
    public Boolean updateByEditBo(MessageEditBo bo) {
        Message update = BeanUtil.toBean(bo, Message.class);
        validEntityBeforeSave(update);
        return this.updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(Message entity){
        //TODO 做一些数据校验,如唯一约束
    }

    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return this.removeByIds(ids);
    }
}
