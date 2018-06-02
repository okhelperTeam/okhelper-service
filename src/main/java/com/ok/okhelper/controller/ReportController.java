package com.ok.okhelper.controller;

import com.ok.okhelper.common.PageModel;
import com.ok.okhelper.common.ServerResponse;
import com.ok.okhelper.exception.IllegalException;
import com.ok.okhelper.pojo.bo.CustomerDebtBo;
import com.ok.okhelper.common.constenum.ConstStr;
import com.ok.okhelper.pojo.vo.SaleTotalVo;
import com.ok.okhelper.pojo.vo.SalesVolumeVo;
import com.ok.okhelper.service.ReportService;
import com.ok.okhelper.util.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * Author: zc
 * Date: 2018/4/30
 * Description:
 */
@RestController
@Api(tags = "统计报表模块")
@Slf4j
public class ReportController {

    @Autowired
    private ReportService reportService;

    @RequiresPermissions("report:sales:view")
    @ApiOperation(value = "热/滞销商品")
    @GetMapping("/report/hot_cold_sale")
    public ServerResponse<List<SalesVolumeVo>> getHotProduct(
            @ApiParam(value = "查询范围(今天->today 三天内->threeDays 一周内->week 一个月->month)") @RequestParam(required = true) String range,
            @ApiParam(value = "true为热销；false为滞销") @RequestParam(required = true) Boolean isHot) {
        List<SalesVolumeVo> hotSaleByRedis=null;
        if(isHot){
            hotSaleByRedis=reportService.getHotSaleByRedis(range);
        }else {
            hotSaleByRedis=reportService.getUnsalableByRedis(range);
        }
        return ServerResponse.createBySuccess(hotSaleByRedis);
    }


    @RequiresPermissions("report:sales:view")
    @ApiOperation(value = "客户欠款查询")
    @GetMapping("/report/customer_debt")
    public ServerResponse<PageModel<CustomerDebtBo>> getCustomerDebt(@ApiParam(value = "查询条件(客户名字/手机号)")
                                                                     @RequestParam(required = false) String condition,
                                                                     @Valid PageModel pageModel) {
        PageModel<CustomerDebtBo> customerDebt = reportService.getCustomerDebt(condition == null ? null : condition.trim(), pageModel);
        return ServerResponse.createBySuccess(customerDebt);
    }


    @RequiresPermissions("report:sales:view")
    @ApiOperation(value = "获取销售汇总", notes = "查询指定范围销售汇总")
    @GetMapping("/sale/total")
    public ServerResponse<SaleTotalVo> getTodaySales(@ApiParam(value = "查询范围(今天->today 昨天->yesterday 三天内->threeDays 一周内->week 近30天->month)") @RequestParam(required = true) String range) {
        Date startDate = new Date();
        Date endDate = DateUtil.weeHours(new Date(), 1);
        switch (range) {
            case ConstStr.QUERY_RANGE_TODAY:
                startDate = DateUtil.weeHours(new Date(), 0);
                break;
            case ConstStr.QUERY_RANGE_YESTERDAY:
                startDate = DateUtils.addDays(DateUtil.weeHours(new Date(), 0), -1);
                endDate = DateUtils.addDays(DateUtil.weeHours(new Date(), 1), -1);
                break;
            case ConstStr.QUERY_RANGE_THREEDAYS:
                startDate = DateUtils.addDays(DateUtil.weeHours(new Date(), 0), -2);
                break;
            case ConstStr.QUERY_RANGE_WEEK:
                startDate = DateUtils.addDays(DateUtil.weeHours(new Date(), 0), -6);
                break;
            case ConstStr.QUERY_RANGE_MONTH:
                startDate = DateUtils.addDays(DateUtil.weeHours(new Date(), 0), -29);
                break;
            default:
                throw new IllegalException("range参数错误");
        }

        SaleTotalVo saleTotalVo =
                reportService.getSaleTotalVo(startDate, endDate);
        return ServerResponse.createBySuccess(saleTotalVo);
    }


}
