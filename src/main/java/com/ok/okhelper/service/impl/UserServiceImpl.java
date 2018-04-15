package com.ok.okhelper.service.impl;

import com.ok.okhelper.common.ServerResponse;
import com.ok.okhelper.dao.RoleMapper;
import com.ok.okhelper.dao.UserMapper;
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
//			return ServerResponse.createByErrorCodeMessage(401, "");
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

        String token = JWTUtil.sign(userId, userName, inPassword, permissionArrays, storeId);

        userVo.setToken(token);

        return ServerResponse.createBySuccess(userVo);
    }


}
