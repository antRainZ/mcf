package org.antrain.user.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;


/**
 * 用户编辑对象 user
 *
 * @author antrain
 * @date 2021-04-11
 */
@Data
@ApiModel("用户编辑对象")
public class UserEditBo {

    private Long id;
    /** 用户名 */
    @ApiModelProperty("用户名")
    private String username;

    /** 用户昵称 */
    @ApiModelProperty("用户昵称")
    private String nickname;

    /** 用户姓名 */
    @ApiModelProperty("用户姓名")
    private String name;

    /** 头像地址 */
    @ApiModelProperty("头像地址")
    private String avatar;

    /** 密码 */
    @ApiModelProperty("密码")
    private String password;

    /** 性别 */
    @ApiModelProperty("性别")
    private String sex;

    /** 手机号 */
    @ApiModelProperty("手机号")
    private String telephone;

    /** 邮箱 */
    @ApiModelProperty("邮箱")
    private String email;

    /** 学校 */
    @ApiModelProperty("学校")
    private String school;

    /** 院系 */
    @ApiModelProperty("院系")
    private String dept;

    /** 专业 */
    @ApiModelProperty("专业")
    private String major;

    /** 家庭地址 */
    @ApiModelProperty("家庭地址")
    private String address;

    /** 现居住地 */
    @ApiModelProperty("现居住地")
    private String contact;

    /** 介绍 */
    @ApiModelProperty("介绍")
    private String introduce;

    /** 兴趣爱好 */
    @ApiModelProperty("兴趣爱好")
    private String interest;

    /** 用户积分 */
    @ApiModelProperty("用户积分")
    private Long integral;

    /** 状态 Y 有效 N 无效 */
    @ApiModelProperty("状态 Y 有效 N 无效")
    private String status;

    /** 修改时间 */
    @ApiModelProperty("修改时间")
    private Date updateTime;
}
