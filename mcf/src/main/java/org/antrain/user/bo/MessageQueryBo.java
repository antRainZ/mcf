package org.antrain.user.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.Map;
import java.util.HashMap;

import org.antrain.common.core.domain.BaseEntity;

/**
 * 消息分页查询对象 message
 *
 * @author antrain
 * @date 2021-04-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("消息分页查询对象")
public class MessageQueryBo extends BaseEntity {

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


	/** 发送者昵称 */
	@ApiModelProperty("发送者昵称")
	private String sendName;
	/** 发送者昵称 */
	@ApiModelProperty("发送者昵称")
	private String acceptName;
	/** 内容 */
	@ApiModelProperty("内容")
	private String content;

}
