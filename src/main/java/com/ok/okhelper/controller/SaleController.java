package com.ok.okhelper.controller;

import com.ok.okhelper.common.PageModel;
import com.ok.okhelper.common.ServerResponse;
import com.ok.okhelper.exception.IllegalException;
import com.ok.okhelper.pojo.constenum.ConstEnum;
import com.ok.okhelper.pojo.dto.PlaceOrderDto;
import com.ok.okhelper.pojo.dto.SaleOrderDto;
import com.ok.okhelper.pojo.dto.SaleTotalVo;
import com.ok.okhelper.pojo.po.SalesOrder;
import com.ok.okhelper.service.SaleService;
import com.ok.okhelper.shiro.JWTUtil;
import com.ok.okhelper.until.DateUntil;
import com.ok.okhelper.until.NumberGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Author: zc
 * Date: 2018/4/22
 * Description:
 */
@RestController
@Api(tags = "销售模块")
@Slf4j
public class SaleController {
    @Autowired
    private SaleService saleService;

    @GetMapping("/sale/sale_table")
    @ApiOperation(value = "获取销售历史订单", notes = "查询指定日期的销售订单列表")
    public ServerResponse<PageModel<SalesOrder>> getSaleOrderRecords(@Valid SaleOrderDto saleOrderDto, @Valid PageModel pageModel) {
        Long storeId = JWTUtil.getStoreId();
        if (null == storeId) {
            throw new IllegalException("商店Id无效");
        }
        PageModel<SalesOrder> saleOrderRecords = saleService.getSaleOrderRecords(storeId, saleOrderDto, pageModel.getPageNum(), pageModel.getLimit());
        return ServerResponse.createBySuccess(saleOrderRecords);
    }

    @GetMapping("/sale/today")
    @ApiOperation(value = "获取当天销售汇总", notes = "查询当天成交笔数和销售总金额")
    public ServerResponse<SaleTotalVo> getTodaySales() {
        Long storeId = JWTUtil.getStoreId();
        SaleTotalVo saleTotalVo =
                saleService.getSaleTotalVo(storeId, DateUntil.weeHours(new Date(), 0), DateUntil.weeHours(new Date(), 1));
        return ServerResponse.createBySuccess(saleTotalVo);
    }

    @PostMapping("/sale/place_order")
    @ApiOperation(value = "下订单", notes = "下单并付款")
    public ServerResponse placeOrder(@Valid PlaceOrderDto placeOrderDto) {

        saleService.placeOrder(JWTUtil.getStoreId(),JWTUtil.getUserId(),placeOrderDto);

        return null;
    }

}
