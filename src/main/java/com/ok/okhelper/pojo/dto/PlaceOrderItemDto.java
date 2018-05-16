package com.ok.okhelper.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Author: zc
 * Date: 2018/4/29
 * Description:
 */
@Data
public class PlaceOrderItemDto {
    /**
     * 商品Id
     */
    @NotNull
    @Column(name = "product_id")
    @ApiModelProperty(value = "商品Id",required = true)
    private Long productId;

    /**
     * 商品数量(最小单位)
     */
    @NotNull
    @Column(name = "sale_count")
    @ApiModelProperty(value = "下单数量",required = true)
    private Integer saleCount;

    /**
     * 商品单价 (销售时单价&最小单位)
     */
    @NotNull
    @Column(name = "sale_price")
    @ApiModelProperty(value = "下单时单价",required = true)
    private BigDecimal salePrice;


    private String productName;
}
