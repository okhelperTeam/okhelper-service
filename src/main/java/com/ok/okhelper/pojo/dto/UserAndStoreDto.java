package com.ok.okhelper.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/*
*Author:zhangxin_an
*Description:店长注册
*Data:Created in 19:54 2018/4/17
*/
@Data
public class UserAndStoreDto {
	
	/**
	 * 登录账号
	 */
	@ApiModelProperty(value = "用户名(自动复制一份到店长手机号)", required = true)
	@NotNull(message = "用户名不能为空")
	private String userName;
	
	/**
	 * 登录密码
	 */
	
	@ApiModelProperty(value = "登陆密码",required = true)
	@NotNull(message = "密码不能为空")
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
	
	/**
	 * 状态 0废除，1激活
	 */
	@ApiModelProperty(value = "状态")
	private String deleteStatus;
	/**
	 * 店铺名称
	 */
	@NotNull(message = "store_name不能为null")
	@ApiModelProperty(value = "店铺名",required = true)
	private String storeName;
	
	/**
	 * 店铺地址
	 */
	@ApiModelProperty(value = "店铺地址")
	private String storeAddress;
	
	/**
	 * 店铺图像
	 */
	@ApiModelProperty(value = "店铺图像")
	private String storePhoto;

	/**
	 * 店铺联系电话
	 */
	@ApiModelProperty(value = "店铺联系电话",required = true)
	private String storePhone;
	
	/**
	 * 描述
	 */
	@ApiModelProperty(value = "店铺描述")
	private String description;
	
	/**
	 * 负责人
	 */
	@ApiModelProperty(value = "店铺所属")
	private Long leaderId;
	
}
