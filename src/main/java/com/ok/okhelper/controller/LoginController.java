package com.ok.okhelper.controller;

import com.ok.okhelper.common.ServerResponse;
import com.ok.okhelper.dao.UserMapper;
import com.ok.okhelper.po.User;
import com.ok.okhelper.service.UserService;
import com.ok.okhelper.shiro.JWTUtil;
import com.ok.okhelper.until.PasswordHelp;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
*Author:zhangxin_an
*Description:
*Data:Created in 21:56 2018/4/9
*/
@RestController
public class LoginController {
    
    @Autowired
    private UserService userService;
    
    @RequestMapping("/login")
    public String login() {
        
        return "login";
    }
    
    
    
  
    @RequestMapping("/loginUser")
    public Object loginUser(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        

        return userService.getToken(username,password);
    
    
    }

    
    @RequestMapping("/logOut")
    public Object logOut(HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
//        session.removeAttribute("user");
        return "登出";
    }
}