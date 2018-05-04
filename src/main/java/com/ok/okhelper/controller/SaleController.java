package com.ok.okhelper.controller;

import com.ok.okhelper.common.PageModel;
import com.ok.okhelper.common.ServerResponse;
import com.ok.okhelper.exception.IllegalException;
import com.ok.okhelper.pojo.dto.DeliveryDto;
import com.ok.okhelper.pojo.dto.PlaceOrderDto;
import com.ok.okhelper.pojo.dto.SaleOrderDto;
import com.ok.okhelper.pojo.vo.SaleTotalVo;
import com.ok.okhelper.pojo.po.SaleOrder;
import com.ok.okhelper.pojo.vo.PlaceOrderVo;
import com.ok.okhelper.service.DeliveryService;
import com.ok.okhelper.service.SaleService;
import com.ok.okhelper.shiro.JWTUtil;
import com.ok.okhelper.until.DateUntil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

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

    @Autowired
    private DeliveryService deliveryService;

    @GetMapping("/sale/sale_table")
    @ApiOperation(value = "获取销售历史订单", notes = "查询指定日期的销售订单列表")
    public ServerResponse<PageModel<SaleOrder>> getSaleOrderRecords(@Valid SaleOrderDto saleOrderDto, @Valid PageModel pageModel) {
        Long storeId = JWTUtil.getStoreId();
        if (null == storeId) {
            throw new IllegalException("商店Id无效");
        }
        PageModel<SaleOrder> saleOrderRecords = saleService.getSaleOrderRecords(storeId, saleOrderDto, pageModel.getPageNum(), pageModel.getLimit(), pageModel.getOrderBy());
        return ServerResponse.createBySuccess(saleOrderRecords);
    }

    @GetMapping("/sale/today_total")
    @ApiOperation(value = "获取当天销售汇总", notes = "查询当天成交笔数和销售总金额")
    public ServerResponse<SaleTotalVo> getTodaySales() {
        Long storeId = JWTUtil.getStoreId();
        SaleTotalVo saleTotalVo =
                saleService.getSaleTotalVo(storeId, DateUntil.weeHours(new Date(), 0), DateUntil.weeHours(new Date(), 1));
        return ServerResponse.createBySuccess(saleTotalVo);
    }

    @PostMapping("/sale/place_order")
    @ApiOperation(value = "下订单", notes = "下单并付款")
    public ServerResponse<PlaceOrderVo> placeOrder(@Valid PlaceOrderDto placeOrderDto) {
        PlaceOrderVo placeOrderVo = saleService.placeOrder(JWTUtil.getStoreId(), JWTUtil.getUserId(), placeOrderDto);
        if (placeOrderDto != null) {
            saleService.recordHotSale(placeOrderDto.getPlaceOrderItemDtos());
        }
        return ServerResponse.createBySuccess(placeOrderVo);
    }


    @PostMapping("/sale/deliver_goods")
    @ApiOperation(value = "发货/出库")
    public ServerResponse deliverGoods(DeliveryDto deliveryDto) {
        Long deliveryId = deliveryService.deliverGoods(deliveryDto);
        if (deliveryId != null) {
            deliveryService.sendEmail(deliveryDto.getSaleOrderId());
        }
        return ServerResponse.createBySuccessMessage("发货成功");
    }


    @PostMapping("/sale/confirm_receipt/{saleOrderId}")
    @ApiOperation(value = "确认收货")
    public ServerResponse confirmReceipt(@ApiParam(value = "销售单Id") @PathVariable Long saleOrderId) {
        saleService.confirmReceipt(saleOrderId);
        return ServerResponse.createBySuccessMessage("确认收货");
    }

}
