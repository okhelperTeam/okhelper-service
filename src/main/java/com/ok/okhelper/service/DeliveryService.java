package com.ok.okhelper.service;

import com.ok.okhelper.pojo.dto.DeliveryDto;

/**
 * Author: zc
 * Date: 2018/5/2
 * Description:出库
 */
public interface DeliveryService {
    Long deliverGoods(DeliveryDto deliveryDto);

    void sendEmail(Long saleOrderId);

}
