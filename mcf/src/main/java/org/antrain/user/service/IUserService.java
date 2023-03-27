package org.antrain.user.service;

import org.antrain.user.domain.User;
import org.antrain.user.vo.UserVo;
import org.antrain.user.bo.UserQueryBo;
import org.antrain.user.bo.UserAddBo;
import org.antrain.user.bo.UserEditBo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;
import java.util.List;

/**
 * 用户Service接口
 *
 * @author antrain
 * @date 2021-04-11
 */
public interface IUserService extends IService<User> {
	/**
	 * 查询单个
	 * @return
	 */
	UserVo queryById(Long id);

	/**
	 * 查询列表
	 */
	List<UserVo> queryList(UserQueryBo bo);

	/**
	 * 根据新增业务对象插入用户
	 * @param bo 用户新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(UserAddBo bo);

	/**
	 * 根据编辑业务对象修改用户
	 * @param bo 用户编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(UserEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
