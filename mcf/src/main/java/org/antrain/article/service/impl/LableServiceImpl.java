package org.antrain.article.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.Page;
import org.antrain.article.bo.LableAddBo;
import org.antrain.article.bo.LableQueryBo;
import org.antrain.article.bo.LableEditBo;
import org.antrain.article.domain.Lable;
import org.antrain.article.mapper.LableMapper;
import org.antrain.article.vo.LableVo;
import org.antrain.article.service.ILableService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 标签Service业务层处理
 *
 * @author antrain
 * @date 2021-04-11
 */
@Service
public class LableServiceImpl extends ServiceImpl<LableMapper, Lable> implements ILableService {

    @Override
    public JSONObject getAll() {
        QueryWrapper<Lable> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id","title");
        queryWrapper.eq("status","Y");
        JSONObject jsonObject = new JSONObject();
        List<Lable> list = this.list(queryWrapper);
        for(Lable label : list) {
            jsonObject.put(label.getId().toString(),label.getTitle());
        }
        return jsonObject;
    }

    @Override
    public LableVo queryById(Long id){
        Lable db = this.baseMapper.selectById(id);
        return BeanUtil.toBean(db, LableVo.class);
    }

    @Override
    public List<LableVo> queryList(LableQueryBo bo) {
        LambdaQueryWrapper<Lable> lqw = Wrappers.lambdaQuery();
        lqw.like(StrUtil.isNotBlank(bo.getTitle()), Lable::getTitle, bo.getTitle());
        lqw.like(StrUtil.isNotBlank(bo.getNickname()), Lable::getNickname, bo.getNickname());
        return entity2Vo(this.list(lqw));
    }

    /**
    * 实体类转化成视图对象
    *
    * @param collection 实体类集合
    * @return
    */
    private List<LableVo> entity2Vo(Collection<Lable> collection) {
        List<LableVo> voList = collection.stream()
                .map(any -> BeanUtil.toBean(any, LableVo.class))
                .collect(Collectors.toList());
        if (collection instanceof Page) {
            Page<Lable> page = (Page<Lable>)collection;
            Page<LableVo> pageVo = new Page<>();
            BeanUtil.copyProperties(page,pageVo);
            pageVo.addAll(voList);
            voList = pageVo;
        }
        return voList;
    }

    @Override
    public Boolean insertByAddBo(LableAddBo bo) {
        Lable add = BeanUtil.toBean(bo, Lable.class);
        validEntityBeforeSave(add);
        return this.save(add);
    }

    @Override
    public Boolean updateByEditBo(LableEditBo bo) {
        Lable update = BeanUtil.toBean(bo, Lable.class);
        validEntityBeforeSave(update);
        return this.updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(Lable entity){
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
