package com.ok.okhelper.pojo.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Author: zc
 * Date: 2018/4/30
 * Description:
 */
@Data
public class HotSaleVo {
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

    /**
     * 销量
     */
    private Integer salesVolume;
}
