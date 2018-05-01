package com.ok.okhelper.task;

import com.ok.okhelper.controller.ReportController;
import com.ok.okhelper.dao.StoreMapper;
import com.ok.okhelper.pojo.constenum.ConstStr;
import com.ok.okhelper.pojo.po.Store;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Author: zc
 * Date: 2018/4/30
 * Description:定时任务
 */
@Component
@Slf4j
public class Jobs {
    @Autowired
    private StoreMapper storeMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    //每天凌晨跑
    @Scheduled(cron = "0 0 0 * * ?")
    public void HotSaleHandler() {
        log.info("HotSaleHandler定时任务开启");
        List<Store> stores = storeMapper.selectAll();
        stores.forEach(store -> {
            String zkey_yesterday = ConstStr.HOT_SALE + ":" + store.getId() + ":" + DateFormatUtils.format(DateUtils.addDays(new Date(), -1), "yyyyMMdd");
            String zkey = ConstStr.HOT_SALE + ":" + store.getId() + ":" + DateFormatUtils.format(new Date(), "yyyyMMdd");

            //初始化新的一天
            redisTemplate.opsForZSet().add(zkey, String.valueOf((long) 0), 0);
            redisTemplate.expire(zkey, 31, TimeUnit.DAYS);
            redisTemplate.opsForZSet().remove(zkey, "0");
            log.info("初始化今天热销缓存{}", zkey);

            //清除前一天商品只留销量前十
//            redisTemplate.opsForZSet().removeRange(zkey_yesterday,0,-11);
//            log.info("清除昨天热销缓存{}",zkey_yesterday);
        });

    }
}
