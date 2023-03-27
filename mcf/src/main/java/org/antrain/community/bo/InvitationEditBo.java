package org.antrain.community.bo;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;


/**
 * 帖子编辑对象 invitation
 *
 * @author antrain
 * @date 2021-04-11
 */
@Data
@ApiModel("帖子编辑对象")
public class InvitationEditBo {

    /** 帖子评论主键 */
    private Long id;

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

    /** 是否加精 */
    @ApiModelProperty("是否加精")
    private String refine;

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
