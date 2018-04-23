package com.ok.okhelper.service;

import com.ok.okhelper.common.PageModel;
import com.ok.okhelper.pojo.dto.SaleOrderDto;
import com.ok.okhelper.pojo.po.SalesOrder;

/**
 * Author: zc
 * Date: 2018/4/23
 * Description:
 */
public interface SaleService {
    PageModel<SalesOrder> getSaleOrderRecords(Long storeId, SaleOrderDto saleOrderDto, Integer pageNum, Integer limit);
}
