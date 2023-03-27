package org.antrain.community.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;



/**
 * 帖子添加对象 invitation
 *
 * @author antrain
 * @date 2021-04-11
 */
@Data
@ApiModel("帖子添加对象")
public class InvitationAddBo {

    /** 主题主键 */
    @ApiModelProperty("主题主键")
    private Long topicId;
    /** 主题标题 */
    @ApiModelProperty("主题标题")
    private String topicTitle;
    /** 标题 */
    @ApiModelProperty("标题")
    private String title;
    /** 创办用户id */
    @ApiModelProperty("创办用户id")
    private Long userId;
    /** 创办用户昵称 */
    @ApiModelProperty("创办用户昵称")
    private String nickname;
    /** 内容 */
    @ApiModelProperty("内容")
    private String content;
    /** 备注 */
    @ApiModelProperty("备注")
    private String remark;

}
