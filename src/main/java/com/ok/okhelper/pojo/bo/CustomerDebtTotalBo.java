package com.ok.okhelper.pojo.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Author: zc
 * Date: 2018/5/6
 * Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDebtTotalBo {
    private Integer customerCount;
    private BigDecimal totalToBePaid;
}
