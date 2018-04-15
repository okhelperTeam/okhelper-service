package com.ok.okhelper.controller;

import com.ok.okhelper.common.ServerResponse;
import com.ok.okhelper.pojo.dto.UserDto;
import com.ok.okhelper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
public class UserController {
    
    @Autowired
    private UserService userService;

    
    /*
    * @Author zhangxin_an 
    * @Date 2018/4/14 18:32
    * @Params [username, password]  
    * @Return com.ok.okhelper.common.ServerResponse  
    * @Description:用户登录
    */  
    @PostMapping("/user/login")
    public ServerResponse loginUser(String username, String password) {
        return userService.loginUser(username,password);
}

    /*
    * @Author zhangxin_an 
    * @Date 2018/4/15 8:27
    * @Params [userDto]  
    * @Return com.ok.okhelper.common.ServerResponse  
    * @Description:用户注册
    */  
    @PostMapping("/user/register")
    public ServerResponse register(UserDto userDto){
        
        return userService.userRegister(userDto);
    }

    @GetMapping("user/checkUserName")
    public ServerResponse checkUserName(String userName){
        return userService.checkUserName(userName);
    }
    
    


    @RequestMapping("/logout")
    public Object logOut(HttpSession session) {
//        Subject subject = SecurityUtils.getSubject();
//        subject.logout();
//        session.removeAttribute("user");
        return "登出";
    }


    //    @RequiresAuthentication
    @GetMapping("/test")
    public String test() {
        return "ok";
    }
}