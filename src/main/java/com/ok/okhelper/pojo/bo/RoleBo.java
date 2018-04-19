package com.ok.okhelper.pojo.bo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

/*
*Author:zhangxin_an
*Description:返回员工角色信息
*Data:Created in 16:59 2018/4/19
*/
@Data
public class RoleBo {
	
	/**
	 * 主键
	 */
	@Id
	private Long id;
	/**
	 * 角色名称
	 */
	@Column(name = "role_name")
	private String roleName;
	
	/**
	 * 描述
	 */
	private String description;
	
	
	
	
	
	/**
	 * 状态 0废除，1激活
	 */
	@Column(name = "delete_status")
	private Integer deleteStatus;
	
}
