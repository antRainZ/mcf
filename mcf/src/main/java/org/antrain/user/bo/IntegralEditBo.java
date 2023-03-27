package org.antrain.user.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;


/**
 * 用户积分记录编辑对象 integral
 *
 * @author antrain
 * @date 2021-04-11
 */
@Data
@ApiModel("用户积分记录编辑对象")
public class IntegralEditBo {

    private Long id;
    /** 评论用户id */
    @ApiModelProperty("评论用户id")
    private Long userId;

    /** 评论用户昵称 */
    @ApiModelProperty("评论用户昵称")
    private String nickname;

    /** 增加多少积分 */
    @ApiModelProperty("增加多少积分")
    private Long addValue;

    /** 增强前积分 */
    @ApiModelProperty("增强前积分")
    private Long perValue;

    /** 备注 */
    @ApiModelProperty("备注")
    private String remark;

    /** 是否有效： Y有效 默认， N 无效 */
    @ApiModelProperty("是否有效： Y有效 默认， N 无效")
    private String status;

    /** 修改时间 */
    @ApiModelProperty("修改时间")
    private Date updateTime;
}
