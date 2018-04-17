package com.ok.okhelper.pojo.constenum;

/*
*Author:zhangxin_an
*Description:
*Data:Created in 9:26 2018/4/16
*/

import lombok.Data;

public enum ConstEnum {
	STATUSENUM_AVAILABLE("可用",1),
	STATUSENUM_UNAVAILABLE("不可用",0),
	ROLE_STOREMANAGER("店长",2);
	
	
	
	// 成员变量
	private String description;
	private int code;
	
	 ConstEnum(String description, int code) {
		this.description = description;
		this.code = code;
	}
	
	public void setCode(int code) {
		this.code = code;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	public int getCode() {
		return code;
	}
}