package org.antrain.community.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import org.antrain.common.annotation.Excel;

/**
 * 帖子对象 invitation
 * 
 * @author antrain
 * @date 2021-04-11
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("invitation")
public class Invitation implements Serializable {

private static final long serialVersionUID=1L;


    /** 主键 */
    @TableId(value = "id")
    private Long id;

    /** 主题主键 */
    private Long topicId;

    /** 主题标题 */
    private String topicTitle;

    /** 标题 */
    private String title;

    /** 创办用户id */
    private Long userId;

    /** 创办用户昵称 */
    private String nickname;

    /** 内容 */
    private String content;

    /** 是否加精 */
    private String refine;

    /** 备注 */
    private String remark;

    /** 是否有效： Y有效 默认， N 无效 */
    private String status;

    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /** 修改时间 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
