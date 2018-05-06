package com.ok.okhelper.controller;

import com.ok.okhelper.common.PageModel;
import com.ok.okhelper.common.ServerResponse;
import com.ok.okhelper.exception.IllegalException;
import com.ok.okhelper.pojo.bo.CustomerDebtBo;
import com.ok.okhelper.pojo.constenum.ConstStr;
import com.ok.okhelper.pojo.vo.SalesVolumeVo;
import com.ok.okhelper.pojo.po.Product;
import com.ok.okhelper.service.ProductService;
import com.ok.okhelper.service.ReportService;
import com.ok.okhelper.shiro.JWTUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

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


    @ApiOperation(value = "热销商品", notes = "获取销量前十的商品")
    @GetMapping("/report/hot_sale")
    public ServerResponse<List<SalesVolumeVo>> getHotProduct(
            @ApiParam(value = "查询范围(今天->today 三天内->threeDays 一周内->week 一个月->month)") @RequestParam(required = true) String range) {
        List<SalesVolumeVo> hotSaleByRedis = reportService.getHotSaleByRedis(range);
        return ServerResponse.createBySuccess(hotSaleByRedis);
    }


    @ApiOperation(value = "滞销商品", notes = "获取销量后十的商品")
    @GetMapping("/report/unsalable")
    public ServerResponse<List<SalesVolumeVo>> getUnsalable(
            @ApiParam(value = "查询范围(今天->today 三天内->threeDays 一周内->week 近30天->month)") @RequestParam(required = true) String range) {
        List<SalesVolumeVo> unsalableByRedis = reportService.getUnsalableByRedis(range);
        return ServerResponse.createBySuccess(unsalableByRedis);
    }


    @ApiOperation(value = "客户欠款查询")
    @GetMapping("/report/customer_debt")
    public ServerResponse<PageModel<CustomerDebtBo>> getCustomerDebt(@ApiParam(value = "查询条件(客户名字/手机号)")
                                                                     @RequestParam(required = false) String condition,
                                                                     @Valid PageModel pageModel) {
        PageModel<CustomerDebtBo> customerDebt = reportService.getCustomerDebt(condition, pageModel);
        return ServerResponse.createBySuccess(customerDebt);
    }


}
