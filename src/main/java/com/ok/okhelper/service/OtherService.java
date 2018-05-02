package com.ok.okhelper.service;

import com.ok.okhelper.pojo.dto.PlaceOrderItemDto;

import java.util.List;

/**
 * Author: zc
 * Date: 2018/5/2
 * Description:一些特殊服务
 */
public interface OtherService {
    Boolean checkAndCutStock(List<PlaceOrderItemDto> placeOrderItemDtos);
}
