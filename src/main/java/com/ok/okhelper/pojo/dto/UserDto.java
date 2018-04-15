package com.ok.okhelper.pojo.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/*
*Author:zhangxin_an
*Description:
*Data:Created in 8:33 2018/4/15
*/
@Data
public class UserDto {
	
	
	/**
	 * 登录账号
	 */
	private String userName;
	
	/**
	 * 登录密码
	 */
	private String userPassword;
	
	/**
	 * 密保问题
	 */
	private String passProblem;
	
	/**
	 * 密保答案
	 */
	private String passAnswer;
	
	/**
	 * 昵称
	 */
	private String userNick;
	
	/**
	 * 头像
	 */
	private String userAvatar;
	
	/**
	 * 邮箱
	 */
	private String userEmail;
	
	/**
	 * 性别
	 */
	private String userSex;
	
	/**
	 * 生日
	 */
	private Date userBirthday;
	
	/**
	 * 状态 0废除，1激活
	 */
	@Column(name = "delete_status")
	private String deleteStatus;
	
	/**
	 * 操作者
	 */
	private Long operator;

}
