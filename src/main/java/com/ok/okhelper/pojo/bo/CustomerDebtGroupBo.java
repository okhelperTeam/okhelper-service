package com.ok.okhelper.pojo.bo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Author: zc
 * Date: 2018/5/6
 * Description:
 */
@Data
@AllArgsConstructor
public class CustomerDebtGroupBo {
    private Long customerId;
    private BigDecimal sumToBePaid;
}
