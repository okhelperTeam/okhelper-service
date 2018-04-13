package com.ok.okhelper.service.impl;

import com.ok.okhelper.dao.PermissionMapper;
import com.ok.okhelper.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
*Author:zhangxin_an
*Description:
*Data:Created in 17:08 2018/4/11
*/
@Service
public class PermissionServiceImpl implements PermissionService {
	@Autowired
	private PermissionMapper permissionMapper;
	
	@Override
	public List<String> findAddPermissionCode(Long userId) {
		return permissionMapper.findAddPermissionCode(userId);
	}
}
