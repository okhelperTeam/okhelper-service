package com.ok.okhelper.pojo.po;

import java.util.Date;
import javax.persistence.*;

@Table(name = "storage_order")
public class StorageOrder {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Long id;

    /**
     * 入库单号
     */
    @Column(name = "order_number")
    private String orderNumber;

    /**
     * 供应商Id 
     */
    @Column(name = "supplier_id")
    private Long supplierId;

    /**
     * 入库员
     */
    private Long stockiner;

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
     * 所属商店Id 
     */
    @Column(name = "store_id")
    private Long storeId;

    public StorageOrder(Long id, String orderNumber, Long supplierId, Long stockiner, String remarks, Date createTime, Date updateTime, Long storeId) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.supplierId = supplierId;
        this.stockiner = stockiner;
        this.remarks = remarks;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.storeId = storeId;
    }

    public StorageOrder() {
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
     * 获取入库单号
     *
     * @return order_number - 入库单号
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * 设置入库单号
     *
     * @param orderNumber 入库单号
     */
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber == null ? null : orderNumber.trim();
    }

    /**
     * 获取供应商Id 
     *
     * @return supplier_id - 供应商Id 
     */
    public Long getSupplierId() {
        return supplierId;
    }

    /**
     * 设置供应商Id 
     *
     * @param supplierId 供应商Id 
     */
    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    /**
     * 获取入库员
     *
     * @return stockiner - 入库员
     */
    public Long getStockiner() {
        return stockiner;
    }

    /**
     * 设置入库员
     *
     * @param stockiner 入库员
     */
    public void setStockiner(Long stockiner) {
        this.stockiner = stockiner;
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
     * 获取所属商店Id 
     *
     * @return store_id - 所属商店Id 
     */
    public Long getStoreId() {
        return storeId;
    }

    /**
     * 设置所属商店Id 
     *
     * @param storeId 所属商店Id 
     */
    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }
}