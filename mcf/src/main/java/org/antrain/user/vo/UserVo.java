package org.antrain.user.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import org.antrain.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;



/**
 * 用户视图对象 mall_package
 *
 * @author antrain
 * @date 2021-04-11
 */
@Data
@ApiModel("用户视图对象")
public class UserVo {
	private static final long serialVersionUID = 1L;

	/** 用户名 */
	@Excel(name = "用户名")
	@ApiModelProperty("用户名")
	private String username;
	/** 用户昵称 */
	@Excel(name = "用户昵称")
	@ApiModelProperty("用户昵称")
	private String nickname;
	/** 用户姓名 */
	@Excel(name = "用户姓名")
	@ApiModelProperty("用户姓名")
	private String name;
	/** 头像地址 */
	@Excel(name = "头像地址")
	@ApiModelProperty("头像地址")
	private String avatar;
	/** 性别 */
	@Excel(name = "性别")
	@ApiModelProperty("性别")
	private String sex;
	/** 手机号 */
	@Excel(name = "手机号")
	@ApiModelProperty("手机号")
	private String telephone;
	/** 邮箱 */
	@Excel(name = "邮箱")
	@ApiModelProperty("邮箱")
	private String email;
	/** 学校 */
	@Excel(name = "学校")
	@ApiModelProperty("学校")
	private String school;
	/** 院系 */
	@Excel(name = "院系")
	@ApiModelProperty("院系")
	private String dept;
	/** 专业 */
	@Excel(name = "专业")
	@ApiModelProperty("专业")
	private String major;
	/** 家庭地址 */
	@Excel(name = "家庭地址")
	@ApiModelProperty("家庭地址")
	private String address;
	/** 现居住地 */
	@Excel(name = "现居住地")
	@ApiModelProperty("现居住地")
	private String contact;
	/** 介绍 */
	@Excel(name = "介绍")
	@ApiModelProperty("介绍")
	private String introduce;
	/** 兴趣爱好 */
	@Excel(name = "兴趣爱好")
	@ApiModelProperty("兴趣爱好")
	private String interest;
	/** 用户积分 */
	@Excel(name = "用户积分")
	@ApiModelProperty("用户积分")
	private Long integral;
	/** 状态 Y 有效 N 无效 */
	@Excel(name = "状态 Y 有效 N 无效")
	@ApiModelProperty("状态 Y 有效 N 无效")
	private String status;
	private Date createTime;
	private Date updateTime;
}
