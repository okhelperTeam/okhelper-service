package com.ok.okhelper.pojo.vo;

import com.ok.okhelper.common.PageModel;
import com.ok.okhelper.pojo.po.SalesOrder;
import lombok.Data;

/**
 * Author: zc
 * Date: 2018/4/23
 * Description:
 */
@Data
public class SaleOrderRrecordVo {

    //总销售额
    private String totalSales;

    private PageModel<SalesOrder> pageModel;
}
