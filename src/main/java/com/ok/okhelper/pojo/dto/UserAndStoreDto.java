package com.ok.okhelper.pojo.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.util.Date;

/*
*Author:zhangxin_an
*Description:
*Data:Created in 19:54 2018/4/17
*/
@Data
public class UserAndStoreDto {
	
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
	 * 店铺名称
	 */
	@NotNull(message = "store_name不能为null")
	@Column(name = "storeName")
	private String storeName;
	
	/**
	 * 店铺地址
	 */
	@NotNull(message = "store_address不能为null")
	@Column(name = "storeAddress")
	private String storeAddress;
	
	/**
	 * 店铺图像
	 */
	@Column(name = "store_photo")
	private String storePhoto;

	/**
	 * 店铺联系电话
	 */
	@Column(name = "store_phone")
	private String storePhone;
	
	/**
	 * 描述
	 */
	private String description;
	
	/**
	 * 操作者
	 */
	private Long operator;
	
	/**
	 * 负责人
	 */
	@Column(name = "leader_id")
	private Long leaderId;
	
}
