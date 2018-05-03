package com.ok.okhelper.pojo.po;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "sale_order_detail")
public class SaleOrderDetail {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Long id;

    /**
     * 销售Id 
     */
    @Column(name = "sale_order_id")
    private Long saleOrderId;

    /**
     * 商品Id 
     */
    @Column(name = "product_id")
    private Long productId;

    /**
     * 商品名
     */
    @Column(name = "product_name")
    private String productName;

    /**
     * 商品标题
     */
    @Column(name = "product_title")
    private String productTitle;

    /**
     * 商品主图
     */
    @Column(name = "main_img")
    private String mainImg;

    /**
     * 商品数量(最小单位)
     */
    @Column(name = "sale_count")
    private Integer saleCount;

    /**
     * 商品单价 (销售时单价&最小单位)
     */
    @Column(name = "sale_price")
    private BigDecimal salePrice;

    /**
     * 备注
     */
    private String remarks;

    public SaleOrderDetail(Long id, Long saleOrderId, Long productId, String productName, String productTitle, String mainImg, Integer saleCount, BigDecimal salePrice, String remarks) {
        this.id = id;
        this.saleOrderId = saleOrderId;
        this.productId = productId;
        this.productName = productName;
        this.productTitle = productTitle;
        this.mainImg = mainImg;
        this.saleCount = saleCount;
        this.salePrice = salePrice;
        this.remarks = remarks;
    }

    public SaleOrderDetail() {
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
     * 获取销售Id 
     *
     * @return sale_order_id - 销售Id 
     */
    public Long getSaleOrderId() {
        return saleOrderId;
    }

    /**
     * 设置销售Id 
     *
     * @param saleOrderId 销售Id 
     */
    public void setSaleOrderId(Long saleOrderId) {
        this.saleOrderId = saleOrderId;
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
     * 获取商品名
     *
     * @return product_name - 商品名
     */
    public String getProductName() {
        return productName;
    }

    /**
     * 设置商品名
     *
     * @param productName 商品名
     */
    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    /**
     * 获取商品标题
     *
     * @return product_title - 商品标题
     */
    public String getProductTitle() {
        return productTitle;
    }

    /**
     * 设置商品标题
     *
     * @param productTitle 商品标题
     */
    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle == null ? null : productTitle.trim();
    }

    /**
     * 获取商品主图
     *
     * @return main_img - 商品主图
     */
    public String getMainImg() {
        return mainImg;
    }

    /**
     * 设置商品主图
     *
     * @param mainImg 商品主图
     */
    public void setMainImg(String mainImg) {
        this.mainImg = mainImg == null ? null : mainImg.trim();
    }

    /**
     * 获取商品数量(最小单位)
     *
     * @return sale_count - 商品数量(最小单位)
     */
    public Integer getSaleCount() {
        return saleCount;
    }

    /**
     * 设置商品数量(最小单位)
     *
     * @param saleCount 商品数量(最小单位)
     */
    public void setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
    }

    /**
     * 获取商品单价 (销售时单价&最小单位)
     *
     * @return sale_price - 商品单价 (销售时单价&最小单位)
     */
    public BigDecimal getSalePrice() {
        return salePrice;
    }

    /**
     * 设置商品单价 (销售时单价&最小单位)
     *
     * @param salePrice 商品单价 (销售时单价&最小单位)
     */
    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
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
}