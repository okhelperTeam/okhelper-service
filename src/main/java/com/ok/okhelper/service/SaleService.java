package com.ok.okhelper.service;

import com.ok.okhelper.common.PageModel;
import com.ok.okhelper.pojo.dto.PaymentDto;
import com.ok.okhelper.pojo.dto.PlaceOrderDto;
import com.ok.okhelper.pojo.dto.PlaceOrderItemDto;
import com.ok.okhelper.pojo.dto.SaleOrderDto;
import com.ok.okhelper.pojo.vo.SaleOrderVo;
import com.ok.okhelper.pojo.vo.SaleTotalVo;
import com.ok.okhelper.pojo.po.SaleOrder;
import com.ok.okhelper.pojo.vo.PlaceOrderVo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Author: zc
 * Date: 2018/4/23
 * Description:
 */
public interface SaleService {
    PageModel<SaleOrderVo> getSaleOrderRecords(SaleOrderDto saleOrderDto, PageModel pageModel);

    SaleOrderVo getSaleOrderRecordOne(Long id);

    PlaceOrderVo placeOrder(Long storeId, Long seller, PlaceOrderDto placeOrderDto);

    void recordHotSale(List<PlaceOrderItemDto> placeOrderItemDtos);

    void recordCustomerScore(Long customerId, BigDecimal sumPrice);

    void confirmReceipt(Long saleOrderId);

    void closeOrder(Long saleOrderId,boolean isTsk);

    void payment(Long saleOrderId, PaymentDto paymentDto);
}
