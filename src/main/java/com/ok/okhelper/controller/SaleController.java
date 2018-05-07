package com.ok.okhelper.controller;

import com.ok.okhelper.common.PageModel;
import com.ok.okhelper.common.ServerResponse;
import com.ok.okhelper.exception.IllegalException;
import com.ok.okhelper.pojo.constenum.ConstStr;
import com.ok.okhelper.pojo.dto.DeliveryDto;
import com.ok.okhelper.pojo.dto.PaymentDto;
import com.ok.okhelper.pojo.dto.PlaceOrderDto;
import com.ok.okhelper.pojo.dto.SaleOrderDto;
import com.ok.okhelper.pojo.vo.SaleOrderVo;
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
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
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
    public ServerResponse<PageModel<SaleOrderVo>> getSaleOrderRecords(@Valid SaleOrderDto saleOrderDto, @Valid PageModel pageModel) {
        PageModel<SaleOrderVo> saleOrderRecords;
        if (StringUtils.isBlank(saleOrderDto.getRange())) {
            if (saleOrderDto.getStartDate() == null || saleOrderDto.getEndDate() == null) {
                throw new IllegalException("参数错误");
            }
        } else {
            saleOrderDto.setEndDate(DateUntil.weeHours(new Date(),1));
            switch (saleOrderDto.getRange()) {
                case ConstStr.QUERY_RANGE_TODAY:
                    saleOrderDto.setStartDate(DateUntil.weeHours(new Date(), 0));
                    break;
                case ConstStr.QUERY_RANGE_YESTERDAY:
                    saleOrderDto.setStartDate(DateUtils.addDays(DateUntil.weeHours(new Date(), 0), -1));
                    saleOrderDto.setEndDate(DateUtils.addDays(DateUntil.weeHours(new Date(), 1), -1));
                    break;
                case ConstStr.QUERY_RANGE_THREEDAYS:
                    saleOrderDto.setStartDate(DateUtils.addDays(DateUntil.weeHours(new Date(), 0), -2));
                    break;
                case ConstStr.QUERY_RANGE_WEEK:
                    saleOrderDto.setStartDate(DateUtils.addDays(DateUntil.weeHours(new Date(), 0), -6));
                    break;
                case ConstStr.QUERY_RANGE_MONTH:
                    saleOrderDto.setStartDate(DateUtils.addDays(DateUntil.weeHours(new Date(), 0), -29));
                    break;
                default:
                    throw new IllegalException("range参数错误");
            }
        }

        saleOrderRecords = saleService.getSaleOrderRecords(saleOrderDto, pageModel);
        return ServerResponse.createBySuccess(saleOrderRecords);
    }

    @GetMapping("/sale/today_total")
    @ApiOperation(value = "获取当天销售汇总", notes = "查询当天成交笔数和销售总金额")
    public ServerResponse<SaleTotalVo> getTodaySales() {
        Long storeId = JWTUtil.getStoreId();
        SaleTotalVo saleTotalVo =
                saleService.getSaleTotalVo(storeId, DateUntil.weeHours(new Date(), 0), DateUntil.weeHours(new Date(),1));
        return ServerResponse.createBySuccess(saleTotalVo);
    }

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

    @PostMapping("/sale/deliver_goods/{saleOrderId}")
    @ApiOperation(value = "发货/出库")
    public ServerResponse deliverGoods(@PathVariable Long saleOrderId, DeliveryDto deliveryDto) {
        deliveryDto.setSaleOrderId(saleOrderId);
        Long deliveryId = deliveryService.deliverGoods(deliveryDto);
        if (deliveryId != null) {
            deliveryService.sendEmail(saleOrderId);
        }
        return ServerResponse.createBySuccessMessage("发货成功");
    }

    @PostMapping("/sale/confirm_receipt/{saleOrderId}")
    @ApiOperation(value = "确认收货")
    public ServerResponse confirmReceipt(@ApiParam(value = "销售单Id") @PathVariable Long saleOrderId) {
        saleService.confirmReceipt(saleOrderId);
        return ServerResponse.createBySuccessMessage("确认收货成功");
    }

    @PostMapping("/sale/close_order/{saleOrderId}")
    @ApiOperation(value = "关闭订单")
    public ServerResponse closeOrder(@ApiParam(value = "销售单Id") @PathVariable Long saleOrderId) {
        saleService.closeOrder(saleOrderId);
        return ServerResponse.createBySuccessMessage("订单关闭成功");
    }

    @PostMapping("/sale/payment/{saleOrderId}")
    @ApiOperation(value = "付款", notes = "支付接口，包括还款，未来扩展为支付宝接口")
    public ServerResponse payment(@ApiParam(value = "销售单Id") @PathVariable Long saleOrderId, PaymentDto paymentDto) {
        saleService.payment(saleOrderId, paymentDto);
        return ServerResponse.createBySuccessMessage("订单支付成功");
    }

}
