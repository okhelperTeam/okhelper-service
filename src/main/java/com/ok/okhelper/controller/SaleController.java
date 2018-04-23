package com.ok.okhelper.controller;

import com.ok.okhelper.common.PageModel;
import com.ok.okhelper.common.ServerResponse;
import com.ok.okhelper.exception.IllegalException;
import com.ok.okhelper.pojo.dto.SaleOrderDto;
import com.ok.okhelper.pojo.po.SalesOrder;
import com.ok.okhelper.service.SaleService;
import com.ok.okhelper.shiro.JWTUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Author: zc
 * Date: 2018/4/22
 * Description:
 */
@RestController
@Api(tags = "销售模块")
@Slf4j
public class SaleController {
    @Autowired
    private SaleService saleService;

    @GetMapping("/sale/sale_table")
    @ApiOperation(value = "获取销售历史订单", notes = "查询指定日期的销售订单列表")
    public ServerResponse<PageModel<SalesOrder>> getSaleOrderRecords(@Valid SaleOrderDto saleOrderDto, @Valid PageModel pageModel) {
        Long storeId = JWTUtil.getStoreId();
        if (null == storeId) {
            throw new IllegalException("商店Id无效");
        }
        PageModel<SalesOrder> saleOrderRecords = saleService.getSaleOrderRecords(storeId, saleOrderDto, pageModel.getPageNum(), pageModel.getLimit());
        return ServerResponse.createBySuccess(saleOrderRecords);
    }
}
