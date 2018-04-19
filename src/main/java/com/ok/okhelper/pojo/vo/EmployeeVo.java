package com.ok.okhelper.pojo.vo;

import com.ok.okhelper.pojo.bo.RoleBo;
import com.ok.okhelper.pojo.po.Role;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

/*
*Author:zhangxin_an
*Description:查看员工信息
*Data:Created in 16:55 2018/4/19
*/
@Data
public class EmployeeVo {
	
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
	
	
	private List<RoleBo> roleList;
	
	
}
