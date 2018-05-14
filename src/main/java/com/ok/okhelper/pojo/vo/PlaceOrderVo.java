package com.ok.okhelper.pojo.vo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Author: zc
 * Date: 2018/4/30
 * Description:
 */
@Data
public class PlaceOrderVo {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Long id;

    /**
     * 销售单号
     */
    @Column(name = "order_number")
    private String orderNumber;

    /**
     * 客户Id
     */
    @Column(name = "customer_id")
    private Long customerId;

    /**
     * 销售员
     */
    private Long seller;

    /**
     * 订单总价
     */
    @Column(name = "sum_price")
    private BigDecimal sumPrice;

//    /**
//     * 实付款
//     */
//    @Column(name = "real_pay")
//    private BigDecimal realPay;

    /**
     * 优惠金额
     */
    @Column(name = "discount_price")
    private BigDecimal discountPrice;

//    /**
//     * 待支付金额 （欠款金额）
//     */
//    @Column(name = "to_be_paid")
//    private BigDecimal toBePaid;

//    /**
//     * 付款方式  (1-现金, 2-支付宝，3-微信，4-刷卡)
//     */
//    @Column(name = "pay_type")
//    private String payType;

    /**
     * 订单状态（1-未付款，2-未付全款，3-已付款，4-交易成功，5-订单关闭）
     */
    @Column(name = "order_status")
    private Integer orderStatus;

    /**
     * 物流状态（1-未发货 2-已发货 3-确认收货）
     */
    @Column(name = "logistics_status")
    private Integer logisticsStatus;

    /**
     * 备注
     */
    private String remarks;

//    /**
//     * 创建日期(下单时间)
//     */
//    @Column(name = "create_time")
//    private Date createTime;

//    /**
//     * 付款时间
//     */
//    @Column(name = "pay_time")
//    private Date payTime;

    /**
     * 商店Id
     */
    @Column(name = "store_id")
    private Long storeId;
}
