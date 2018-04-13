package com.ok.okhelper.service;

import com.ok.okhelper.OkhelperApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: zc
 * @description:
 * @date: 2018/4/13
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OkhelperApplication.class)
public class RedisTest {


    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void test(){
        ValueOperations<String,String> valueOperations = redisTemplate.opsForValue();
//        valueOperations.set("mykey4","random1="+Math.random());
        System.out.println(valueOperations.get("mykey4"));

    }
}
