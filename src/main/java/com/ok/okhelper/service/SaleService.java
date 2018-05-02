package com.ok.okhelper.service;

import com.ok.okhelper.common.PageModel;
import com.ok.okhelper.pojo.dto.PlaceOrderDto;
import com.ok.okhelper.pojo.dto.PlaceOrderItemDto;
import com.ok.okhelper.pojo.dto.SaleOrderDto;
import com.ok.okhelper.pojo.dto.SaleTotalVo;
import com.ok.okhelper.pojo.po.SalesOrder;
import com.ok.okhelper.pojo.vo.PlaceOrderVo;

import java.util.Date;
import java.util.List;

/**
 * Author: zc
 * Date: 2018/4/23
 * Description:
 */
public interface SaleService {
    PageModel<SalesOrder> getSaleOrderRecords(Long storeId, SaleOrderDto saleOrderDto, Integer pageNum, Integer limit);

    SaleTotalVo getSaleTotalVo(Long storeId,Date startDate,Date endDate);

    PlaceOrderVo placeOrder(Long storeId, Long seller, PlaceOrderDto placeOrderDto);

    void recordHotSale(List<PlaceOrderItemDto> placeOrderItemDtos);
}
