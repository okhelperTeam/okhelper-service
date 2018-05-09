package com.ok.okhelper.shiro;

import com.ok.okhelper.util.PropertiesUtil;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author: zc
 * @description:RedisCache
 * @date: 2018/4/15
 */
public class RedisCache<K, V> implements Cache<K, V> {

    private long expireTime = Long.parseLong(PropertiesUtil.getProperty("myShiro.cache.expireTime"));// 缓存的超时时间，单位为s

    private RedisTemplate<K, V> redisTemplate;// 通过构造方法注入该对象

    public RedisCache() {
        super();
    }

    public RedisCache(long expireTime, RedisTemplate<K, V> redisTemplate) {
        super();
        this.expireTime = expireTime;
        this.redisTemplate = redisTemplate;
    }

    public RedisCache(RedisTemplate<K, V> redisTemplate) {
        super();
        this.redisTemplate = redisTemplate;
    }

    /**
     * 通过key来获取对应的缓存对象
     * 通过源码我们可以发现，shiro需要的key的类型为Object，V的类型为AuthorizationInfo对象
     */
    @Override
    public V get(K key) throws CacheException {
        if (key instanceof SimplePrincipalCollection) {
            SimplePrincipalCollection simplePrincipalCollection = new SimplePrincipalCollection(JWTUtil.getUserId(((SimplePrincipalCollection) key).getPrimaryPrincipal().toString()), AuthRealm.class.getName());
            key = (K) simplePrincipalCollection;
        }
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 将权限信息加入缓存中
     */
    @Override
    public V put(K key, V value) throws CacheException {
        if (key instanceof SimplePrincipalCollection) {
            SimplePrincipalCollection simplePrincipalCollection = new SimplePrincipalCollection(JWTUtil.getUserId(((SimplePrincipalCollection) key).getPrimaryPrincipal().toString()), AuthRealm.class.getName());
            key = (K) simplePrincipalCollection;
        }
        redisTemplate.opsForValue().set(key, value, this.expireTime, TimeUnit.SECONDS);
        return value;
    }

    /**
     * 将权限信息从缓存中删除
     */
    @Override
    public V remove(K key) throws CacheException {
        V v = redisTemplate.opsForValue().get(key);
        redisTemplate.opsForValue().getOperations().delete(key);
        return v;
    }

    @Override
    public void clear() throws CacheException {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<K> keys() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

}