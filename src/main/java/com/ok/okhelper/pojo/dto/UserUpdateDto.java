package com.ok.okhelper.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/*
 *Author:zhangxin_an
 *Description:
 *Data:Created in 14:31 2018/5/14
 */
@Data
public class UserUpdateDto {
	/**
	 * 登录密码
	 */
	
	@ApiModelProperty(value = "新密码")
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
	 * 手机号
	 */
	@ApiModelProperty(value = "手机号")
	private String userPhone;
	
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
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@ApiModelProperty(value = "生日")
	private Date userBirthday;
	
}
