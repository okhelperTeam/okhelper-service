package com.ok.okhelper.bo;

import lombok.Data;

import java.util.List;

/*
*Author:zhangxin_an
*Description:
*Data:Created in 9:14 2018/4/10
*/
@Data

public class RoleBo {
	private String id;
	private String name;
	private List<PermissionBo> permissionList;
	public RoleBo(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public RoleBo(){
	
	}
}
