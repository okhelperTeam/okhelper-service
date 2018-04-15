package com.ok.okhelper.shiro;


import com.ok.okhelper.dao.PermissionMapper;
import com.ok.okhelper.dao.UserMapper;
import com.ok.okhelper.pojo.po.User;
import com.ok.okhelper.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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

        if (!JWTUtil.verify(token, username, userBean.getUserPassword())) {
            throw new AuthenticationException("token 失效");
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(token, token, getName());


        return info;//放入shiro.调用CredentialsMatcher检验密码
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {

        Long userId = JWTUtil.getUserId(principal.toString());

        List<String> addPermissionCode = permissionMapper.findAddPermissionCode(userId);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        if (CollectionUtils.isNotEmpty(addPermissionCode)) {
            info.addStringPermissions(addPermissionCode);
        }


//        //TODO优化
//        UserVo userBo = new UserVo();
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