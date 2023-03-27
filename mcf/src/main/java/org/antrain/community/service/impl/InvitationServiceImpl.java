package org.antrain.community.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.Page;
import org.antrain.community.bo.InvitationAddBo;
import org.antrain.community.bo.InvitationQueryBo;
import org.antrain.community.bo.InvitationEditBo;
import org.antrain.community.domain.Invitation;
import org.antrain.community.mapper.InvitationMapper;
import org.antrain.community.vo.InvitationVo;
import org.antrain.community.service.IInvitationService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 帖子Service业务层处理
 *
 * @author antrain
 * @date 2021-04-11
 */
@Service
public class InvitationServiceImpl extends ServiceImpl<InvitationMapper, Invitation> implements IInvitationService {

    @Override
    public InvitationVo queryById(Long id){
        Invitation db = this.baseMapper.selectById(id);
        return BeanUtil.toBean(db, InvitationVo.class);
    }

    @Override
    public List<InvitationVo> queryList(InvitationQueryBo bo) {
        LambdaQueryWrapper<Invitation> lqw = Wrappers.lambdaQuery();
        lqw.select(Invitation.class, i-> !"content".equals(i.getColumn()));
        lqw.like(StrUtil.isNotBlank(bo.getTopicTitle()), Invitation::getTopicTitle, bo.getTopicTitle());
        lqw.like(StrUtil.isNotBlank(bo.getTitle()), Invitation::getTitle, bo.getTitle());
        lqw.like(StrUtil.isNotBlank(bo.getNickname()), Invitation::getNickname, bo.getNickname());
        return entity2Vo(this.list(lqw));
    }

    /**
    * 实体类转化成视图对象
    *
    * @param collection 实体类集合
    * @return
    */
    private List<InvitationVo> entity2Vo(Collection<Invitation> collection) {
        List<InvitationVo> voList = collection.stream()
                .map(any -> BeanUtil.toBean(any, InvitationVo.class))
                .collect(Collectors.toList());
        if (collection instanceof Page) {
            Page<Invitation> page = (Page<Invitation>)collection;
            Page<InvitationVo> pageVo = new Page<>();
            BeanUtil.copyProperties(page,pageVo);
            pageVo.addAll(voList);
            voList = pageVo;
        }
        return voList;
    }

    @Override
    public Boolean insertByAddBo(InvitationAddBo bo) {
        Invitation add = BeanUtil.toBean(bo, Invitation.class);
        validEntityBeforeSave(add);
        return this.save(add);
    }

    @Override
    public Boolean updateByEditBo(InvitationEditBo bo) {
        Invitation update = BeanUtil.toBean(bo, Invitation.class);
        validEntityBeforeSave(update);
        return this.updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(Invitation entity){
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
