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
 * 好友申请分页查询对象 apply
 *
 * @author antrain
 * @date 2021-04-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("好友申请分页查询对象")
public class ApplyQueryBo extends BaseEntity {

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
	/** 申请内容 */
	@ApiModelProperty("申请内容")
	private String content;

}
