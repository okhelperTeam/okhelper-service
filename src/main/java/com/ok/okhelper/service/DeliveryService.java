package com.ok.okhelper.service;

import com.ok.okhelper.common.PageModel;
import com.ok.okhelper.pojo.dto.DeliveryDto;
import com.ok.okhelper.pojo.po.SaleOrder;

/**
 * Author: zc
 * Date: 2018/5/2
 * Description:出库
 */
public interface DeliveryService {
    Long deliverGoods(DeliveryDto deliveryDto);

    void sendEmail(Long saleOrderId);

    PageModel<SaleOrder> getUnSendOrder(PageModel pageModel);

}
