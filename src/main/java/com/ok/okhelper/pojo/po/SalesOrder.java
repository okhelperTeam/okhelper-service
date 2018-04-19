package com.ok.okhelper.pojo.po;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "sales_order")
public class SalesOrder {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Long id;

    /**
     * 出库单号
     */
    @Column(name = "order_number")
    private String orderNumber;

    /**
     * 客户Id
     */
    @Column(name = "customer_id")
    private Long customerId;

    /**
     * 创建者(出库人Id )
     */
    private Long operator;

    /**
     * 商品总价
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
     * 待支付金额
     */
    @Column(name = "to_be_paid")
    private BigDecimal toBePaid;

    /**
     * 付款方式
     */
    @Column(name = "pay_type")
    private String payType;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 创建日期
     */
    @Column(name = "create_time")
    private Date createTime;

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

    public SalesOrder(Long id, String orderNumber, Long customerId, Long operator, BigDecimal sumPrice, BigDecimal realPay, BigDecimal discountPrice, BigDecimal toBePaid, String payType, String remarks, Date createTime, Date updateTime, Long storeId) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.customerId = customerId;
        this.operator = operator;
        this.sumPrice = sumPrice;
        this.realPay = realPay;
        this.discountPrice = discountPrice;
        this.toBePaid = toBePaid;
        this.payType = payType;
        this.remarks = remarks;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.storeId = storeId;
    }

    public SalesOrder() {
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
     * 获取出库单号
     *
     * @return order_number - 出库单号
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * 设置出库单号
     *
     * @param orderNumber 出库单号
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
     * 获取创建者(出库人Id )
     *
     * @return operator - 创建者(出库人Id )
     */
    public Long getOperator() {
        return operator;
    }

    /**
     * 设置创建者(出库人Id )
     *
     * @param operator 创建者(出库人Id )
     */
    public void setOperator(Long operator) {
        this.operator = operator;
    }

    /**
     * 获取商品总价
     *
     * @return sum_price - 商品总价
     */
    public BigDecimal getSumPrice() {
        return sumPrice;
    }

    /**
     * 设置商品总价
     *
     * @param sumPrice 商品总价
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
     * 获取待支付金额
     *
     * @return to_be_paid - 待支付金额
     */
    public BigDecimal getToBePaid() {
        return toBePaid;
    }

    /**
     * 设置待支付金额
     *
     * @param toBePaid 待支付金额
     */
    public void setToBePaid(BigDecimal toBePaid) {
        this.toBePaid = toBePaid;
    }

    /**
     * 获取付款方式
     *
     * @return pay_type - 付款方式
     */
    public String getPayType() {
        return payType;
    }

    /**
     * 设置付款方式
     *
     * @param payType 付款方式
     */
    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
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
     * 获取创建日期
     *
     * @return create_time - 创建日期
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建日期
     *
     * @param createTime 创建日期
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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