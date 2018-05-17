package com.ok.okhelper.controller;

import com.ok.okhelper.common.PageModel;
import com.ok.okhelper.common.ServerResponse;
import com.ok.okhelper.pojo.bo.IdAndNameBo;
import com.ok.okhelper.pojo.dto.UserAndStoreDto;
import com.ok.okhelper.pojo.dto.UserDto;
import com.ok.okhelper.pojo.dto.UserUpdateDto;
import com.ok.okhelper.pojo.po.User;
import com.ok.okhelper.pojo.vo.EmployeeVo;
import com.ok.okhelper.pojo.vo.UserVo;
import com.ok.okhelper.service.UserService;
import com.ok.okhelper.shiro.JWTUtil;
import com.ok.okhelper.util.IpUtil;
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
    @ApiOperation(value = "用户登陆")
    public ServerResponse<UserVo> loginUser(@ApiParam(value = "用户名", required = true) String userName,
                                    @ApiParam(value = "密码", required = true) String userPassword, HttpServletRequest request) {
        String ip = IpUtil.getIpAddr(request);
        return userService.loginUser(userName, userPassword, ip);
    }

    /*
     * @Author zhangxin_an
     * @Date 2018/4/15 8:27
     * @Params [userDto]
     * @Return com.ok.okhelper.common.ServerResponse
     * @Description:店长注册
     */
    @PostMapping("/user/register")
    @ApiOperation(value = "店长注册", notes = "")
    @ApiResponses({@ApiResponse(code = 400, message = "注册信息有误")})
    @Transactional
    public ServerResponse register(@Valid UserAndStoreDto userAndStoreDto) {
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
    @RequiresPermissions("employee:edit")
    @PostMapping("/user/employee")
    @ApiOperation(value = "添加员工", notes = "添加员工并注册")
    @ApiResponses({@ApiResponse(code = 400, message = "所添加员工信息有误")})
    public ServerResponse<IdAndNameBo> addEmployee(@Valid UserDto userDto) {

        return userService.addEmployee(userDto);
    }
    
    @RequiresPermissions("employee:view")
    @GetMapping("user/employee")
    @ApiOperation(value = "获取员工", notes = "店长获取该店所有员工")
    public ServerResponse<PageModel<EmployeeVo>> getEmployeeList(@Valid PageModel pageModel,Integer deleteStatus) {
        PageModel<EmployeeVo> employeeVoList = userService.getEmployeeList(pageModel,deleteStatus);
        return ServerResponse.createBySuccess(employeeVoList);
    }
    
    @RequiresPermissions("employee:edit")
    @DeleteMapping("user/employee/{id}")
    @ApiOperation(value = "删除员工", notes = "数据库完全删除该员工")
    public ServerResponse deleteEmployee(@PathVariable Long id) {
        userService.deleteEmployee(id);
        return ServerResponse.createBySuccess("删除成功");
    }


    @GetMapping("user/check_username")
    @ApiOperation(value = "检查用户名")
    @ApiResponses({@ApiResponse(code = 409, message = "用户名存在")})
    public ServerResponse checkUserName(@ApiParam(value = "用户名",required = true) String userName) {
        return userService.checkUserName(userName);
    }
    
    @GetMapping("user/checkPassword")
    @ApiOperation(value = "检查密码")
    @ApiResponses({@ApiResponse(code = 409, message = "密码错误")})
    public ServerResponse checkPassword(@ApiParam(value = "旧密码",required = true) String password) {
        return userService.checkPassword(password);
    }
    
    @GetMapping("/user/me")
    @ApiOperation(value = "获取我的信息",notes = "用于检测token是否有效，并补签")
    public ServerResponse<UserVo> getMyUserInfo() {
        UserVo userInfo = userService.getUserInfo(JWTUtil.getUserId());
        return ServerResponse.createBySuccess(userInfo);
    }
    
    @GetMapping("/user/employeeInfo/{userName}")
    @ApiOperation(value = "用户名获取用户",notes = "根据用户名查询用户")
    public ServerResponse<User> getUserInfoByUserName(@PathVariable String userName) {
        User userInfo = userService.getUserInfoByUserName(userName);
        return ServerResponse.createBySuccess(userInfo);
    }
    
    

    @GetMapping("/user/phoneCode")
    @ApiOperation(value = "发短信",notes = "给该手机号发短信，10分钟失效")
    public ServerResponse sendSms(String number){
        
        userService.sendMs(number);
        return ServerResponse.createBySuccess("发送成功");
    }
    
    @PostMapping("/user/phoneLogin")
    @ApiOperation(value = "短信验证",notes = "短信登陆")
    public ServerResponse sendSms(String phone,String code){
        
        UserVo userVo = userService.verifyPhoneCode(phone,code);
        
        return ServerResponse.createBySuccess(userVo);
    }
    
    @ApiOperation(value = "修改个人信息")
    @PutMapping("/user/myInfo")
    public ServerResponse updateMyInfo(UserUpdateDto userDto){
        userService.updateMyInfo(userDto);
        return ServerResponse.createBySuccess("修改成功");
    }
    
    @ApiOperation(value = "修改员工状态")
    @GetMapping("/employee/status")
    @RequiresPermissions("employee:edit")
    public ServerResponse changeEmplyeeStatus(Long userId,Integer status){
        userService.changeEmplyeeStatus(userId,status);
        return ServerResponse.createBySuccess("修改成功");
    }
    
    
    
    
    //---------------------------------------------------------------------------------------------------------------------------------------------------------

    //暂时不用
    @ApiIgnore
    @RequiresPermissions("user/userList:get")
    @GetMapping("/user")
    public ServerResponse getUserListByStoreId(HttpServletRequest request) {
        String token = request.getHeader("token");
        return userService.getUserListByStoreId(token);
    }

    @ApiIgnore//使用该注解忽略这个API
    @RequestMapping("/logout")
    public Object logOut(HttpSession session) {
        return "登出";
    }
}
