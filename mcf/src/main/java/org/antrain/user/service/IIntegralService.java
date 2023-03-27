package org.antrain.user.service;

import org.antrain.user.domain.Integral;
import org.antrain.user.vo.IntegralVo;
import org.antrain.user.bo.IntegralQueryBo;
import org.antrain.user.bo.IntegralAddBo;
import org.antrain.user.bo.IntegralEditBo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;
import java.util.List;

/**
 * 用户积分记录Service接口
 *
 * @author antrain
 * @date 2021-04-11
 */
public interface IIntegralService extends IService<Integral> {
	/**
	 * 查询单个
	 * @return
	 */
	IntegralVo queryById(Long id);

	/**
	 * 查询列表
	 */
	List<IntegralVo> queryList(IntegralQueryBo bo);

	/**
	 * 根据新增业务对象插入用户积分记录
	 * @param bo 用户积分记录新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(IntegralAddBo bo);

	/**
	 * 根据编辑业务对象修改用户积分记录
	 * @param bo 用户积分记录编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(IntegralEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

	/**
	 * 检查当前用户是否已签到
	 * @param userId
	 * @return
	 */
	Long  checkSignIn(Long userId);
}
