package com.ok.okhelper.service.impl;

import com.ok.okhelper.bo.UserBo;
import com.ok.okhelper.dao.PermissionMapper;
import com.ok.okhelper.dao.RoleMapper;
import com.ok.okhelper.dao.UserMapper;
import com.ok.okhelper.po.Role;
import com.ok.okhelper.po.User;
import com.ok.okhelper.service.RoleService;
import com.ok.okhelper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
*Author:zhangxin_an
*Description:
*Data:Created in 21:27 2018/4/10
*/
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	

	
	@Override
	public User findUserByUserNme(String username) {
		return userMapper.findUserByUserName(username);
	}
	

}
