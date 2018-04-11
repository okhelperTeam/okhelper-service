package com.ok.okhelper.service.impl;

import com.ok.okhelper.bo.UserBo;
import com.ok.okhelper.dao.SysUserMapper;
import com.ok.okhelper.po.SysUser;
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
	SysUserMapper sysUserMapper;
	
	@Override
	public SysUser findUserByUserNme(Integer username) {
		
		return sysUserMapper.selectByPrimaryKey(username);
	}
	
	@Override
	public UserBo findUserRolePersmission(Integer userId) {
		return null;
	}
}
