package com.ok.okhelper.pojo.bo;

import lombok.Data;

/*
*Author:zhangxin_an
*Description:查询员工信息
*Data:Created in 17:06 2018/4/19
*/
@Data
public class UserBo {
	
	public  UserBo(){
	
	}
	public UserBo(Long id, String userName, String userNick, String userAvatar, String userSex, String userPhone, Integer deleteStatus) {
		this.id = id;
		this.userName = userName;
		this.userNick = userNick;
		this.userAvatar = userAvatar;
		this.userSex = userSex;
		this.userPhone = userPhone;
		this.deleteStatus = deleteStatus;
	}
	
	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 登录账号
	 */
	private String userName;
	
	
	/**
	 * 昵称
	 */
	private String userNick;
	
	/**
	 * 头像
	 */
	private String userAvatar;
	/**
	 * 性别
	 */
	private String userSex;
	/**
	 * 手机号
	 */
	private String userPhone;
	
	/**
	 * 状态 0废除，1激活
	 */
	private Integer deleteStatus;
}
