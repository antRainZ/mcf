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
 * 主题对象 topic
 * 
 * @author antrain
 * @date 2021-04-11
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("topic")
public class Topic implements Serializable {

private static final long serialVersionUID=1L;


    /** 主题主键 */
    @TableId(value = "id")
    private Long id;

    /** 标题 */
    private String title;

    /** 创办用户id */
    private Long userId;

    /** 创办用户昵称 */
    private String nickname;

    /** 封面 */
    private String cover;

    /** 描述 */
    private String description;

    /** 申请文件 */
    private String fileUrl;

    /** 理由 */
    private String reason;

    /** 备注 */
    private String remark;

    /** 是否加精，会显示在菜单栏里 */
    private String refine;

    /** 是否有效：A 申请中  Y有效 默认， N 无效 */
    private String status;

    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /** 修改时间 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
