package com.ok.okhelper.pojo.po;

import java.util.Date;
import javax.persistence.*;

@Table(name = "delivery_order")
public class DeliveryOrder {
    /**
     * id
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
     * 销售单id
     */
    @Column(name = "sale_order_id")
    private Long saleOrderId;

    /**
     * 创建日期(出库时间)
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新日期
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 状态 0废除，1激活
     */
    @Column(name = "delete_status")
    private Integer deleteStatus;

    /**
     * 描述
     */
    private String description;

    /**
     * 出库员
     */
    private Long stockouter;

    /**
     * 商铺Id
     */
    @Column(name = "store_id")
    private Long storeId;

    public DeliveryOrder(Long id, String orderNumber, Long saleOrderId, Date createTime, Date updateTime, Integer deleteStatus, String description, Long stockouter, Long storeId) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.saleOrderId = saleOrderId;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.deleteStatus = deleteStatus;
        this.description = description;
        this.stockouter = stockouter;
        this.storeId = storeId;
    }

    public DeliveryOrder() {
        super();
    }

    /**
     * 获取id
     *
     * @return id - id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
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
     * 获取销售单id
     *
     * @return sale_order_id - 销售单id
     */
    public Long getSaleOrderId() {
        return saleOrderId;
    }

    /**
     * 设置销售单id
     *
     * @param saleOrderId 销售单id
     */
    public void setSaleOrderId(Long saleOrderId) {
        this.saleOrderId = saleOrderId;
    }

    /**
     * 获取创建日期(出库时间)
     *
     * @return create_time - 创建日期(出库时间)
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建日期(出库时间)
     *
     * @param createTime 创建日期(出库时间)
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
     * 获取状态 0废除，1激活
     *
     * @return delete_status - 状态 0废除，1激活
     */
    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    /**
     * 设置状态 0废除，1激活
     *
     * @param deleteStatus 状态 0废除，1激活
     */
    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    /**
     * 获取描述
     *
     * @return description - 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述
     *
     * @param description 描述
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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
     * 获取商铺Id
     *
     * @return store_id - 商铺Id
     */
    public Long getStoreId() {
        return storeId;
    }

    /**
     * 设置商铺Id
     *
     * @param storeId 商铺Id
     */
    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }
}