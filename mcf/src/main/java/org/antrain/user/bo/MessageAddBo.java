package org.antrain.user.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;



/**
 * 消息添加对象 message
 *
 * @author antrain
 * @date 2021-04-11
 */
@Data
@ApiModel("消息添加对象")
public class MessageAddBo {

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
    /** 内容 */
    @ApiModelProperty("内容")
    private String content;
    /** 类别：C普通 默认; */
    @ApiModelProperty("类别：C普通 默认;")
    private String type;
    /** 是否有效： Y有效 默认， N 无效 */
    @ApiModelProperty("是否有效： Y有效 默认， N 无效")
    private String status;
    /** 创建时间 */
    @ApiModelProperty("创建时间")
    private Date createTime;
    /** 修改时间 */
    @ApiModelProperty("修改时间")
    private Date updateTime;
}
