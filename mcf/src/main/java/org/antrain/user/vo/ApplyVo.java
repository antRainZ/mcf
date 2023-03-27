package org.antrain.user.vo;

import org.antrain.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;



/**
 * 好友申请视图对象 mall_package
 *
 * @author antrain
 * @date 2021-04-11
 */
@Data
@ApiModel("好友申请视图对象")
public class ApplyVo {
	private static final long serialVersionUID = 1L;

	/** 消息主键 */
	@ApiModelProperty("消息主键")
	private Long id;

	/** 发送者id */
	@Excel(name = "发送者id")
	@ApiModelProperty("发送者id")
	private Long sendId;
	/** 发送者昵称 */
	@Excel(name = "发送者昵称")
	@ApiModelProperty("发送者昵称")
	private String sendName;
	/** 接受者id */
	@Excel(name = "接受者id")
	@ApiModelProperty("接受者id")
	private Long acceptId;
	/** 发送者昵称 */
	@Excel(name = "发送者昵称")
	@ApiModelProperty("发送者昵称")
	private String acceptName;
	/** 申请内容 */
	@Excel(name = "申请内容")
	@ApiModelProperty("申请内容")
	private String content;
	/** 申请回复 */
	@Excel(name = "申请回复")
	@ApiModelProperty("申请回复")
	private String reply;
	/** 状态 N 未接受 默认， Y 允许；R 拒绝 */
	@Excel(name = "状态 N 未接受 默认， Y 允许；R 拒绝")
	@ApiModelProperty("状态 N 未接受 默认， Y 允许；R 拒绝")
	private String status;

}
