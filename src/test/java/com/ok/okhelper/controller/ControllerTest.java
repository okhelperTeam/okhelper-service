package com.ok.okhelper.controller;

import com.ok.okhelper.OkhelperApplication;
import com.ok.okhelper.common.constenum.ConstStr;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Author: zc
 * Date: 2018/4/30
 * Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OkhelperApplication.class)
@Slf4j
public class ControllerTest {
    @Autowired
    private StringRedisTemplate redisTemplate;


    @Test
    public void testZset() {
        Long storeId = Long.valueOf(3);

        String zkey_yesterday = ConstStr.HOT_SALE + ":" + storeId + ":" + DateFormatUtils.format(DateUtils.addDays(new Date(), -1), "yyyyMMdd");

        String zkey = ConstStr.HOT_SALE + ":" + storeId + ":" + DateFormatUtils.format(new Date(), "yyyyMMdd");

        //初始化新的一天
        redisTemplate.opsForZSet().add(zkey, String.valueOf((long) 0), 0);
        redisTemplate.expire(zkey, 31, TimeUnit.DAYS);
//        redisTemplate.opsForZSet().remove(zkey, "0");


//        String zkey = ConstStr.HOT_SALE + ":" + "3" + ":" + DateFormatUtils.format(DateUtils.addDays(new Date(), 0), "yyyyMMdd");
//        String zkey2 = ConstStr.HOT_SALE + ":" + "3" + ":" + DateFormatUtils.format(DateUtils.addDays(new Date(), -1), "yyyyMMdd");
//        String zkey3 = ConstStr.HOT_SALE + ":" + "3" + ":" + DateFormatUtils.format(DateUtils.addDays(new Date(), -2), "yyyyMMdd");
//
//        redisTemplate.opsForZSet().incrementScore(zkey, String.valueOf((long) 1), Double.parseDouble(RandomStringUtils.randomNumeric(2)));
//        redisTemplate.opsForZSet().incrementScore(zkey, String.valueOf((long) 2), Double.parseDouble(RandomStringUtils.randomNumeric(2)));
//        redisTemplate.opsForZSet().incrementScore(zkey, String.valueOf((long) 3), Double.parseDouble(RandomStringUtils.randomNumeric(2)));
//        redisTemplate.opsForZSet().incrementScore(zkey, String.valueOf((long) 4), Double.parseDouble(RandomStringUtils.randomNumeric(2)));
//        redisTemplate.opsForZSet().incrementScore(zkey, String.valueOf((long) 5), Double.parseDouble(RandomStringUtils.randomNumeric(2)));
//        redisTemplate.opsForZSet().incrementScore(zkey, String.valueOf((long) 6), Double.parseDouble(RandomStringUtils.randomNumeric(2)));
//        redisTemplate.opsForZSet().incrementScore(zkey, String.valueOf((long) 7), Double.parseDouble(RandomStringUtils.randomNumeric(2)));
//        redisTemplate.opsForZSet().incrementScore(zkey, String.valueOf((long) 8), Double.parseDouble(RandomStringUtils.randomNumeric(2)));
//        redisTemplate.opsForZSet().incrementScore(zkey, String.valueOf((long) 9), Double.parseDouble(RandomStringUtils.randomNumeric(2)));
//        redisTemplate.opsForZSet().incrementScore(zkey, String.valueOf((long) 10), Double.parseDouble(RandomStringUtils.randomNumeric(2)));
//        redisTemplate.opsForZSet().incrementScore(zkey, String.valueOf((long) 11), Double.parseDouble(RandomStringUtils.randomNumeric(2)));
//        redisTemplate.opsForZSet().incrementScore(zkey, String.valueOf((long) 12), Double.parseDouble(RandomStringUtils.randomNumeric(2)));
//        redisTemplate.opsForZSet().incrementScore(zkey, String.valueOf((long) 13), Double.parseDouble(RandomStringUtils.randomNumeric(2)));


//        redisTemplate.opsForZSet().incrementScore(zkey2, String.valueOf((long) 1), Double.parseDouble(RandomStringUtils.randomNumeric(2)));
//        redisTemplate.opsForZSet().incrementScore(zkey2, String.valueOf((long) 2), Double.parseDouble(RandomStringUtils.randomNumeric(2)));
//        redisTemplate.opsForZSet().incrementScore(zkey2, String.valueOf((long) 3), Double.parseDouble(RandomStringUtils.randomNumeric(2)));
//        redisTemplate.opsForZSet().incrementScore(zkey2, String.valueOf((long) 4), Double.parseDouble(RandomStringUtils.randomNumeric(2)));
//        redisTemplate.opsForZSet().incrementScore(zkey2, String.valueOf((long) 5), Double.parseDouble(RandomStringUtils.randomNumeric(2)));
//        redisTemplate.opsForZSet().incrementScore(zkey2, String.valueOf((long) 6), Double.parseDouble(RandomStringUtils.randomNumeric(2)));
//        redisTemplate.opsForZSet().incrementScore(zkey2, String.valueOf((long) 7), Double.parseDouble(RandomStringUtils.randomNumeric(2)));
//        redisTemplate.opsForZSet().incrementScore(zkey2, String.valueOf((long) 8), Double.parseDouble(RandomStringUtils.randomNumeric(2)));
//        redisTemplate.opsForZSet().incrementScore(zkey2, String.valueOf((long) 9), Double.parseDouble(RandomStringUtils.randomNumeric(2)));
//        redisTemplate.opsForZSet().incrementScore(zkey2, String.valueOf((long) 10), Double.parseDouble(RandomStringUtils.randomNumeric(2)));
//        redisTemplate.opsForZSet().incrementScore(zkey2, String.valueOf((long) 11), Double.parseDouble(RandomStringUtils.randomNumeric(2)));
//        redisTemplate.opsForZSet().incrementScore(zkey2, String.valueOf((long) 12), Double.parseDouble(RandomStringUtils.randomNumeric(2)));
//        redisTemplate.opsForZSet().incrementScore(zkey2, String.valueOf((long) 13), Double.parseDouble(RandomStringUtils.randomNumeric(2)));

//        redisTemplate.opsForZSet().incrementScore(zkey3, String.valueOf((long) 1), Double.parseDouble(RandomStringUtils.randomNumeric(2)));
//        redisTemplate.opsForZSet().incrementScore(zkey3, String.valueOf((long) 2), Double.parseDouble(RandomStringUtils.randomNumeric(2)));
//        redisTemplate.opsForZSet().incrementScore(zkey3, String.valueOf((long) 3), Double.parseDouble(RandomStringUtils.randomNumeric(2)));
//        redisTemplate.opsForZSet().incrementScore(zkey3, String.valueOf((long) 4), Double.parseDouble(RandomStringUtils.randomNumeric(2)));
//        redisTemplate.opsForZSet().incrementScore(zkey3, String.valueOf((long) 5), Double.parseDouble(RandomStringUtils.randomNumeric(2)));
//        redisTemplate.opsForZSet().incrementScore(zkey3, String.valueOf((long) 6), Double.parseDouble(RandomStringUtils.randomNumeric(2)));
//        redisTemplate.opsForZSet().incrementScore(zkey3, String.valueOf((long) 7), Double.parseDouble(RandomStringUtils.randomNumeric(2)));
//        redisTemplate.opsForZSet().incrementScore(zkey3, String.valueOf((long) 8), Double.parseDouble(RandomStringUtils.randomNumeric(2)));
//        redisTemplate.opsForZSet().incrementScore(zkey3, String.valueOf((long) 9), Double.parseDouble(RandomStringUtils.randomNumeric(2)));
//        redisTemplate.opsForZSet().incrementScore(zkey3, String.valueOf((long) 10), Double.parseDouble(RandomStringUtils.randomNumeric(2)));
//        redisTemplate.opsForZSet().incrementScore(zkey3, String.valueOf((long) 11), Double.parseDouble(RandomStringUtils.randomNumeric(2)));
//        redisTemplate.opsForZSet().incrementScore(zkey3, String.valueOf((long) 12), Double.parseDouble(RandomStringUtils.randomNumeric(2)));
//        redisTemplate.opsForZSet().incrementScore(zkey3, String.valueOf((long) 13), Double.parseDouble(RandomStringUtils.randomNumeric(2)));

//        redisTemplate.opsForZSet().remove(zkey,0,-11);

//        redisTemplate.expire(zkey,600,TimeUnit.SECONDS);

//        Set<String> top10 = redisTemplate.opsForZSet().reverseRange(zkey, 0, 9);
//
//        top10.forEach(item -> {
//            getProjectById(Long.valueOf(item));
//        });
    }

//    private void getProjectById(Long id){
//         log.debug(String.valueOf(id));
//    }


    {

//
//        //清除前一天商品只留销量前十
//        redisTemplate.opsForZSet().removeRange(zkey_yesterday,0,-11);

    }
}
