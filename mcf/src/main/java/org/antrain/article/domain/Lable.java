package org.antrain.article.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import org.antrain.common.annotation.Excel;

/**
 * 标签对象 lable
 * 
 * @author antrain
 * @date 2021-04-11
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("lable")
public class Lable implements Serializable {

private static final long serialVersionUID=1L;


    /** 主键 */
    @TableId(value = "id")
    private Long id;

    /** 主题标题 */
    private String title;

    /** 备注 */
    private String remark;

    /** 创办用户id */
    private Long userId;

    /** 创办用户昵称 */
    private String nickname;

    /** 是否有效： Y有效 默认， N 无效 */
    private String status;

    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /** 修改时间 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
