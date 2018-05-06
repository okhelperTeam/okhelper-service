package com.ok.okhelper.pojo.bo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Author: zc
 * Date: 2018/5/6
 * Description:
 */
@Data
public class CustomerDebtTotalBo {
    private Integer customerCount;
    private BigDecimal totalToBePaid;
}
