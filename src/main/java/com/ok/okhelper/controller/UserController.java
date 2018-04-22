package com.ok.okhelper.controller;

import com.ok.okhelper.common.ServerResponse;
import com.ok.okhelper.pojo.dto.UserAndRoleDto;
import com.ok.okhelper.pojo.dto.UserAndStoreDto;
import com.ok.okhelper.pojo.dto.UserDto;
import com.ok.okhelper.pojo.vo.EmployeeVo;
import com.ok.okhelper.service.UserService;
import com.ok.okhelper.until.IpUtil;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/*
 *Author:zhangxin_an
 *Description:
 *Data:Created in 21:56 2018/4/9
 */
@Api(tags = "用户模块")
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
    @ApiOperation(value = "用户登陆", notes = "根据我的userId获取我的权限列表")
    public ServerResponse loginUser(@ApiParam(value = "用户名", required = true) String username,
                                    @ApiParam(value = "密码", required = true) String password, HttpServletRequest request) {
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
    @ApiOperation(value = "店长注册", notes = "")
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
    @ApiOperation(value = "添加员工", notes = "添加员工并注册")
    public ServerResponse addEmployee(UserDto userDto) {

        return userService.addEmployee(userDto);
    }

    @GetMapping("/employee")
    public ServerResponse getEmployeeList() {
        List<EmployeeVo> employeeVoList = userService.getEmployeeList();
        return ServerResponse.createBySuccess(employeeVoList);
    }


    @GetMapping("user/check_username")
    @ApiOperation(value = "检查用户名")
    @ApiResponses({
            @ApiResponse(code = 200, message = "用户名不存在"),
            @ApiResponse(code = 400, message = "用户名存在")})
    public ServerResponse checkUserName(String userName) {
        return userService.checkUserName(userName);
    }


    @RequiresPermissions("user/userList:get")
    @GetMapping("/user")
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
    @ApiOperation(value = "变更角色")
    public ServerResponse changeRoleFromUser(@Valid UserAndRoleDto userAndRoleDto) {
        return userService.changeRole(userAndRoleDto);
    }

    @ApiIgnore//使用该注解忽略这个API
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