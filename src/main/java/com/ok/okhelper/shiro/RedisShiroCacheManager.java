package com.ok.okhelper.shiro;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author: zc
 * @description:RedisShiroCacheManager
 * @date: 2018/4/15
 */
@Service
public class RedisShiroCacheManager implements CacheManager {

    @Autowired
    private RedisTemplate redisTemplate;// extends RedisTemplate<String, String>

    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
//        System.out.println("name:"+s);
        return new RedisCache<K, V>(redisTemplate);
    }
}
