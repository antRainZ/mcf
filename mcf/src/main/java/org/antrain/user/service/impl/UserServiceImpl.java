package org.antrain.user.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.Page;
import org.antrain.user.bo.UserAddBo;
import org.antrain.user.bo.UserQueryBo;
import org.antrain.user.bo.UserEditBo;
import org.antrain.user.domain.User;
import org.antrain.user.mapper.UserMapper;
import org.antrain.user.vo.UserVo;
import org.antrain.user.service.IUserService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户Service业务层处理
 *
 * @author antrain
 * @date 2021-04-11
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public UserVo queryById(Long id){
        User db = this.baseMapper.selectById(id);
        return BeanUtil.toBean(db, UserVo.class);
    }

    @Override
    public List<UserVo> queryList(UserQueryBo bo) {
        LambdaQueryWrapper<User> lqw = Wrappers.lambdaQuery();
        lqw.like(StrUtil.isNotBlank(bo.getName()), User::getName, bo.getName());
        lqw.eq(StrUtil.isNotBlank(bo.getSchool()), User::getSchool, bo.getSchool());
        return entity2Vo(this.list(lqw));
    }

    /**
    * 实体类转化成视图对象
    *
    * @param collection 实体类集合
    * @return
    */
    private List<UserVo> entity2Vo(Collection<User> collection) {
        List<UserVo> voList = collection.stream()
                .map(any -> BeanUtil.toBean(any, UserVo.class))
                .collect(Collectors.toList());
        if (collection instanceof Page) {
            Page<User> page = (Page<User>)collection;
            Page<UserVo> pageVo = new Page<>();
            BeanUtil.copyProperties(page,pageVo);
            pageVo.addAll(voList);
            voList = pageVo;
        }
        return voList;
    }

    @Override
    public Boolean insertByAddBo(UserAddBo bo) {
        User add = BeanUtil.toBean(bo, User.class);
        validEntityBeforeSave(add);
        return this.save(add);
    }

    @Override
    public Boolean updateByEditBo(UserEditBo bo) {
        User update = BeanUtil.toBean(bo, User.class);
        validEntityBeforeSave(update);
        return this.updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(User entity){
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
