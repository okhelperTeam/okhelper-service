package com.ok.okhelper.pojo.vo;

import com.ok.okhelper.pojo.po.SaleOrderDetail;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Author: zc
 * Date: 2018/5/6
 * Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleOrderVo {
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
     * 销售员
     */
    private Long seller;

    /**
     * 出库员
     */
    private Long stockouter;

    /**
     * 订单总价
     */
    @Column(name = "sum_price")
    private BigDecimal sumPrice;

    /**
     * 实付款
     */
    @Column(name = "real_pay")
    private BigDecimal realPay;

    /**
     * 优惠金额
     */
    @Column(name = "discount_price")
    private BigDecimal discountPrice;

    /**
     * 待支付金额 （欠款金额）
     */
    @Column(name = "to_be_paid")
    private BigDecimal toBePaid;

    /**
     * 付款方式  (1-现金, 2-支付宝，3-微信，4-刷卡  混合数字拼接)
     */
    @Column(name = "pay_type")
    private String payType;

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
     * 创建日期(下单时间)
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 付款时间
     */
    @Column(name = "pay_time")
    private Date payTime;

    /**
     * 出库时间
     */
    @Column(name = "send_time")
    private Date sendTime;

    /**
     * 交易完成时间
     */
    @Column(name = "success_time")
    private Date successTime;

    /**
     * 交易关闭时间
     */
    @Column(name = "close_time")
    private Date closeTime;


    /**
     * 备注
     */
    private String remarks;


    /**
     * 商铺Id
     */
    private Long storeId;

    /**
     * 产品
     */
    private Integer productCount;


    private List<SaleOrderItemVo> saleOrderItemVos;
}
