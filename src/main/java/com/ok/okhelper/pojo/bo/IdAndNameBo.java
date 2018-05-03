package com.ok.okhelper.pojo.bo;

import lombok.Data;

/*
*Author:zhangxin_an
*Description:id和name
*Data:Created in 10:20 2018/5/3
*/
@Data
public class IdAndNameBo {
	private Long id;
	private String name;
	public IdAndNameBo(){
	
	}
	
	public IdAndNameBo(Long id, String name) {
		this.id = id;
		this.name = name;
	}
}
