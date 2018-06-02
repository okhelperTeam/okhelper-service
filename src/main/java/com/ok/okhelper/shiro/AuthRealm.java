package com.ok.okhelper.shiro;


import com.ok.okhelper.dao.UserMapper;
import com.ok.okhelper.common.constenum.ConstEnum;
import com.ok.okhelper.pojo.po.Permission;
import com.ok.okhelper.pojo.po.User;
import com.ok.okhelper.service.PermissionService;
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
import org.springframework.context.annotation.Lazy;

import java.util.List;

/*
 *Author:zhangxin_an，zc
 *Description:
 *Data:Created in 21:25 2018/4/9
 */
public class AuthRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    /**
     * FIXME service/realm 调用 service 缓存会失效 加Lazy 解决  其他只要有 service 调用这个service 无论走不走那里缓存都失效
     * FIXME  shiro和cache在引用service实例顺序问题，shiro引入应在cache后，
     * FIXME shiro配置文件中引用realm属性bean中引用的service采用延迟加载策略。
     */
    @Autowired
    @Lazy
    private PermissionService permissionService;

    //认证.登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {

        // 解密获得username，用于和数据库进行对比
        String token = (String) auth.getCredentials();
        String username = JWTUtil.getUsername(token);
        Long userId = JWTUtil.getUserId(token);
        Long storeId = JWTUtil.getStoreId(token);
        if (username == null || userId == null || storeId == null) {
            throw new AuthenticationException("token invalid");
        }

        User userBean = userService.findUserByUserNme((username));
        if (userBean == null) {
            throw new AuthenticationException("User didn't existed!");
        }

        if (ConstEnum.STATUSENUM_UNAVAILABLE.getCode() == userBean.getDeleteStatus()) {
            throw new AuthenticationException("当前账户不可用");
        }

        if (!JWTUtil.verify(token, userBean.getUserPassword())) {
            throw new AuthenticationException("token 失效");
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(token, token, getName());


        return info;//放入shiro.调用CredentialsMatcher检验密码
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {

        Long userId = JWTUtil.getUserId(principal.toString());

        List<Permission> addPermissions = permissionService.findAddPermission(userId);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if (CollectionUtils.isNotEmpty(addPermissions)) {
            addPermissions.forEach(permission -> info.addStringPermission(permission.getPermissionCode()));
        }

        return info;
    }


}