package com.ok.okhelper.service;

import org.springframework.stereotype.Service;

import java.util.List;

/*
*Author:zhangxin_an
*Description:
*Data:Created in 17:07 2018/4/11
*/
@Service
public interface SysPermissionService {
	
	//获取权限URL
	List<String> findAllPermissionUrl();
}
