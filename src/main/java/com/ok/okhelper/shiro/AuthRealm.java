package com.ok.okhelper.shiro;


import com.ok.okhelper.bo.PermissionBo;
import com.ok.okhelper.bo.RoleBo;
import com.ok.okhelper.bo.UserBo;
import com.ok.okhelper.po.SysUser;
import com.ok.okhelper.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import javax.management.relation.Role;
import java.util.*;

/*
*Author:zhangxin_an
*Description:
*Data:Created in 21:25 2018/4/9
*/
public class AuthRealm extends AuthorizingRealm {
//    @Autowired
    private UserService userService;
    
    //认证.登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
    
        // 解密获得username，用于和数据库进行对比
        String token = (String) auth.getCredentials();
        String username = JWTUtil.getUsername(token);
        if (username == null) {
            throw new AuthenticationException("token invalid");
        }
    
        SysUser userBean = userService.findUserByUserNme(Integer.valueOf(username));
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
        
        //TODO优化
        UserBo userBo = userService.findUserRolePersmission(Integer.valueOf(username));
        
        
        
        
        List<String> permissions=new ArrayList<>();
        List<RoleBo> roles = userBo.getRoles();
        Set<String> roleSet = new HashSet<>();
        for(RoleBo r:roles) {
            
            roleSet.add(r.getName());
            
            for(RoleBo role : roles) {
                for(PermissionBo p : role.getPermissionList())
                    permissions.add(p.getUrl()+":"+p.getAction());
                    
            }
        }
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        info.addStringPermissions(permissions);//将权限放入shiro中.
        info.addRoles(roleSet);
        return info;
    }
    
    
    
}