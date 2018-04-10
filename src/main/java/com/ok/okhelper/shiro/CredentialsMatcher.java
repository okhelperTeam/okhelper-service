package com.ok.okhelper.shiro;

import com.ok.okhelper.until.PasswordHelp;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/*
*Author:zhangxin_an
*Description:
*Data:Created in 21:50 2018/4/9
*/
public class CredentialsMatcher extends SimpleCredentialsMatcher {
    
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken utoken=(UsernamePasswordToken) token;
        //获得用户输入的密码:(可以采用加盐(salt)的方式去检验)
        String inPassword = new String(utoken.getPassword());
        Object saltPassword  = PasswordHelp.passwordSalt(utoken.getUsername(),utoken.getPassword());
        //获得数据库中的密码
        System.out.println("================输入"+saltPassword);
        System.out.println("================数据库"+info.getCredentials());
        //进行密码的比对
        
        
        return saltPassword.toString().equals(info.getCredentials().toString());
    }
    
}