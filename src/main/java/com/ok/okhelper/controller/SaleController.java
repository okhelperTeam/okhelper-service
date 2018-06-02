package com.ok.okhelper.controller;

import com.ok.okhelper.common.PageModel;
import com.ok.okhelper.common.ServerResponse;
import com.ok.okhelper.exception.IllegalException;
import com.ok.okhelper.common.constenum.ConstStr;
import com.ok.okhelper.pojo.dto.PaymentDto;
import com.ok.okhelper.pojo.dto.PlaceOrderDto;
import com.ok.okhelper.pojo.dto.SaleOrderDto;
import com.ok.okhelper.pojo.vo.SaleOrderVo;
import com.ok.okhelper.pojo.vo.PlaceOrderVo;
import com.ok.okhelper.service.DeliveryService;
import com.ok.okhelper.service.SaleService;
import com.ok.okhelper.shiro.JWTUtil;
import com.ok.okhelper.util.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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

    @RequiresPermissions("sale_order:view")
    @GetMapping("/sale/sale_table")
    @ApiOperation(value = "获取销售历史订单", notes = "查询指定日期的销售订单列表")
    public ServerResponse<PageModel<SaleOrderVo>> getSaleOrderRecords(@Valid SaleOrderDto saleOrderDto, @Valid PageModel pageModel) {
        PageModel<SaleOrderVo> saleOrderRecords;
        if (StringUtils.isBlank(saleOrderDto.getRange())) {
            if (saleOrderDto.getStartDate() == null || saleOrderDto.getEndDate() == null) {
                throw new IllegalException("时间参数错误");
            }
        } else {
            saleOrderDto.setEndDate(DateUtil.weeHours(new Date(),1));
            switch (saleOrderDto.getRange()) {
                case ConstStr.QUERY_RANGE_TODAY:
                    saleOrderDto.setStartDate(DateUtil.weeHours(new Date(), 0));
                    break;
                case ConstStr.QUERY_RANGE_YESTERDAY:
                    saleOrderDto.setStartDate(DateUtils.addDays(DateUtil.weeHours(new Date(), 0), -1));
                    saleOrderDto.setEndDate(DateUtils.addDays(DateUtil.weeHours(new Date(), 1), -1));
                    break;
                case ConstStr.QUERY_RANGE_THREEDAYS:
                    saleOrderDto.setStartDate(DateUtils.addDays(DateUtil.weeHours(new Date(), 0), -2));
                    break;
                case ConstStr.QUERY_RANGE_WEEK:
                    saleOrderDto.setStartDate(DateUtils.addDays(DateUtil.weeHours(new Date(), 0), -6));
                    break;
                case ConstStr.QUERY_RANGE_MONTH:
                    saleOrderDto.setStartDate(DateUtils.addDays(DateUtil.weeHours(new Date(), 0), -29));
                    break;
                default:
                    throw new IllegalException("range参数错误");
            }
        }

        saleOrderRecords = saleService.getSaleOrderRecords(saleOrderDto, pageModel);
        return ServerResponse.createBySuccess(saleOrderRecords);
    }

    @RequiresPermissions("sale_order:view")
    @GetMapping("/sale/{id}")
    @ApiOperation(value = "获取指定订单", notes = "查询指定订单")
    public ServerResponse<SaleOrderVo> getSaleOrderRecordOne(@PathVariable Long id) {
        SaleOrderVo saleOrderRecordOne = saleService.getSaleOrderRecordOne(id);
        return ServerResponse.createBySuccess(saleOrderRecordOne);
    }

    @RequiresPermissions("sale_order:add")
    @PostMapping("/sale/place_order")
    @ApiOperation(value = "下订单", notes = "只下单不付款")
    public ServerResponse<PlaceOrderVo> placeOrder(@Valid PlaceOrderDto placeOrderDto) {
        PlaceOrderVo placeOrderVo = saleService.placeOrder(JWTUtil.getStoreId(), JWTUtil.getUserId(), placeOrderDto);
        if (placeOrderVo != null) {
            saleService.recordHotSale(placeOrderDto.getPlaceOrderItemDtos());
            if (placeOrderDto.getCustomerId() != null) {
                saleService.recordCustomerScore(placeOrderDto.getCustomerId(), placeOrderDto.getSumPrice());
            }
        }
        return ServerResponse.createBySuccess(placeOrderVo);
    }


    @RequiresPermissions("sale_order:edit")
    @PostMapping("/sale/confirm_receipt/{id:\\d+}")
    @ApiOperation(value = "确认收货")
    public ServerResponse confirmReceipt(@ApiParam(value = "销售单Id") @PathVariable Long id) {
        saleService.confirmReceipt(id);
        return ServerResponse.createBySuccessMessage("确认收货成功");
    }

    @RequiresPermissions("sale_order:edit")
    @PostMapping("/sale/close_order/{id:\\d+}")
    @ApiOperation(value = "关闭订单")
    public ServerResponse closeOrder(@ApiParam(value = "销售单Id") @PathVariable Long id) {
        saleService.closeOrder(id,false);
        return ServerResponse.createBySuccessMessage("订单关闭成功");
    }

    @RequiresPermissions("sale_order:pay")
    @PostMapping("/sale/payment/{id:\\d+}")
    @ApiOperation(value = "支付", notes = "支付接口，包括还款，支持支付宝条码支付")
    public ServerResponse payment(@ApiParam(value = "销售单Id") @PathVariable Long id, PaymentDto paymentDto) {
        saleService.payment(id, paymentDto);
        return ServerResponse.createBySuccessMessage("支付成功");
    }

}
