package com.ok.okhelper.pojo.po;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

public class Goods {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Long id;

    /**
     * 商品名
     */
    @Column(name = "goods_name")
    private String goodsName;

    /**
     * 主标题
     */
    @Column(name = "goods_title")
    private String goodsTitle;

    /**
     * 副标题
     */
    @Column(name = "goods_subtitle")
    private String goodsSubtitle;

    /**
     * 类别Id
     */
    @Column(name = "category_id")
    private Long categoryId;

    /**
     * 规格
     */
    @Column(name = "goods_spec_number")
    private String goodsSpecNumber;

    /**
     * 规格单位
     */
    @Column(name = "goods_unit")
    private String goodsUnit;

    /**
     * 价格
     */
    @Column(name = "goods_retail_price")
    private BigDecimal goodsRetailPrice;

    /**
     * 主图
     */
    @Column(name = "goods_photo")
    private String goodsPhoto;

    /**
     * 副图
     */
    @Column(name = "goods_sub_photo")
    private String goodsSubPhoto;

    /**
     * 条码
     */
    @Column(name = "goods_bar_code")
    private String goodsBarCode;

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

    public Goods(Long id, String goodsName, String goodsTitle, String goodsSubtitle, Long categoryId, String goodsSpecNumber, String goodsUnit, BigDecimal goodsRetailPrice, String goodsPhoto, String goodsSubPhoto, String goodsBarCode, Long operator, Date createTime, Date updateTime, Integer deleteStatus, Long storeId) {
        this.id = id;
        this.goodsName = goodsName;
        this.goodsTitle = goodsTitle;
        this.goodsSubtitle = goodsSubtitle;
        this.categoryId = categoryId;
        this.goodsSpecNumber = goodsSpecNumber;
        this.goodsUnit = goodsUnit;
        this.goodsRetailPrice = goodsRetailPrice;
        this.goodsPhoto = goodsPhoto;
        this.goodsSubPhoto = goodsSubPhoto;
        this.goodsBarCode = goodsBarCode;
        this.operator = operator;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.deleteStatus = deleteStatus;
        this.storeId = storeId;
    }

    public Goods() {
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
     * @return goods_name - 商品名
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * 设置商品名
     *
     * @param goodsName 商品名
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    /**
     * 获取主标题
     *
     * @return goods_title - 主标题
     */
    public String getGoodsTitle() {
        return goodsTitle;
    }

    /**
     * 设置主标题
     *
     * @param goodsTitle 主标题
     */
    public void setGoodsTitle(String goodsTitle) {
        this.goodsTitle = goodsTitle == null ? null : goodsTitle.trim();
    }

    /**
     * 获取副标题
     *
     * @return goods_subtitle - 副标题
     */
    public String getGoodsSubtitle() {
        return goodsSubtitle;
    }

    /**
     * 设置副标题
     *
     * @param goodsSubtitle 副标题
     */
    public void setGoodsSubtitle(String goodsSubtitle) {
        this.goodsSubtitle = goodsSubtitle == null ? null : goodsSubtitle.trim();
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
     * 获取规格
     *
     * @return goods_spec_number - 规格
     */
    public String getGoodsSpecNumber() {
        return goodsSpecNumber;
    }

    /**
     * 设置规格
     *
     * @param goodsSpecNumber 规格
     */
    public void setGoodsSpecNumber(String goodsSpecNumber) {
        this.goodsSpecNumber = goodsSpecNumber == null ? null : goodsSpecNumber.trim();
    }

    /**
     * 获取规格单位
     *
     * @return goods_unit - 规格单位
     */
    public String getGoodsUnit() {
        return goodsUnit;
    }

    /**
     * 设置规格单位
     *
     * @param goodsUnit 规格单位
     */
    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit == null ? null : goodsUnit.trim();
    }

    /**
     * 获取价格
     *
     * @return goods_retail_price - 价格
     */
    public BigDecimal getGoodsRetailPrice() {
        return goodsRetailPrice;
    }

    /**
     * 设置价格
     *
     * @param goodsRetailPrice 价格
     */
    public void setGoodsRetailPrice(BigDecimal goodsRetailPrice) {
        this.goodsRetailPrice = goodsRetailPrice;
    }

    /**
     * 获取主图
     *
     * @return goods_photo - 主图
     */
    public String getGoodsPhoto() {
        return goodsPhoto;
    }

    /**
     * 设置主图
     *
     * @param goodsPhoto 主图
     */
    public void setGoodsPhoto(String goodsPhoto) {
        this.goodsPhoto = goodsPhoto == null ? null : goodsPhoto.trim();
    }

    /**
     * 获取副图
     *
     * @return goods_sub_photo - 副图
     */
    public String getGoodsSubPhoto() {
        return goodsSubPhoto;
    }

    /**
     * 设置副图
     *
     * @param goodsSubPhoto 副图
     */
    public void setGoodsSubPhoto(String goodsSubPhoto) {
        this.goodsSubPhoto = goodsSubPhoto == null ? null : goodsSubPhoto.trim();
    }

    /**
     * 获取条码
     *
     * @return goods_bar_code - 条码
     */
    public String getGoodsBarCode() {
        return goodsBarCode;
    }

    /**
     * 设置条码
     *
     * @param goodsBarCode 条码
     */
    public void setGoodsBarCode(String goodsBarCode) {
        this.goodsBarCode = goodsBarCode == null ? null : goodsBarCode.trim();
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