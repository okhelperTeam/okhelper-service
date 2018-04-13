package com.ok.okhelper.pojo.bo;

import com.ok.okhelper.po.Permission;
import com.ok.okhelper.po.Role;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

/*
*Author:zhangxin_an
*Description:
*Data:Created in 22:32 2018/4/13
*/
@Data
public class UserBo {
	/**
	 * 主键
	 */
	@Id
	private Long id;
	
	/**
	 * 登录账号
	 */
	@Column(name = "user_name")
	private String userName;
	

	/**
	 * 密保问题
	 */
	@Column(name = "pass_problem")
	private String passProblem;
	

	/**
	 * 昵称
	 */
	@Column(name = "user_nick")
	private String userNick;
	
	/**
	 * 头像
	 */
	@Column(name = "user_avatar")
	private String userAvatar;
	
	/**
	 * 邮箱
	 */
	@Column(name = "user_email")
	private String userEmail;
	
	/**
	 * 性别
	 */
	@Column(name = "user_sex")
	private String userSex;
	
	/**
	 * 生日
	 */
	@Column(name = "user_birthday")
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
	
	private List<Role> roleList;
	
	private List<String> permissionCodes;
	
	private String token;
	
	//所属商店Id
	private List<Long> storeIds;
}
