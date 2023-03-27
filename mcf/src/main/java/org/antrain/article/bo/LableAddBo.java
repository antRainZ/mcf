package org.antrain.article.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;



/**
 * 标签添加对象 lable
 *
 * @author antrain
 * @date 2021-04-11
 */
@Data
@ApiModel("标签添加对象")
public class LableAddBo {
    /** 创办用户id */
    private Long userId;

    /** 创办用户昵称 */
    private String nickname;
    /** 标题 */
    @ApiModelProperty("标题")
    private String title;
    /** 备注 */
    @ApiModelProperty("备注")
    private String remark;
}
