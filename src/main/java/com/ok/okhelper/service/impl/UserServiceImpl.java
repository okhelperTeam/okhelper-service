package com.ok.okhelper.service.impl;

import com.ok.okhelper.bo.UserBo;
import com.ok.okhelper.common.ServerResponse;
import com.ok.okhelper.dao.PermissionMapper;
import com.ok.okhelper.dao.RoleMapper;
import com.ok.okhelper.dao.UserMapper;
import com.ok.okhelper.po.Role;
import com.ok.okhelper.po.User;
import com.ok.okhelper.service.PermissionService;
import com.ok.okhelper.service.RoleService;
import com.ok.okhelper.service.UserService;
import com.ok.okhelper.shiro.JWTUtil;
import com.ok.okhelper.until.PasswordHelp;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
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
	
	@Autowired
	private PermissionService permissionService;
	

	
	@Override
	public User findUserByUserNme(String username) {
		return userMapper.findUserByUserName(username);
	}
	
	@Override
	public ServerResponse getToken(String userName, String password) {
		
		if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
			return ServerResponse.createByErrorCodeMessage(401, "用户名或密码为空");
		}
		
		
		
		User user = findUserByUserNme(userName);
		
		if(user == null){
			return ServerResponse.createByErrorCodeMessage(401, "用户名不存在");
		}
		
		//加密
		String inPassword = PasswordHelp.passwordSalt(userName, password).toString();
		
		String dbPassword = user.getUserPassword();
		if(!dbPassword.equals(inPassword)){
			return ServerResponse.createByErrorCodeMessage(401, "密码不正确");
		}
		
		Long userId = user.getId();
		
		
		//获取用户权限
		List<String> permissionList = permissionService.findAddPermissionCode(userId);
		String [] permissionArrays = null;
		
		if(!CollectionUtils.isEmpty(permissionList)) {
			permissionArrays = permissionList.toArray(new String[permissionList.size()]);
		}
		String token = JWTUtil.sign(userName, inPassword,permissionArrays);
		
		
		
		return ServerResponse.createBySuccess(token);
	}
	
	
}
