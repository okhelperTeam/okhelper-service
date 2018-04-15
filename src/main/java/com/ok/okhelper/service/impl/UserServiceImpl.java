package com.ok.okhelper.service.impl;

import com.ok.okhelper.common.ServerResponse;
import com.ok.okhelper.dao.RoleMapper;
import com.ok.okhelper.dao.UserMapper;
import com.ok.okhelper.exception.IllegalException;
import com.ok.okhelper.pojo.dto.UserDto;
import com.ok.okhelper.pojo.po.Role;
import com.ok.okhelper.pojo.po.User;
import com.ok.okhelper.pojo.vo.UserVo;
import com.ok.okhelper.service.PermissionService;
import com.ok.okhelper.service.UserService;
import com.ok.okhelper.shiro.JWTUtil;
import com.ok.okhelper.until.PasswordHelp;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.UnauthenticatedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionService permissionService;


    @Override
    public User findUserByUserNme(String username) {
        return userMapper.findUserByUserName(username);
    }

    @Override
    public ServerResponse loginUser(String userName, String password, String ip) {

        if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
            throw new UnauthenticatedException("用户名或密码为空");
        }

        User user = findUserByUserNme(userName);


        if (user == null) {
            throw new UnauthenticatedException("用户名不存在");
        }

        //加密
        String inPassword = PasswordHelp.passwordSalt(userName, password).toString();

        String dbPassword = user.getUserPassword();
        if (!dbPassword.equals(inPassword)) {
            throw new UnauthenticatedException("密码不正确");
        }

        Long userId = user.getId();

        user.setLastLoginIp(ip);
        userMapper.updateByPrimaryKeySelective(user);

        //传值给前端封装类
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);

        List<Role> roles = roleMapper.findRoleByUserId(user.getId());
        if (!CollectionUtils.isEmpty(roles)) {
            userVo.setRoleList(roles);
        }

        //获取用户权限
        List<String> permissionList = permissionService.findAddPermissionCode(userId);
        String[] permissionArrays = null;

        if (!CollectionUtils.isEmpty(permissionList)) {
            permissionArrays = permissionList.toArray(new String[permissionList.size()]);
            userVo.setPermissionCodes(permissionList);
        }

        Long storeId = userMapper.findStoreIdByUserId(userId);

        userVo.setStoreId(storeId);

        String token = JWTUtil.sign(userId, userName, inPassword, storeId);

        userVo.setToken(token);

        return ServerResponse.createBySuccess(userVo);
    }

    /*
     * @Author zhangxin_an
     * @Date 2018/4/14 18:52
     * @Params [userDto]
     * @Return com.ok.okhelper.common.ServerResponse
     * @Description:注册
     */
    @Override
    public ServerResponse userRegister(UserDto userDto) {
        logger.info("Enter userRegister" + userDto);
        if (StringUtils.isBlank(userDto.getUserName())
                && StringUtils.isBlank(userDto.getUserPassword())
                && userDto.getUserBirthday() != null) {
            new IllegalException("注册信息不完善（用户名，密码，生日不为空）");
        }

        //密码加密
        userDto.setUserPassword(PasswordHelp.passwordSalt(userDto.getUserName(), userDto.getUserPassword()));


        User user = new User();
        BeanUtils.copyProperties(userDto, user);

        try {

            userMapper.insertSelective(user);
        } catch (Exception e) {

            throw new IllegalException("注册失败");
        }

        ServerResponse serverResponse = ServerResponse.createBySuccess();

        logger.info("EXit userRegister" + userDto);

        return serverResponse;

    }

    @Override
    public ServerResponse checkUserName(String userName) {

        if (StringUtils.isBlank(userName)) {
            throw new IllegalException("用户名为空");
        }

        if (CollectionUtils.isEmpty(userMapper.checkUserName(userName))) {
            return ServerResponse.createBySuccess();
        }
        throw new IllegalException("用户名重复");
    }
}
