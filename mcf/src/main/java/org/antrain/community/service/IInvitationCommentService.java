package org.antrain.community.service;

import org.antrain.community.domain.InvitationComment;
import org.antrain.community.vo.InvitationCommentVo;
import org.antrain.community.bo.InvitationCommentQueryBo;
import org.antrain.community.bo.InvitationCommentAddBo;
import org.antrain.community.bo.InvitationCommentEditBo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;
import java.util.List;

/**
 * 帖子评论Service接口
 *
 * @author antrain
 * @date 2021-04-11
 */
public interface IInvitationCommentService extends IService<InvitationComment> {
	/**
	 * 查询单个
	 * @return
	 */
	InvitationCommentVo queryById(Long id);

	/**
	 * 查询列表
	 */
	List<InvitationCommentVo> queryList(InvitationCommentQueryBo bo);

	/**
	 * 根据新增业务对象插入帖子评论
	 * @param bo 帖子评论新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(InvitationCommentAddBo bo);

	/**
	 * 根据编辑业务对象修改帖子评论
	 * @param bo 帖子评论编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(InvitationCommentEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
