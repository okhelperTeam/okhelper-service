package com.ok.okhelper.pojo.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

public class Product implements Serializable {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Long id;

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
     * 商品属性(使用json存储)
     */
    @Column(name = "product_attribute")
    private String productAttribute;

    /**
     * 类别Id
     */
    @Column(name = "category_id")
    private Long categoryId;

    /**
     * 可销售库存(小于等于真是库存)
     */
    @Column(name = "sales_stock")
    private Integer salesStock;

    /**
     * 规格
     */
    private String specification;

    /**
     * 规格单位
     */
    private String unit;

    /**
     * 零售价
     */
    @Column(name = "retail_price")
    private BigDecimal retailPrice;

    /**
     * 主图
     */
    @Column(name = "main_img")
    private String mainImg;

    /**
     * 副图(数组)
     */
    @Column(name = "sub_imgs")
    private String subImgs;

    /**
     * 货号
     */
    @Column(name = "article_number")
    private String articleNumber;

    /**
     * 条码
     */
    @Column(name = "bar_code")
    private String barCode;

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
     * 状态 0下架，1上架
     */
    @Column(name = "delete_status")
    private Integer deleteStatus;

    /**
     * 所属商店Id
     */
    @Column(name = "store_id")
    private Long storeId;

    public Product(Long id, String productName, String productTitle, String productAttribute, Long categoryId, Integer salesStock, String specification, String unit, BigDecimal retailPrice, String mainImg, String subImgs, String articleNumber, String barCode, Long operator, Date createTime, Date updateTime, Integer deleteStatus, Long storeId) {
        this.id = id;
        this.productName = productName;
        this.productTitle = productTitle;
        this.productAttribute = productAttribute;
        this.categoryId = categoryId;
        this.salesStock = salesStock;
        this.specification = specification;
        this.unit = unit;
        this.retailPrice = retailPrice;
        this.mainImg = mainImg;
        this.subImgs = subImgs;
        this.articleNumber = articleNumber;
        this.barCode = barCode;
        this.operator = operator;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.deleteStatus = deleteStatus;
        this.storeId = storeId;
    }

    public Product() {
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
     * 获取商品属性(使用json存储)
     *
     * @return product_attribute - 商品属性(使用json存储)
     */
    public String getProductAttribute() {
        return productAttribute;
    }

    /**
     * 设置商品属性(使用json存储)
     *
     * @param productAttribute 商品属性(使用json存储)
     */
    public void setProductAttribute(String productAttribute) {
        this.productAttribute = productAttribute == null ? null : productAttribute.trim();
    }

    /**
     * 获取类别Id
     *
     * @return category_id - 类别Id
     */
    public Long getCategoryId() {
        return categoryId;
    }

    /**
     * 设置类别Id
     *
     * @param categoryId 类别Id
     */
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 获取可销售库存(小于等于真是库存)
     *
     * @return sales_stock - 可销售库存(小于等于真是库存)
     */
    public Integer getSalesStock() {
        return salesStock;
    }

    /**
     * 设置可销售库存(小于等于真是库存)
     *
     * @param salesStock 可销售库存(小于等于真是库存)
     */
    public void setSalesStock(Integer salesStock) {
        this.salesStock = salesStock;
    }

    /**
     * 获取规格
     *
     * @return specification - 规格
     */
    public String getSpecification() {
        return specification;
    }

    /**
     * 设置规格
     *
     * @param specification 规格
     */
    public void setSpecification(String specification) {
        this.specification = specification == null ? null : specification.trim();
    }

    /**
     * 获取规格单位
     *
     * @return unit - 规格单位
     */
    public String getUnit() {
        return unit;
    }

    /**
     * 设置规格单位
     *
     * @param unit 规格单位
     */
    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    /**
     * 获取零售价
     *
     * @return retail_price - 零售价
     */
    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    /**
     * 设置零售价
     *
     * @param retailPrice 零售价
     */
    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    /**
     * 获取主图
     *
     * @return main_img - 主图
     */
    public String getMainImg() {
        return mainImg;
    }

    /**
     * 设置主图
     *
     * @param mainImg 主图
     */
    public void setMainImg(String mainImg) {
        this.mainImg = mainImg == null ? null : mainImg.trim();
    }

    /**
     * 获取副图(数组)
     *
     * @return sub_imgs - 副图(数组)
     */
    public String getSubImgs() {
        return subImgs;
    }

    /**
     * 设置副图(数组)
     *
     * @param subImgs 副图(数组)
     */
    public void setSubImgs(String subImgs) {
        this.subImgs = subImgs == null ? null : subImgs.trim();
    }

    /**
     * 获取货号
     *
     * @return article_number - 货号
     */
    public String getArticleNumber() {
        return articleNumber;
    }

    /**
     * 设置货号
     *
     * @param articleNumber 货号
     */
    public void setArticleNumber(String articleNumber) {
        this.articleNumber = articleNumber == null ? null : articleNumber.trim();
    }

    /**
     * 获取条码
     *
     * @return bar_code - 条码
     */
    public String getBarCode() {
        return barCode;
    }

    /**
     * 设置条码
     *
     * @param barCode 条码
     */
    public void setBarCode(String barCode) {
        this.barCode = barCode == null ? null : barCode.trim();
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
     * 获取状态 0下架，1上架
     *
     * @return delete_status - 状态 0下架，1上架
     */
    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    /**
     * 设置状态 0下架，1上架
     *
     * @param deleteStatus 状态 0下架，1上架
     */
    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
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