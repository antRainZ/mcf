package org.antrain.user.service;

import org.antrain.user.domain.Apply;
import org.antrain.user.vo.ApplyVo;
import org.antrain.user.bo.ApplyQueryBo;
import org.antrain.user.bo.ApplyAddBo;
import org.antrain.user.bo.ApplyEditBo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;
import java.util.List;

/**
 * 好友申请Service接口
 *
 * @author antrain
 * @date 2021-04-11
 */
public interface IApplyService extends IService<Apply> {
	/**
	 * 查询单个
	 * @return
	 */
	ApplyVo queryById(Long id);

	/**
	 * 查询列表
	 */
	List<ApplyVo> queryList(ApplyQueryBo bo);

	/**
	 * 根据新增业务对象插入好友申请
	 * @param bo 好友申请新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(ApplyAddBo bo);

	/**
	 * 根据编辑业务对象修改好友申请
	 * @param bo 好友申请编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(ApplyEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
	int accept(Long id,Long userId);
	int reject(Long id,Long userId);
	int deleteById(Long id,Long userId);
}
