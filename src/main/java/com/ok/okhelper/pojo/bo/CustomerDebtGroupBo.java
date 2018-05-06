package com.ok.okhelper.pojo.bo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Author: zc
 * Date: 2018/5/6
 * Description:
 */
@Data
public class CustomerDebtGroupBo {
    private Long customerId;
    private BigDecimal sum_to_be_paid;
}
