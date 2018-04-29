package com.ok.okhelper.pojo.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Author: zc
 * Date: 2018/4/28
 * Description:
 */
@Data
public class SaleTotalVo {

    //销售总笔数
    private Integer saleCount;

    //总销售额
    private BigDecimal totalSales;
}
