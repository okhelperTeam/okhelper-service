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

    @ApiModelProperty("本次优惠金额是本次")
    private BigDecimal discountPrice = BigDecimal.ZERO;

    @ApiModelProperty(value = "支付方式(1-现金,2-支付宝,3-微信,4-刷卡;注:混合方式拼接成 现金+微信-->1,3 )", required = true)
    @NotNull
    private String payType;
}
