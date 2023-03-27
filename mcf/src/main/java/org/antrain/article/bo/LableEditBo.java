package org.antrain.article.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;


/**
 * 标签编辑对象 lable
 *
 * @author antrain
 * @date 2021-04-11
 */
@Data
@ApiModel("标签编辑对象")
public class LableEditBo {
    private Long id;
    /** 主题标题 */
    @ApiModelProperty("主题标题")
    private String title;

    /** 备注 */
    @ApiModelProperty("备注")
    private String remark;

    /** 是否有效： Y有效 默认， N 无效 */
    @ApiModelProperty("是否有效： Y有效 默认， N 无效")
    private String status;


}
