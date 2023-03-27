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
 * 消息对象 message
 * 
 * @author antrain
 * @date 2021-04-11
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("message")
public class Message implements Serializable {

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

    /** 内容 */
    private String content;

    /** 类别：C普通 默认; */
    private String type;

    /** 是否有效： Y有效 默认， N 无效 */
    private String status;

    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /** 修改时间 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
