package org.antrain.community.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;



/**
 * 主题添加对象 topic
 *
 * @author antrain
 * @date 2021-04-11
 */
@Data
@ApiModel("主题添加对象")
public class TopicAddBo {
    /** 标题 */
    @ApiModelProperty("标题")
    private String title;
    /** 创办用户id */
    @ApiModelProperty("创办用户id")
    private Long userId;
    /** 创办用户昵称 */
    @ApiModelProperty("创办用户昵称")
    private String nickname;
    /** 封面 */
    @ApiModelProperty("封面")
    private String cover;
    /** 描述 */
    @ApiModelProperty("描述")
    private String description;
    /** 申请文件 */
    @ApiModelProperty("申请文件")
    private String fileUrl;
    /** 理由 */
    @ApiModelProperty("理由")
    private String reason;
}
