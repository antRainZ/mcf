package org.antrain.community.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.Map;
import java.util.HashMap;

import org.antrain.common.core.domain.BaseEntity;

/**
 * 帖子评论分页查询对象 invitation_comment
 *
 * @author antrain
 * @date 2021-04-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("帖子评论分页查询对象")
public class InvitationCommentQueryBo extends BaseEntity {

	/** 分页大小 */
	@ApiModelProperty("分页大小")
	private Integer pageSize;
	/** 当前页数 */
	@ApiModelProperty("当前页数")
	private Integer pageNum;
	/** 排序列 */
	@ApiModelProperty("排序列")
	private String orderByColumn;
	/** 排序的方向desc或者asc */
	@ApiModelProperty(value = "排序的方向", example = "asc,desc")
	private String isAsc;


	/** 对应帖子标题 */
	@ApiModelProperty("对应帖子标题")
	private String invitationTitle;
	/** 评论用户昵称 */
	@ApiModelProperty("评论用户昵称")
	private String nickname;

}
