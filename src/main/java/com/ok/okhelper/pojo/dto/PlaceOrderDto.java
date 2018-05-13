package com.ok.okhelper.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * Author: zc
 * Date: 2018/4/28
 * Description:下单dto
 */
@Data
public class PlaceOrderDto {

    /**
     * 客户Id
     */
    @ApiModelProperty(value = "客户Id")
    private Long customerId;

    /**
     * 订单总价
     */
    @NotNull
    @Column(name = "sum_price")
    @ApiModelProperty(value = "订单总金额",required = true)
    private BigDecimal sumPrice;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remarks;


//    /**
//     * 实付款
//     */
//    @NotNull
//    @Column(name = "real_pay")
//    @ApiModelProperty(value = "实付金额",required = true)
//    private BigDecimal realPay;
//
    /**
     * 优惠金额
     */
    @Column(name = "discount_price")
    @ApiModelProperty(value = "优惠金额")
    private BigDecimal discountPrice;
//
//    /**
//     * 付款方式  (1-现金, 2-支付宝，3-微信，4-刷卡)
//     */
//    @NotNull
//    @Column(name = "pay_type")
//    @ApiModelProperty(value = "支付方式(1-现金,2-支付宝,3-微信,4-刷卡;注:混合方式拼接成 现金+微信-->1,3 )", required = true)
//    private String payType;
//
//    /**
//     * 订单状态（1-未付款，2-未付全款，3-已付款，4-交易成功，5-订单关闭）
//     */
//    @ApiModelProperty(hidden = true)
//    @Column(name = "order_status")
//    private Integer orderStatus;


    //销售单详情
    List<PlaceOrderItemDto> placeOrderItemDtos;

}
