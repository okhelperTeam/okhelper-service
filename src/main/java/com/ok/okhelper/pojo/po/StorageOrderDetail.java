package com.ok.okhelper.pojo.po;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "storage_order_detail")
public class StorageOrderDetail {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Long id;

    /**
     * 供应商Id 
     */
    @Column(name = "supplier_id")
    private Long supplierId;

    /**
     * 入库Id 
     */
    @Column(name = "storage_in_id")
    private Long storageInId;

    /**
     * 仓库Id 
     */
    @Column(name = "warehouse_id")
    private Long warehouseId;

    /**
     * 商品Id 
     */
    @Column(name = "product_id")
    private Long productId;

    /**
     * 商品数量(最小单位) 
     */
    @Column(name = "storage_count")
    private Integer storageCount;

    /**
     * 进价 (最小单位)
     */
    @Column(name = "storage_price")
    private BigDecimal storagePrice;

    /**
     * 生产日期
     */
    @Column(name = "product_date")
    private Date productDate;

    /**
     * 保质期
     */
    @Column(name = "shelf_life")
    private Integer shelfLife;

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

    public StorageOrderDetail(Long id, Long supplierId, Long storageInId, Long warehouseId, Long productId, Integer storageCount, BigDecimal storagePrice, Date productDate, Integer shelfLife, String remarks, Date createTime, Date updateTime) {
        this.id = id;
        this.supplierId = supplierId;
        this.storageInId = storageInId;
        this.warehouseId = warehouseId;
        this.productId = productId;
        this.storageCount = storageCount;
        this.storagePrice = storagePrice;
        this.productDate = productDate;
        this.shelfLife = shelfLife;
        this.remarks = remarks;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public StorageOrderDetail() {
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
     * 获取入库Id 
     *
     * @return storage_in_id - 入库Id 
     */
    public Long getStorageInId() {
        return storageInId;
    }

    /**
     * 设置入库Id 
     *
     * @param storageInId 入库Id 
     */
    public void setStorageInId(Long storageInId) {
        this.storageInId = storageInId;
    }

    /**
     * 获取仓库Id 
     *
     * @return warehouse_id - 仓库Id 
     */
    public Long getWarehouseId() {
        return warehouseId;
    }

    /**
     * 设置仓库Id 
     *
     * @param warehouseId 仓库Id 
     */
    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    /**
     * 获取商品Id 
     *
     * @return product_id - 商品Id 
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * 设置商品Id 
     *
     * @param productId 商品Id 
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * 获取商品数量(最小单位) 
     *
     * @return storage_count - 商品数量(最小单位) 
     */
    public Integer getStorageCount() {
        return storageCount;
    }

    /**
     * 设置商品数量(最小单位) 
     *
     * @param storageCount 商品数量(最小单位) 
     */
    public void setStorageCount(Integer storageCount) {
        this.storageCount = storageCount;
    }

    /**
     * 获取进价 (最小单位)
     *
     * @return storage_price - 进价 (最小单位)
     */
    public BigDecimal getStoragePrice() {
        return storagePrice;
    }

    /**
     * 设置进价 (最小单位)
     *
     * @param storagePrice 进价 (最小单位)
     */
    public void setStoragePrice(BigDecimal storagePrice) {
        this.storagePrice = storagePrice;
    }

    /**
     * 获取生产日期
     *
     * @return product_date - 生产日期
     */
    public Date getProductDate() {
        return productDate;
    }

    /**
     * 设置生产日期
     *
     * @param productDate 生产日期
     */
    public void setProductDate(Date productDate) {
        this.productDate = productDate;
    }

    /**
     * 获取保质期
     *
     * @return shelf_life - 保质期
     */
    public Integer getShelfLife() {
        return shelfLife;
    }

    /**
     * 设置保质期
     *
     * @param shelfLife 保质期
     */
    public void setShelfLife(Integer shelfLife) {
        this.shelfLife = shelfLife;
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
}