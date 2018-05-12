package com.ok.okhelper.pojo.po;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "sale_order")
public class SaleOrder {
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
     * 备注
     */
    private String remarks;

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
     * 更新日期
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 商店Id 
     */
    @Column(name = "store_id")
    private Long storeId;

    public SaleOrder(Long id, String orderNumber, Long customerId, Long seller, Long stockouter, BigDecimal sumPrice, BigDecimal realPay, BigDecimal discountPrice, BigDecimal toBePaid, String payType, Integer orderStatus, Integer logisticsStatus, String remarks, Date createTime, Date payTime, Date sendTime, Date successTime, Date closeTime, Date updateTime, Long storeId) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.customerId = customerId;
        this.seller = seller;
        this.stockouter = stockouter;
        this.sumPrice = sumPrice;
        this.realPay = realPay;
        this.discountPrice = discountPrice;
        this.toBePaid = toBePaid;
        this.payType = payType;
        this.orderStatus = orderStatus;
        this.logisticsStatus = logisticsStatus;
        this.remarks = remarks;
        this.createTime = createTime;
        this.payTime = payTime;
        this.sendTime = sendTime;
        this.successTime = successTime;
        this.closeTime = closeTime;
        this.updateTime = updateTime;
        this.storeId = storeId;
    }

    public SaleOrder() {
        super();
    }

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取销售单号
     *
     * @return order_number - 销售单号
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * 设置销售单号
     *
     * @param orderNumber 销售单号
     */
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber == null ? null : orderNumber.trim();
    }

    /**
     * 获取客户Id 
     *
     * @return customer_id - 客户Id 
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * 设置客户Id 
     *
     * @param customerId 客户Id 
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    /**
     * 获取销售员
     *
     * @return seller - 销售员
     */
    public Long getSeller() {
        return seller;
    }

    /**
     * 设置销售员
     *
     * @param seller 销售员
     */
    public void setSeller(Long seller) {
        this.seller = seller;
    }

    /**
     * 获取出库员
     *
     * @return stockouter - 出库员
     */
    public Long getStockouter() {
        return stockouter;
    }

    /**
     * 设置出库员
     *
     * @param stockouter 出库员
     */
    public void setStockouter(Long stockouter) {
        this.stockouter = stockouter;
    }

    /**
     * 获取订单总价 
     *
     * @return sum_price - 订单总价 
     */
    public BigDecimal getSumPrice() {
        return sumPrice;
    }

    /**
     * 设置订单总价 
     *
     * @param sumPrice 订单总价 
     */
    public void setSumPrice(BigDecimal sumPrice) {
        this.sumPrice = sumPrice;
    }

    /**
     * 获取实付款 
     *
     * @return real_pay - 实付款 
     */
    public BigDecimal getRealPay() {
        return realPay;
    }

    /**
     * 设置实付款 
     *
     * @param realPay 实付款 
     */
    public void setRealPay(BigDecimal realPay) {
        this.realPay = realPay;
    }

    /**
     * 获取优惠金额 
     *
     * @return discount_price - 优惠金额 
     */
    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    /**
     * 设置优惠金额 
     *
     * @param discountPrice 优惠金额 
     */
    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    /**
     * 获取待支付金额 （欠款金额）
     *
     * @return to_be_paid - 待支付金额 （欠款金额）
     */
    public BigDecimal getToBePaid() {
        return toBePaid;
    }

    /**
     * 设置待支付金额 （欠款金额）
     *
     * @param toBePaid 待支付金额 （欠款金额）
     */
    public void setToBePaid(BigDecimal toBePaid) {
        this.toBePaid = toBePaid;
    }

    /**
     * 获取付款方式  (1-现金, 2-支付宝，3-微信，4-刷卡  混合数字拼接)
     *
     * @return pay_type - 付款方式  (1-现金, 2-支付宝，3-微信，4-刷卡  混合数字拼接)
     */
    public String getPayType() {
        return payType;
    }

    /**
     * 设置付款方式  (1-现金, 2-支付宝，3-微信，4-刷卡  混合数字拼接)
     *
     * @param payType 付款方式  (1-现金, 2-支付宝，3-微信，4-刷卡  混合数字拼接)
     */
    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    /**
     * 获取订单状态（1-未付款，2-未付全款，3-已付款，4-交易成功，5-订单关闭）
     *
     * @return order_status - 订单状态（1-未付款，2-未付全款，3-已付款，4-交易成功，5-订单关闭）
     */
    public Integer getOrderStatus() {
        return orderStatus;
    }

    /**
     * 设置订单状态（1-未付款，2-未付全款，3-已付款，4-交易成功，5-订单关闭）
     *
     * @param orderStatus 订单状态（1-未付款，2-未付全款，3-已付款，4-交易成功，5-订单关闭）
     */
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * 获取物流状态（1-未发货 2-已发货 3-确认收货）
     *
     * @return logistics_status - 物流状态（1-未发货 2-已发货 3-确认收货）
     */
    public Integer getLogisticsStatus() {
        return logisticsStatus;
    }

    /**
     * 设置物流状态（1-未发货 2-已发货 3-确认收货）
     *
     * @param logisticsStatus 物流状态（1-未发货 2-已发货 3-确认收货）
     */
    public void setLogisticsStatus(Integer logisticsStatus) {
        this.logisticsStatus = logisticsStatus;
    }

    /**
     * 获取备注
     *
     * @return remarks - 备注
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置备注
     *
     * @param remarks 备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    /**
     * 获取创建日期(下单时间)
     *
     * @return create_time - 创建日期(下单时间)
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建日期(下单时间)
     *
     * @param createTime 创建日期(下单时间)
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取付款时间
     *
     * @return pay_time - 付款时间
     */
    public Date getPayTime() {
        return payTime;
    }

    /**
     * 设置付款时间
     *
     * @param payTime 最后一次付款时间
     */
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    /**
     * 获取出库时间
     *
     * @return send_time - 出库时间
     */
    public Date getSendTime() {
        return sendTime;
    }

    /**
     * 设置出库时间
     *
     * @param sendTime 出库时间
     */
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * 获取交易完成时间
     *
     * @return success_time - 交易完成时间
     */
    public Date getSuccessTime() {
        return successTime;
    }

    /**
     * 设置交易完成时间
     *
     * @param successTime 交易完成时间
     */
    public void setSuccessTime(Date successTime) {
        this.successTime = successTime;
    }

    /**
     * 获取交易关闭时间
     *
     * @return close_time - 交易关闭时间
     */
    public Date getCloseTime() {
        return closeTime;
    }

    /**
     * 设置交易关闭时间
     *
     * @param closeTime 交易关闭时间
     */
    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    /**
     * 获取更新日期
     *
     * @return update_time - 更新日期
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新日期
     *
     * @param updateTime 更新日期
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取商店Id 
     *
     * @return store_id - 商店Id 
     */
    public Long getStoreId() {
        return storeId;
    }

    /**
     * 设置商店Id 
     *
     * @param storeId 商店Id 
     */
    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }
}