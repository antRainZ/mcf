package org.antrain.user.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.Page;
import org.antrain.user.bo.ApplyAddBo;
import org.antrain.user.bo.ApplyQueryBo;
import org.antrain.user.bo.ApplyEditBo;
import org.antrain.user.domain.Apply;
import org.antrain.user.mapper.ApplyMapper;
import org.antrain.user.vo.ApplyVo;
import org.antrain.user.service.IApplyService;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 好友申请Service业务层处理
 *
 * @author antrain
 * @date 2021-04-11
 */
@Service
public class ApplyServiceImpl extends ServiceImpl<ApplyMapper, Apply> implements IApplyService {

    @Resource
    private ApplyMapper applyMapper;

    @Override
    public ApplyVo queryById(Long id){
        Apply db = this.baseMapper.selectById(id);
        return BeanUtil.toBean(db, ApplyVo.class);
    }

    @Override
    public List<ApplyVo> queryList(ApplyQueryBo bo) {
        LambdaQueryWrapper<Apply> lqw = Wrappers.lambdaQuery();
        lqw.like(StrUtil.isNotBlank(bo.getSendName()), Apply::getSendName, bo.getSendName());
        lqw.like(StrUtil.isNotBlank(bo.getAcceptName()), Apply::getAcceptName, bo.getAcceptName());
        lqw.like(StrUtil.isNotBlank(bo.getContent()), Apply::getContent, bo.getContent());
        return entity2Vo(this.list(lqw));
    }

    /**
    * 实体类转化成视图对象
    *
    * @param collection 实体类集合
    * @return
    */
    private List<ApplyVo> entity2Vo(Collection<Apply> collection) {
        List<ApplyVo> voList = collection.stream()
                .map(any -> BeanUtil.toBean(any, ApplyVo.class))
                .collect(Collectors.toList());
        if (collection instanceof Page) {
            Page<Apply> page = (Page<Apply>)collection;
            Page<ApplyVo> pageVo = new Page<>();
            BeanUtil.copyProperties(page,pageVo);
            pageVo.addAll(voList);
            voList = pageVo;
        }
        return voList;
    }

    @Override
    public Boolean insertByAddBo(ApplyAddBo bo) {
        Apply add = BeanUtil.toBean(bo, Apply.class);
        validEntityBeforeSave(add);
        return this.save(add);
    }

    @Override
    public Boolean updateByEditBo(ApplyEditBo bo) {
        Apply update = BeanUtil.toBean(bo, Apply.class);
        validEntityBeforeSave(update);
        return this.updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(Apply entity){
        //TODO 做一些数据校验,如唯一约束
    }

    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return this.removeByIds(ids);
    }

    @Override
    public int accept(Long id, Long userId) {
        return applyMapper.accept(id,userId);
    }

    @Override
    public int reject(Long id, Long userId) {
        return applyMapper.reject(id,userId);
    }

    @Override
    public int deleteById(Long id, Long userId) {
        return applyMapper.deleteById(id,userId);
    }
}
