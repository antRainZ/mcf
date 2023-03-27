package org.antrain.article.vo;

import org.antrain.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;



/**
 * 文章视图对象 mall_package
 *
 * @author antrain
 * @date 2021-04-11
 */
@Data
@ApiModel("文章视图对象")
public class ArticleVo {
	private static final long serialVersionUID = 1L;

	/** 文章主键 */
	@ApiModelProperty("文章主键")
	private Long id;

	/** 主题标题 */
	@Excel(name = "主题标题")
	@ApiModelProperty("主题标题")
	private String title;
	/** 内容 */
	@Excel(name = "内容")
	@ApiModelProperty("内容")
	private String content;
	/** 备注 */
	@Excel(name = "备注")
	@ApiModelProperty("备注")
	private String remark;
	/** 创办用户id */
	@Excel(name = "创办用户id")
	@ApiModelProperty("创办用户id")
	private Long userId;
	/** 创办用户昵称 */
	@Excel(name = "创办用户昵称")
	@ApiModelProperty("创办用户昵称")
	private String nickname;
	/** 是否加精 */
	@Excel(name = "是否加精")
	@ApiModelProperty("是否加精")
	private String refine;
	/** 是否有效： Y有效 默认， N 无效 */
	@Excel(name = "是否有效： Y有效 默认， N 无效")
	@ApiModelProperty("是否有效： Y有效 默认， N 无效")
	private String status;

	private String labels;

}
