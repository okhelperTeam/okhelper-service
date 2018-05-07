package com.ok.okhelper.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.math.BigDecimal;

/**
 * Author: zc
 * Date: 2018/5/7
 * Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleOrderItemVo {
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
}
