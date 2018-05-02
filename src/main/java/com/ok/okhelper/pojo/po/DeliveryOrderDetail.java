package com.ok.okhelper.pojo.po;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "delivery_order_detail")
public class DeliveryOrderDetail {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Long id;

    /**
     * 出库单id
     */
    @Column(name = "delivery_order_id")
    private Long deliveryOrderId;

    /**
     * 商品ID
     */
    @Column(name = "product_id")
    private Long productId;

    /**
     * 仓库ID
     */
    @Column(name = "warehouse_id")
    private Long warehouseId;

    /**
     * 出库数量
     */
    @Column(name = "delivery_count")
    private Integer deliveryCount;

    /**
     * 生产日期
     */
    @Column(name = "product_date")
    private Date productDate;

    public DeliveryOrderDetail(Long id, Long deliveryOrderId, Long productId, Long warehouseId, Integer deliveryCount, Date productDate) {
        this.id = id;
        this.deliveryOrderId = deliveryOrderId;
        this.productId = productId;
        this.warehouseId = warehouseId;
        this.deliveryCount = deliveryCount;
        this.productDate = productDate;
    }

    public DeliveryOrderDetail() {
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
     * 获取出库单id
     *
     * @return delivery_order_id - 出库单id
     */
    public Long getDeliveryOrderId() {
        return deliveryOrderId;
    }

    /**
     * 设置出库单id
     *
     * @param deliveryOrderId 出库单id
     */
    public void setDeliveryOrderId(Long deliveryOrderId) {
        this.deliveryOrderId = deliveryOrderId;
    }

    /**
     * 获取商品ID
     *
     * @return product_id - 商品ID
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * 设置商品ID
     *
     * @param productId 商品ID
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * 获取仓库ID
     *
     * @return warehouse_id - 仓库ID
     */
    public Long getWarehouseId() {
        return warehouseId;
    }

    /**
     * 设置仓库ID
     *
     * @param warehouseId 仓库ID
     */
    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    /**
     * 获取出库数量
     *
     * @return delivery_count - 出库数量
     */
    public Integer getDeliveryCount() {
        return deliveryCount;
    }

    /**
     * 设置出库数量
     *
     * @param deliveryCount 出库数量
     */
    public void setDeliveryCount(Integer deliveryCount) {
        this.deliveryCount = deliveryCount;
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
}