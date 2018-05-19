package com.ok.okhelper.controller;

import com.ok.okhelper.common.PageModel;
import com.ok.okhelper.common.ServerResponse;
import com.ok.okhelper.pojo.dto.DeliveryDto;
import com.ok.okhelper.pojo.po.SaleOrder;
import com.ok.okhelper.service.DeliveryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Author: zc
 * Date: 2018/5/14
 * Description:
 */
@RestController
@Api(tags = "出库模块")
public class DeliveryController {
    @Autowired
    private DeliveryService deliveryService;

    @RequiresPermissions("delivery_order:add")
    @PostMapping("/delivery/deliver_goods/{id:\\d+}")
    @ApiOperation(value = "发货/出库")
    public ServerResponse deliverGoods(@ApiParam(value = "销售单Id") @PathVariable Long id, DeliveryDto deliveryDto) {
        deliveryDto.setSaleOrderId(id);
        Long deliveryId = deliveryService.deliverGoods(deliveryDto);
        if (deliveryId != null) {
            deliveryService.sendEmail(id);
        }
        return ServerResponse.createBySuccessMessage("发货成功");
    }


    @RequiresPermissions("sale_order:unsend:view")
    @GetMapping("/delivery/unsend_orders")
    @ApiOperation(value = "查询待发货销售订单列表",notes = "排除了未付款状态")
    public ServerResponse<PageModel<SaleOrder>> getUnSendOrder(@Valid PageModel pageModel) {
        PageModel<SaleOrder> unSendOrder = deliveryService.getUnSendOrder(pageModel);
        return ServerResponse.createBySuccess(unSendOrder);
    }
}
