package org.antrain.community.service;

import org.antrain.community.domain.Invitation;
import org.antrain.community.vo.InvitationVo;
import org.antrain.community.bo.InvitationQueryBo;
import org.antrain.community.bo.InvitationAddBo;
import org.antrain.community.bo.InvitationEditBo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;
import java.util.List;

/**
 * 帖子Service接口
 *
 * @author antrain
 * @date 2021-04-11
 */
public interface IInvitationService extends IService<Invitation> {
	/**
	 * 查询单个
	 * @return
	 */
	InvitationVo queryById(Long id);

	/**
	 * 查询列表
	 */
	List<InvitationVo> queryList(InvitationQueryBo bo);

	/**
	 * 根据新增业务对象插入帖子
	 * @param bo 帖子新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(InvitationAddBo bo);

	/**
	 * 根据编辑业务对象修改帖子
	 * @param bo 帖子编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(InvitationEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
