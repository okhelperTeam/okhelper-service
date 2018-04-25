package com.ok.okhelper.pojo.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

/*
*Author:zhangxin_an
*Description:更新个人信息
*Data:Created in 9:08 2018/4/25
*/
public class MyInfoDto {
	
	
	/**
	 * 登录账号
	 */
	@ApiModelProperty(value = "用户名")
	private String userName;
	
	/**
	 * 登录密码
	 */
	
	@ApiModelProperty(value = "登陆密码")
	private String userPassword;
	
	/**
	 * 密保问题
	 */
	@ApiModelProperty(value = "密保问题")
	private String passProblem;
	
	/**
	 * 密保答案
	 */
	@ApiModelProperty(value = "密保答案")
	private String passAnswer;
	
	/**
	 * 昵称
	 */
	
	@ApiModelProperty(value = "昵称")
	private String userNick;
	
	/**
	 * 头像
	 */
	
	@ApiModelProperty(value = "头像")
	private String userAvatar;
	
	/**
	 * 邮箱
	 */
	
	@ApiModelProperty(value = "邮箱")
	private String userEmail;
	
	/**
	 * 性别
	 */
	@ApiModelProperty(value = "性别")
	private String userSex;
	
	/**
	 * 生日
	 */
	@ApiModelProperty(value = "生日")
	private Date userBirthday;
	
	
	
}
