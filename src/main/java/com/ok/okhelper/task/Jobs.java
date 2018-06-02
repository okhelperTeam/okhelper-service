package com.ok.okhelper.task;

import com.ok.okhelper.dao.SaleOrderMapper;
import com.ok.okhelper.dao.StoreMapper;
import com.ok.okhelper.common.constenum.ConstEnum;
import com.ok.okhelper.pojo.po.SaleOrder;
import com.ok.okhelper.service.SaleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

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
    private SaleOrderMapper saleOrderMapper;

    @Autowired
    private SaleService saleService;

    @Autowired
    private StringRedisTemplate redisTemplate;


    //定时关单(每天凌晨关闭前一天为付款订单)
    @Scheduled(cron = "0 0 0 * * ?")
    public void HotSaleHandler() {
        log.info("-------------定时关单开始-------------");
        Example example = new Example(SaleOrder.class);
        example.createCriteria()
                .andLessThanOrEqualTo("createTime", DateUtils.addDays(new Date(), -1))
                .andEqualTo("orderStatus", ConstEnum.SALESTATUS_NOPAYMENT.getCode());

        List<SaleOrder> saleOrders = saleOrderMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(saleOrders)) {
            saleOrders.forEach(x -> saleService.closeOrder(x.getId(),true));
        }

        log.info("----------------定时关单结束----------");

    }
}
