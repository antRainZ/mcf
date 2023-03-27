package org.antrain.community.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.Page;
import org.antrain.community.bo.InvitationCommentAddBo;
import org.antrain.community.bo.InvitationCommentQueryBo;
import org.antrain.community.bo.InvitationCommentEditBo;
import org.antrain.community.domain.InvitationComment;
import org.antrain.community.mapper.InvitationCommentMapper;
import org.antrain.community.vo.InvitationCommentVo;
import org.antrain.community.service.IInvitationCommentService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 帖子评论Service业务层处理
 *
 * @author antrain
 * @date 2021-04-11
 */
@Service
public class InvitationCommentServiceImpl extends ServiceImpl<InvitationCommentMapper, InvitationComment> implements IInvitationCommentService {

    @Override
    public InvitationCommentVo queryById(Long id){
        InvitationComment db = this.baseMapper.selectById(id);
        return BeanUtil.toBean(db, InvitationCommentVo.class);
    }

    @Override
    public List<InvitationCommentVo> queryList(InvitationCommentQueryBo bo) {
        LambdaQueryWrapper<InvitationComment> lqw = Wrappers.lambdaQuery();
        lqw.like(StrUtil.isNotBlank(bo.getInvitationTitle()), InvitationComment::getInvitationTitle, bo.getInvitationTitle());
        lqw.like(StrUtil.isNotBlank(bo.getNickname()), InvitationComment::getNickname, bo.getNickname());
        return entity2Vo(this.list(lqw));
    }

    /**
    * 实体类转化成视图对象
    *
    * @param collection 实体类集合
    * @return
    */
    private List<InvitationCommentVo> entity2Vo(Collection<InvitationComment> collection) {
        List<InvitationCommentVo> voList = collection.stream()
                .map(any -> BeanUtil.toBean(any, InvitationCommentVo.class))
                .collect(Collectors.toList());
        if (collection instanceof Page) {
            Page<InvitationComment> page = (Page<InvitationComment>)collection;
            Page<InvitationCommentVo> pageVo = new Page<>();
            BeanUtil.copyProperties(page,pageVo);
            pageVo.addAll(voList);
            voList = pageVo;
        }
        return voList;
    }

    @Override
    public Boolean insertByAddBo(InvitationCommentAddBo bo) {
        InvitationComment add = BeanUtil.toBean(bo, InvitationComment.class);
        validEntityBeforeSave(add);
        return this.save(add);
    }

    @Override
    public Boolean updateByEditBo(InvitationCommentEditBo bo) {
        InvitationComment update = BeanUtil.toBean(bo, InvitationComment.class);
        validEntityBeforeSave(update);
        return this.updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(InvitationComment entity){
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
