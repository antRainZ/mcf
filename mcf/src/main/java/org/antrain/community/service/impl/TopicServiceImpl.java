package org.antrain.community.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.Page;
import org.antrain.community.bo.TopicAddBo;
import org.antrain.community.bo.TopicQueryBo;
import org.antrain.community.bo.TopicEditBo;
import org.antrain.community.domain.Topic;
import org.antrain.community.mapper.TopicMapper;
import org.antrain.community.vo.TopicVo;
import org.antrain.community.service.ITopicService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 主题Service业务层处理
 *
 * @author antrain
 * @date 2021-04-11
 */
@Service
public class TopicServiceImpl extends ServiceImpl<TopicMapper, Topic> implements ITopicService {

    @Override
    public TopicVo queryById(Long id){
        Topic db = this.baseMapper.selectById(id);
        return BeanUtil.toBean(db, TopicVo.class);
    }

    @Override
    public List<TopicVo> queryList(TopicQueryBo bo) {
        LambdaQueryWrapper<Topic> lqw = Wrappers.lambdaQuery();
        lqw.like(StrUtil.isNotBlank(bo.getTitle()), Topic::getTitle, bo.getTitle());
        lqw.like(StrUtil.isNotBlank(bo.getNickname()), Topic::getNickname, bo.getNickname());
        return entity2Vo(this.list(lqw));
    }

    /**
    * 实体类转化成视图对象
    *
    * @param collection 实体类集合
    * @return
    */
    private List<TopicVo> entity2Vo(Collection<Topic> collection) {
        List<TopicVo> voList = collection.stream()
                .map(any -> BeanUtil.toBean(any, TopicVo.class))
                .collect(Collectors.toList());
        if (collection instanceof Page) {
            Page<Topic> page = (Page<Topic>)collection;
            Page<TopicVo> pageVo = new Page<>();
            BeanUtil.copyProperties(page,pageVo);
            pageVo.addAll(voList);
            voList = pageVo;
        }
        return voList;
    }

    @Override
    public Boolean insertByAddBo(TopicAddBo bo) {
        Topic add = BeanUtil.toBean(bo, Topic.class);
        validEntityBeforeSave(add);
        return this.save(add);
    }

    @Override
    public Boolean updateByEditBo(TopicEditBo bo) {
        Topic update = BeanUtil.toBean(bo, Topic.class);
        validEntityBeforeSave(update);
        return this.updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(Topic entity){
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
