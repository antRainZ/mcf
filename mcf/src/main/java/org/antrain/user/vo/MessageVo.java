package org.antrain.user.vo;

import org.antrain.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;



/**
 * 消息视图对象 mall_package
 *
 * @author antrain
 * @date 2021-04-11
 */
@Data
@ApiModel("消息视图对象")
public class MessageVo {
	private static final long serialVersionUID = 1L;


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
	/** 内容 */
	@Excel(name = "内容")
	@ApiModelProperty("内容")
	private String content;
	/** 类别：C普通 默认; */
	@Excel(name = "类别：C普通 默认;")
	@ApiModelProperty("类别：C普通 默认;")
	private String type;
	/** 是否有效： Y有效 默认， N 无效 */
	@Excel(name = "是否有效： Y有效 默认， N 无效")
	@ApiModelProperty("是否有效： Y有效 默认， N 无效")
	private String status;

}
