package com.ok.okhelper.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: zc
 * @description:Shiro缓存帮助类
 * @date: 2018/4/16
 */

@Component
public class ShiroCacheHelper {

    @Autowired
    private RedisShiroCacheManager redisShiroCacheManager;


    /**
     * 根据userId清空缓存重的权限
     *
     * @param userId
     */
    public void clearAuthorizationCache(Long userId) {
        SimplePrincipalCollection simplePrincipalCollection = new SimplePrincipalCollection(userId, AuthRealm.class.getName());
        Cache<Object, Object> cache = redisShiroCacheManager.getCache(AuthRealm.class + ".authorizationCache");
        cache.remove(simplePrincipalCollection);
    }


    /**
     * 清除当前用户Token认证缓存
     */
    public void clearCurrentAuthenticationCache() {
        Cache<Object, Object> cache = redisShiroCacheManager.getCache(AuthRealm.class + ".authenticationCache");
        cache.remove(JWTUtil.getToken());
    }
}
