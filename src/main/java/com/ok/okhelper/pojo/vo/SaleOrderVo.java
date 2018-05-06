package com.ok.okhelper.pojo.vo;

import com.ok.okhelper.pojo.po.SaleOrderDetail;
import lombok.Data;

import java.util.List;

/**
 * Author: zc
 * Date: 2018/5/6
 * Description:
 */
@Data
public class SaleOrderVo {
    private List<SaleOrderDetail> saleOrderDetails;
}
