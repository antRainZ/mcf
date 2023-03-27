package org.antrain.user.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import org.antrain.common.annotation.Excel;

/**
 * 好友申请对象 apply
 * 
 * @author antrain
 * @date 2021-04-11
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("apply")
public class Apply implements Serializable {

private static final long serialVersionUID=1L;


    /** 消息主键 */
    @TableId(value = "id")
    private Long id;

    /** 发送者id */
    private Long sendId;

    /** 发送者昵称 */
    private String sendName;

    /** 接受者id */
    private Long acceptId;

    /** 发送者昵称 */
    private String acceptName;

    /** 申请内容 */
    private String content;

    /** 申请回复 */
    private String reply;

    /** 状态 N 未接受 默认， Y 允许；R 拒绝 */
    private String status;

    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /** 修改时间 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
