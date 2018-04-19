package com.ok.okhelper.controller;

import com.ok.okhelper.common.ServerResponse;
import com.ok.okhelper.dao.UserMapper;
import com.ok.okhelper.pojo.dto.UserAndRoleDto;
import com.ok.okhelper.pojo.dto.UserAndStoreDto;
import com.ok.okhelper.pojo.dto.UserDto;
import com.ok.okhelper.pojo.vo.EmployeeVo;
import com.ok.okhelper.service.UserService;
import com.ok.okhelper.until.IpUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/*
 *Author:zhangxin_an
 *Description:
 *Data:Created in 21:56 2018/4/9
 */
@RestController
public class UserController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

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
    @PostMapping("/user/employee")
    public ServerResponse addEmployee(UserDto userDto) {

        return userService.addEmployee(userDto);
    }
    
    @GetMapping("/employee")
    public ServerResponse getEmployeeList(){
        List<EmployeeVo> employeeVoList = userService.getEmployeeList();
        return ServerResponse.createBySuccess(employeeVoList);
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

    /**
     * @Author zc
     * @Date 2018/4/18 上午10:59
     * @Param [userAndRoleDto]
     * @Return com.ok.okhelper.common.ServerResponse
     * @Description: 变更角色
     */  
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
}