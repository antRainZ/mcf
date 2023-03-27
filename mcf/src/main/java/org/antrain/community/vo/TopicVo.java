package org.antrain.community.vo;

import org.antrain.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;



/**
 * 主题视图对象 mall_package
 *
 * @author antrain
 * @date 2021-04-11
 */
@Data
@ApiModel("主题视图对象")
public class TopicVo {
	private static final long serialVersionUID = 1L;

	/** 主题主键 */
	@ApiModelProperty("主题主键")
	private Long id;

	/** 标题 */
	@Excel(name = "标题")
	@ApiModelProperty("标题")
	private String title;
	/** 创办用户id */
	@Excel(name = "创办用户id")
	@ApiModelProperty("创办用户id")
	private Long userId;
	/** 创办用户昵称 */
	@Excel(name = "创办用户昵称")
	@ApiModelProperty("创办用户昵称")
	private String nickname;
	/** 封面 */
	@Excel(name = "封面")
	@ApiModelProperty("封面")
	private String cover;
	/** 描述 */
	@Excel(name = "描述")
	@ApiModelProperty("描述")
	private String description;
	/** 申请文件 */
	@Excel(name = "申请文件")
	@ApiModelProperty("申请文件")
	private String fileUrl;
	/** 理由 */
	@Excel(name = "理由")
	@ApiModelProperty("理由")
	private String reason;
	/** 备注 */
	@Excel(name = "备注")
	@ApiModelProperty("备注")
	private String remark;
	/** 是否加精，会显示在菜单栏里 */
	@Excel(name = "是否加精，会显示在菜单栏里")
	@ApiModelProperty("是否加精，会显示在菜单栏里")
	private String refine;
	/** 是否有效：A 申请中  Y有效 默认， N 无效 */
	@Excel(name = "是否有效：A 申请中  Y有效 默认， N 无效")
	@ApiModelProperty("是否有效：A 申请中  Y有效 默认， N 无效")
	private String status;

}
