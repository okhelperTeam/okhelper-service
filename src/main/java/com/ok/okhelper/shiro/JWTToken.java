package com.ok.okhelper.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/*
*Author:zhangxin_an
*Description:
*Data:Created in 15:13 2018/4/11
*/
public class JWTToken extends UsernamePasswordToken {
	
	// 密钥
	private String token;
	
	public JWTToken(String token) {
		this.token = token;
	}
	
	@Override
	public Object getPrincipal() {
		return token;
	}
	
	@Override
	public Object getCredentials() {
		return token;
	}
}