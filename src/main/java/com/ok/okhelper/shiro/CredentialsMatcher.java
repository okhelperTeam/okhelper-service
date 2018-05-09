package com.ok.okhelper.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/*
*Author:zhangxin_an
*Description:
*Data:Created in 21:50 2018/4/9
*/
public class CredentialsMatcher extends SimpleCredentialsMatcher {
    
    
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {

        
        return super.doCredentialsMatch(token,info);
}
    
}