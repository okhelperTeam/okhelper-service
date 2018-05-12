package com.ok.okhelper.pojo.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Author: zc
 * Date: 2018/5/6
 * Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDebtBo {

    private Long saleOrderId;

    private String saleOrderNumber;

    /**
     * 待支付金额 （欠款金额）
     */
    @Column(name = "to_be_paid")
    private BigDecimal toBePaid;


    private Date placeOrderTime;

    /**
     * 客户Id
     */
    @Column(name = "customer_id")
    private Long customerId;

    /**
     * 客户姓名
     */
    @Column(name = "customer_name")
    private String customerName;

    /**
     * 客户店名
     */
    @Column(name = "customer_store_name")
    private String customerStoreName;

    /**
     * 客户手机号
     */
    @Column(name = "customer_phone")
    private String customerPhone;

    /**
     * 邮箱
     */
    @Column(name = "customer_email")
    private String customerEmail;

    /**
     * 客户积分(每次消费取整)
     */
    @Column(name = "customer_score")
    private Integer customerScore;

    /**
     * vip级别 0->5 默认0
     */
    @Column(name = "customer_level")
    private Integer customerLevel;

    /**
     * 客户地址
     */
    @Column(name = "customer_address")
    private String customerAddress;
}
