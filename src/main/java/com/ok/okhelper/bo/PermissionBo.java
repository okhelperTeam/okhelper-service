package com.ok.okhelper.bo;

import lombok.Data;

/*
*Author:zhangxin_an
*Description:
*Data:Created in 9:15 2018/4/10
*/
@Data
public class PermissionBo {
	
	
	public PermissionBo(String id, String name, String url, String action) {
		this.id = id;
		this.name = name;
		this.url = url;
		this.action = action;
	}
	public PermissionBo(){
	
	}
	
	private String id;
	private String name;
	private String url;
	private String action;
}
