package com.ok.okhelper.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Author: zc
 * Date: 2018/5/5
 * Description:
 */
@Data
public class PaymentDto {
    @ApiModelProperty(value = "本次实付金额", required = true)
    @NotNull
    private BigDecimal realPay = BigDecimal.ZERO;

//    @ApiModelProperty("本次优惠金额是本次")
//    private BigDecimal discountPrice = BigDecimal.ZERO;

    @ApiModelProperty("交易类型(1-首次支付  2-还款支付  组合收款算一次) 默认为 1")
    private Integer tradeType=1;

    @ApiModelProperty(value = "支付方式(1-现金,2-支付宝,3-微信,4-刷卡;注:每次只能传一个，组合收款一次一次发 )", required = true)
    @NotNull
    private String payType;

    @ApiModelProperty(value = "支付宝付款码(当payType 为2时候必传)")
    private String aliPayAuthCode;
}
