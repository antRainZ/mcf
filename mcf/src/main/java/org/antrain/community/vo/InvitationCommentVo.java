package org.antrain.community.vo;

import org.antrain.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;



/**
 * 帖子评论视图对象 mall_package
 *
 * @author antrain
 * @date 2021-04-11
 */
@Data
@ApiModel("帖子评论视图对象")
public class InvitationCommentVo {
	private static final long serialVersionUID = 1L;

	/** 帖子评论主键 */
	@ApiModelProperty("帖子评论主键")
	private Long id;

	/** 对应帖子主键 */
	@Excel(name = "对应帖子主键")
	@ApiModelProperty("对应帖子主键")
	private Long invitationId;
	/** 对应帖子标题 */
	@Excel(name = "对应帖子标题")
	@ApiModelProperty("对应帖子标题")
	private String invitationTitle;
	/** 评论用户id */
	@Excel(name = "评论用户id")
	@ApiModelProperty("评论用户id")
	private Long userId;
	/** 评论用户昵称 */
	@Excel(name = "评论用户昵称")
	@ApiModelProperty("评论用户昵称")
	private String nickname;
	/** 内容 */
	@Excel(name = "内容")
	@ApiModelProperty("内容")
	private String content;
	/** 备注 */
	@Excel(name = "备注")
	@ApiModelProperty("备注")
	private String remark;
	/** 是否有效： Y有效 默认， N 无效 */
	@Excel(name = "是否有效： Y有效 默认， N 无效")
	@ApiModelProperty("是否有效： Y有效 默认， N 无效")
	private String status;

}
