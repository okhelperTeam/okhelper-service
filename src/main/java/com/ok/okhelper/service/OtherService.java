package com.ok.okhelper.service;

import com.ok.okhelper.pojo.dto.DeliveryDto;
import com.ok.okhelper.pojo.dto.PlaceOrderItemDto;
import com.ok.okhelper.pojo.po.DeliveryOrderDetail;

import java.util.List;

/**
 * Author: zc
 * Date: 2018/5/2
 * Description:一些特殊服务
 */
public interface OtherService {
    void checkAndCutStock(List<PlaceOrderItemDto> placeOrderItemDtos);

    void checkDelivery(DeliveryDto deliveryDto);

}
