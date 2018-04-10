package com.ok.okhelper.shiro;


import com.ok.okhelper.bo.PermissionBo;
import com.ok.okhelper.bo.RoleBo;
import com.ok.okhelper.bo.UserBo;
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
//    private UserService userService;
    
    //认证.登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken utoken=(UsernamePasswordToken) token;//获取用户输入的token
        String username = utoken.getUsername();
        UserBo user = null;
//        User user = userService.findUserByUserNme(username);
        Object credentials = user.getPassword();
        
        //3)realmName：当前realm对象的name，调用父类的getName()方法即可
        String realmName = getName();
        ByteSource credentialsSalt = ByteSource.Util.bytes(username);//使用账号作为盐值
        
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, credentials, credentialsSalt, realmName);
        return info;//放入shiro.调用CredentialsMatcher检验密码
    }
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        
        String username = (String) getAvailablePrincipal(principal);
    
        UserBo user = null;
//        User user = userService.findUserByUserNme(username);
//        User user = CreateMap.createUser().get();
        List<String> permissions=new ArrayList<>();
        List<RoleBo> roles = user.getRoles();
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