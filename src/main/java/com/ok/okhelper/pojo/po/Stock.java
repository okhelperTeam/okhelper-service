package com.ok.okhelper.pojo.po;

import java.util.Date;
import javax.persistence.*;

public class Stock {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Long id;

    /**
     * 仓库Id
     */
    @Column(name = "warehouse_id")
    private Long warehouseId;

    /**
     * 商品Id
     */
    @Column(name = "goods_id")
    private Long goodsId;

    /**
     * 生产日期
     */
    @Column(name = "goods_birthday")
    private Date goodsBirthday;

    /**
     * 保质期
     */
    @Column(name = "goods_shelf_life")
    private Integer goodsShelfLife;

    /**
     * 库存数量
     */
    @Column(name = "stock_number")
    private Long stockNumber;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 操作者
     */
    private Long operator;

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

    public Stock(Long id, Long warehouseId, Long goodsId, Date goodsBirthday, Integer goodsShelfLife, Long stockNumber, String remarks, Long operator, Date createTime, Date updateTime, Long storeId) {
        this.id = id;
        this.warehouseId = warehouseId;
        this.goodsId = goodsId;
        this.goodsBirthday = goodsBirthday;
        this.goodsShelfLife = goodsShelfLife;
        this.stockNumber = stockNumber;
        this.remarks = remarks;
        this.operator = operator;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.storeId = storeId;
    }

    public Stock() {
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
     * @return goods_id - 商品Id
     */
    public Long getGoodsId() {
        return goodsId;
    }

    /**
     * 设置商品Id
     *
     * @param goodsId 商品Id
     */
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 获取生产日期
     *
     * @return goods_birthday - 生产日期
     */
    public Date getGoodsBirthday() {
        return goodsBirthday;
    }

    /**
     * 设置生产日期
     *
     * @param goodsBirthday 生产日期
     */
    public void setGoodsBirthday(Date goodsBirthday) {
        this.goodsBirthday = goodsBirthday;
    }

    /**
     * 获取保质期
     *
     * @return goods_shelf_life - 保质期
     */
    public Integer getGoodsShelfLife() {
        return goodsShelfLife;
    }

    /**
     * 设置保质期
     *
     * @param goodsShelfLife 保质期
     */
    public void setGoodsShelfLife(Integer goodsShelfLife) {
        this.goodsShelfLife = goodsShelfLife;
    }

    /**
     * 获取库存数量
     *
     * @return stock_number - 库存数量
     */
    public Long getStockNumber() {
        return stockNumber;
    }

    /**
     * 设置库存数量
     *
     * @param stockNumber 库存数量
     */
    public void setStockNumber(Long stockNumber) {
        this.stockNumber = stockNumber;
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
     * 获取操作者
     *
     * @return operator - 操作者
     */
    public Long getOperator() {
        return operator;
    }

    /**
     * 设置操作者
     *
     * @param operator 操作者
     */
    public void setOperator(Long operator) {
        this.operator = operator;
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