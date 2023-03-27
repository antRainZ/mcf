package org.antrain.user.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import org.antrain.common.annotation.Excel;

/**
 * 用户对象 user
 * 
 * @author antrain
 * @date 2021-04-11
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("user")
public class User implements Serializable {

private static final long serialVersionUID=1L;


    /** 消息主键 */
    @TableId(value = "id")
    private Long id;

    /** 用户名 */
    private String username;

    /** 用户昵称 */
    private String nickname;

    /** 用户姓名 */
    private String name;

    /** 头像地址 */
    private String avatar;

    /** 密码 */
    @JSONField(serialize = false)
    private String password;

    /** 性别 */
    private String sex;

    /** 手机号 */
    private String telephone;

    /** 邮箱 */
    private String email;

    /** 学校 */
    private String school;

    /** 院系 */
    private String dept;

    /** 专业 */
    private String major;

    /** 家庭地址 */
    private String address;

    /** 现居住地 */
    private String contact;

    /** 介绍 */
    private String introduce;

    /** 兴趣爱好 */
    private String interest;

    /** 用户积分 */
    private Long integral;

    /** 状态 Y 有效 N 无效 */
    private String status;

    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /** 修改时间 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
