package com.ok.okhelper.service.impl;

import com.ok.okhelper.bo.UserBo;
import com.ok.okhelper.dao.UserMapper;
import com.ok.okhelper.po.User;
import com.ok.okhelper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
*Author:zhangxin_an
*Description:
*Data:Created in 21:27 2018/4/10
*/
@Service
public class SysUserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;

	@Override
	public User findUserByUserNme(Integer username) {

		return userMapper.selectByPrimaryKey(username);
	}

	@Override
	public UserBo findUserRolePersmission(Integer userId) {
		return null;
	}
}
