package com.ok.okhelper.pojo.po;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "sales_order_detail")
public class SalesOrderDetail {
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
     * 销售Id
     */
    @Column(name = "sales_order_id")
    private Long salesOrderId;

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
     * 商品数量(最小单位)
     */
    private Integer number;

    /**
     * 商品单价 (最小单位)
     */
    private BigDecimal price;

    /**
     * 生产日期
     */
    @Column(name = "goods_birthday")
    private Date goodsBirthday;

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

    public SalesOrderDetail(Long id, Long supplierId, Long salesOrderId, Long warehouseId, Long goodsId, Integer number, BigDecimal price, Date goodsBirthday, String remarks, Date createTime, Date updateTime) {
        this.id = id;
        this.supplierId = supplierId;
        this.salesOrderId = salesOrderId;
        this.warehouseId = warehouseId;
        this.goodsId = goodsId;
        this.number = number;
        this.price = price;
        this.goodsBirthday = goodsBirthday;
        this.remarks = remarks;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public SalesOrderDetail() {
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
     * 获取销售Id
     *
     * @return sales_order_id - 销售Id
     */
    public Long getSalesOrderId() {
        return salesOrderId;
    }

    /**
     * 设置销售Id
     *
     * @param salesOrderId 销售Id
     */
    public void setSalesOrderId(Long salesOrderId) {
        this.salesOrderId = salesOrderId;
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
     * 获取商品数量(最小单位)
     *
     * @return number - 商品数量(最小单位)
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * 设置商品数量(最小单位)
     *
     * @param number 商品数量(最小单位)
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * 获取商品单价 (最小单位)
     *
     * @return price - 商品单价 (最小单位)
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置商品单价 (最小单位)
     *
     * @param price 商品单价 (最小单位)
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
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