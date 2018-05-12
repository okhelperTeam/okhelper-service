package com.ok.okhelper.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * Author: zc
 * Date: 2018/4/28
 * Description:
 */
@Data
@AllArgsConstructor
public class SaleTotalVo {

    //销售总笔数
    private Integer saleCount;

    //总销售额
    private BigDecimal totalSales;

    //单品数量(包含已经关闭订单)
    private Integer totalProductCount;

    private List<ProductCountMapVo> productCountMap;

    private  Integer totalSalesProductNumber;

    public SaleTotalVo(Integer saleCount, BigDecimal totalSales) {
        this.saleCount = saleCount;
        this.totalSales = totalSales;
    }
}
