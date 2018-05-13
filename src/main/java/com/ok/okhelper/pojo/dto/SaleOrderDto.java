package com.ok.okhelper.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Author: zc
 * Date: 2018/4/23
 * Description:
 */
@Data
public class SaleOrderDto {

    //    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "开始时间")
    private Date startDate=new Date(0);

    //    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "结束时间")
    private Date endDate=new Date();

    @ApiModelProperty(value = "查询范围(当指定这个值时startDate&endDate失效)&&(今天->today 昨天->yesterday 三天内->threeDays 一周内->week 近30天->month)")
    private String range = "";

    @ApiModelProperty(value = "客户Id")
    private Long customerId;

    @ApiModelProperty(value = "客户姓名")
    private String customerName;

    @ApiModelProperty(value = "客户级别 0-->5")
    private Integer customerLevel;

    @ApiModelProperty(value = "销售员Id")
    private Long seller;

    @ApiModelProperty(value = "出库员Id")
    private Long stockouter;

    @ApiModelProperty(value = "订单状态（1-未付款，2-未付全款，3-已付款，4-交易成功，5-订单关闭）")
    private Integer orderStatus;

    @ApiModelProperty(value = "物流状态 （1-未发货 2-已发货 3-确认收货）")
    private Integer logisticsStatus;

//    @ApiModelProperty(value = "付款方式  (1-现金, 2-支付宝，3-微信，4-刷卡  json格式 例如{'1':'0.00','2':'0.00'}  ) LIKE模糊查询")
//    private String payType;

    @ApiModelProperty(hidden = true)
    private Long id;

}
