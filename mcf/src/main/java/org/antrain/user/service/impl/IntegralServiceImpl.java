package org.antrain.user.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.Page;
import org.antrain.user.bo.IntegralAddBo;
import org.antrain.user.bo.IntegralQueryBo;
import org.antrain.user.bo.IntegralEditBo;
import org.antrain.user.domain.Integral;
import org.antrain.user.mapper.IntegralMapper;
import org.antrain.user.vo.IntegralVo;
import org.antrain.user.service.IIntegralService;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户积分记录Service业务层处理
 *
 * @author antrain
 * @date 2021-04-11
 */
@Service
public class IntegralServiceImpl extends ServiceImpl<IntegralMapper, Integral> implements IIntegralService {

    @Resource
    private IntegralMapper integralMapper;

    @Override
    public IntegralVo queryById(Long id){
        Integral db = this.baseMapper.selectById(id);
        return BeanUtil.toBean(db, IntegralVo.class);
    }

    @Override
    public List<IntegralVo> queryList(IntegralQueryBo bo) {
        LambdaQueryWrapper<Integral> lqw = Wrappers.lambdaQuery();
        lqw.like(StrUtil.isNotBlank(bo.getNickname()), Integral::getNickname, bo.getNickname());
        return entity2Vo(this.list(lqw));
    }

    /**
    * 实体类转化成视图对象
    *
    * @param collection 实体类集合
    * @return
    */
    private List<IntegralVo> entity2Vo(Collection<Integral> collection) {
        List<IntegralVo> voList = collection.stream()
                .map(any -> BeanUtil.toBean(any, IntegralVo.class))
                .collect(Collectors.toList());
        if (collection instanceof Page) {
            Page<Integral> page = (Page<Integral>)collection;
            Page<IntegralVo> pageVo = new Page<>();
            BeanUtil.copyProperties(page,pageVo);
            pageVo.addAll(voList);
            voList = pageVo;
        }
        return voList;
    }

    @Override
    public Boolean insertByAddBo(IntegralAddBo bo) {
        Integral add = BeanUtil.toBean(bo, Integral.class);
        validEntityBeforeSave(add);
        return this.save(add);
    }

    @Override
    public Boolean updateByEditBo(IntegralEditBo bo) {
        Integral update = BeanUtil.toBean(bo, Integral.class);
        validEntityBeforeSave(update);
        return this.updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(Integral entity){
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
    public Long checkSignIn(Long userId) {
        return integralMapper.checkSignIn(userId);
    }
}
