package com.ok.okhelper.config;

/**
 * @author: zc
 * @description: Redis缓存管理器配置
 * @date: 2018/4/15
 */

import com.ok.okhelper.util.PropertiesUtil;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.time.Duration;
import java.util.Objects;

import static java.util.Collections.singletonMap;
import static org.springframework.data.redis.cache.RedisCacheConfiguration.defaultCacheConfig;


@Configuration
@EnableCaching//启用缓存，这个注解很重要；
public class RedisCacheConfig {

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        RedisCacheConfiguration redisCacheConfiguration
                = RedisCacheConfiguration.defaultCacheConfig().entryTtl
                (Duration.ofSeconds(Long.parseLong(Objects.requireNonNull(PropertiesUtil.getProperty("cacheable.redis.ttl")))));

        RedisCacheManager cm = RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(redisCacheConfiguration)
                .withInitialCacheConfigurations(singletonMap("predefined", defaultCacheConfig().disableCachingNullValues()))
                .transactionAware()
                .build();

        return cm;
    }

}