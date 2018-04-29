package com.ok.okhelper.pojo.dto;

import com.ok.okhelper.pojo.po.SalesOrderDetail;
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
     * 销售单号
     */
    @Column(name = "order_number")
    @ApiModelProperty(hidden = true)
    private String orderNumber;

    /**
     * 客户Id
     */
    @ApiModelProperty(value = "客户Id")
    private Long customerId;

    /**
     * 销售员
     */
    @ApiModelProperty(value = "销售员Id")
    private Long seller;

    /**
     * 订单总价
     */
    @NotNull
    @Column(name = "sum_price")
    @ApiModelProperty(value = "订单总金额",required = true)
    private BigDecimal sumPrice;

    /**
     * 实付款
     */
    @NotNull
    @Column(name = "real_pay")
    @ApiModelProperty(value = "实付金额",required = true)
    private BigDecimal realPay;

    /**
     * 优惠金额
     */
    @NotNull
    @Column(name = "discount_price")
    @ApiModelProperty(value = "优惠金额",required = true)
    private BigDecimal discountPrice;

    /**
     * 待支付金额 （欠款金额）
     */
    @NotNull
    @Column(name = "to_be_paid")
    @ApiModelProperty(value = "欠款金额",required = true)
    private BigDecimal toBePaid;

    /**
     * 付款方式  (1-现金, 2-支付宝，3-微信，4-刷卡)
     */
    @NotNull
    @Column(name = "pay_type")
    @ApiModelProperty(value = "支付方式(1-现金,2-支付宝,3-微信,4-刷卡;注:混合方式拼接成 现金+微信-->13 )" ,required = true)
    private Integer payType;

    /**
     * 订单状态（1-未付款，2-未付全款，3-已付款，4-交易成功，5-订单关闭）
     */
    @ApiModelProperty(hidden = true)
    @Column(name = "order_status")
    private Integer orderStatus;

    /**
     * 物流状态（1-未发货 2-已发货 3-确认收货）
     */
    @ApiModelProperty(hidden = true)
    @Column(name = "logistics_status")
    private Integer logisticsStatus;

    /**
     * 商店Id
     */
    @ApiModelProperty(hidden = true)
    @Column(name = "store_id")
    private Long storeId;


    //销售单详情
    List<PlaceOrderItemDto> placeOrderItemDtos;

}
