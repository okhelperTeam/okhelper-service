package com.ok.okhelper.shiro;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: zc
 * @description: Shiro缓存帮助类
 * @date: 2018/4/16
 */

@Component
public class ShiroCacheHelper {

    @Autowired
    private RedisShiroCacheManager redisShiroCacheManager;

    /**
     * @Author zc
     * @Date 2018/4/18 上午12:54
     * @Param [userId]
     * @Return void
     * @Description: 根据userId清空用户权限缓存 请不要直接调用该方法而是去调用 PermissionService 中的更新用户/清空用户权限 权限方法
     */
//    public void clearAuthorizationCache(Long userId) {
//        SimplePrincipalCollection simplePrincipalCollection = new SimplePrincipalCollection(userId, AuthRealm.class.getName());
//        Cache<Object, Object> cache = redisShiroCacheManager.getCache(AuthRealm.class + ".authorizationCache");
//        cache.remove(simplePrincipalCollection);
//    }


    /**
     * @Author zc
     * @Date 2018/4/18 上午8:30
     * @Param []
     * @Return void
     * @Description: 清除当前用户Token认证缓存
     */
    public void clearCurrentAuthenticationCache() {
        Cache<Object, Object> cache = redisShiroCacheManager.getCache(AuthRealm.class + ".authenticationCache");
        cache.remove(JWTUtil.getToken());
    }
}
