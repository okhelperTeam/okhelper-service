package com.ok.okhelper.shiro;


import com.ok.okhelper.bo.PermissionBo;
import com.ok.okhelper.bo.RoleBo;
import com.ok.okhelper.bo.UserBo;
import com.ok.okhelper.dao.PermissionMapper;
import com.ok.okhelper.dao.UserMapper;
import com.ok.okhelper.po.User;
import com.ok.okhelper.service.PermissionService;
import com.ok.okhelper.service.RoleService;
import com.ok.okhelper.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
*Author:zhangxin_an
*Description:
*Data:Created in 21:25 2018/4/9
*/
public class AuthRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private PermissionMapper permissionMapper;
    
    //认证.登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
    
        // 解密获得username，用于和数据库进行对比
        String token = (String) auth.getCredentials();
        String username = JWTUtil.getUsername(token);
        if (username == null) {
            throw new AuthenticationException("token invalid");
        }
    
        User userBean = userService.findUserByUserNme((username));
        if (userBean == null) {
            throw new AuthenticationException("User didn't existed!");
        }
    
        if (! JWTUtil.verify(token, username, userBean.getUserPassword())) {
            throw new AuthenticationException("Username or password error");
        }
    
    
    
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(token, token, getName());


      return info;//放入shiro.调用CredentialsMatcher检验密码
    }
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
    
        String username = JWTUtil.getUsername(principal.toString());
        String []permissions = JWTUtil.getPermissions(principal.toString());
    
    
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        
        for(String p : permissions){
            info.addStringPermission(p);
        }
        
        
//        //TODO优化
//        UserBo userBo = new UserBo();
//
//        Long userId = userMapper.findUserIdByName(username);
        
        
        
        
        
//        List<String> permissions=new ArrayList<>();
        
//        permissions = permissionMapper.findAddPermissionCode(userId);
//        List<RoleBo> roles = userBo.getRoles();
//        Set<String> roleSet = new HashSet<>();
//        for(RoleBo r:roles) {
//
//            roleSet.add(r.getName());
//
//            for(RoleBo role : roles) {
//                for(PermissionBo p : role.getPermissionList())
//                    permissions.add(p.getUrl()+":"+p.getAction());
//
//            }
//        }
//        info.addRoles(roleSet);
        return info;
    }
    
    
    
}