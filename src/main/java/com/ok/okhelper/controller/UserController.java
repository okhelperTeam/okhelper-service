package com.ok.okhelper.controller;

import com.ok.okhelper.common.ServerResponse;
import com.ok.okhelper.exception.IllegalException;
import com.ok.okhelper.pojo.dto.UserAndRoleDto;
import com.ok.okhelper.pojo.dto.UserAndStoreDto;
import com.ok.okhelper.pojo.dto.UserDto;
import com.ok.okhelper.pojo.vo.UserVo;
import com.ok.okhelper.service.UserService;
import com.ok.okhelper.shiro.JWTUtil;
import com.ok.okhelper.shiro.RedisShiroCacheManager;
import com.ok.okhelper.until.IpUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.UserDataHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.logging.Logger;

/*
 *Author:zhangxin_an
 *Description:
 *Data:Created in 21:56 2018/4/9
 */
@RestController
public class UserController {
    org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

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
    public ServerResponse loginUser(String username, String password, HttpServletRequest request) {
        String ip = IpUtil.getIpAddr(request);
        return userService.loginUser(username, password, ip);
    }

    /*
     * @Author zhangxin_an
     * @Date 2018/4/15 8:27
     * @Params [userDto]
     * @Return com.ok.okhelper.common.ServerResponse
     * @Description:店长注册
     */
    @Transactional
    @PostMapping("/user/register")
    public ServerResponse register(UserAndStoreDto userAndStoreDto) {
        userService.userRegister(userAndStoreDto);
       
        return ServerResponse.createBySuccess("注册成功");
    }
    
    /*
    * @Author zhangxin_an 
    * @Date 2018/4/17 20:47
    * @Params [userDto]  
    * @Return com.ok.okhelper.common.ServerResponse  
    * @Description:添加员工
    */  
    @RequiresPermissions("addEmployee")
    @PostMapping("/user/addEmployee")
    public ServerResponse addEmployee(UserDto userDto) {
        
        return userService.addEmployee(userDto);
    }

    @GetMapping("user/checkUserName")
    public ServerResponse checkUserName(String userName) {
        return userService.checkUserName(userName);
    }

    @RequiresPermissions("user/userList:get")
    @GetMapping("user/userList")
    public ServerResponse getUserListByStoreId(HttpServletRequest request) {
        String token = request.getHeader("token");
        return userService.getUserListByStoreId(token);
    }


    @PutMapping("/user/role")
    public ServerResponse changeRoleFromUser(@Valid UserAndRoleDto userAndRoleDto) {
        return userService.changeRole(userAndRoleDto);
    }


    @RequestMapping("/logout")
    public Object logOut(HttpSession session) {
//        Subject subject = SecurityUtils.getSubject();
//        subject.logout();
//        session.removeAttribute("user");
//        try {
//            ShiroAuthorizationHelper.clearAuthorizationInfo(JWTUtil.getToken());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        return "登出";
    }


    //    @RequiresAuthentication
    @GetMapping("/test")
    public String test() {
        return "ok";
    }
}