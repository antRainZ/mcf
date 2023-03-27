package org.antrain.user.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;


/**
 * 好友申请编辑对象 apply
 *
 * @author antrain
 * @date 2021-04-11
 */
@Data
@ApiModel("好友申请编辑对象")
public class ApplyEditBo {

    private Long id;
    /** 发送者id */
    @ApiModelProperty("发送者id")
    private Long sendId;

    /** 发送者昵称 */
    @ApiModelProperty("发送者昵称")
    private String sendName;

    /** 接受者id */
    @ApiModelProperty("接受者id")
    private Long acceptId;

    /** 发送者昵称 */
    @ApiModelProperty("发送者昵称")
    private String acceptName;

    /** 申请内容 */
    @ApiModelProperty("申请内容")
    private String content;

    /** 申请回复 */
    @ApiModelProperty("申请回复")
    private String reply;

    /** 状态 N 未接受 默认， Y 允许；R 拒绝 */
    @ApiModelProperty("状态 N 未接受 默认， Y 允许；R 拒绝")
    private String status;

    /** 修改时间 */
    @ApiModelProperty("修改时间")
    private Date updateTime;
}
