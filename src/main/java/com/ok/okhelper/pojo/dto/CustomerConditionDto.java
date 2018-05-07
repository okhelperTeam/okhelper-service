package com.ok.okhelper.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

/**
 * Author: zc
 * Date: 2018/5/7
 * Description:
 */
@Data
public class CustomerConditionDto {
    /**
     * 客户姓名
     */
    @ApiModelProperty(value = "客户姓名")
    private String customerName;

    /**
     * 客户店名
     */
    @ApiModelProperty(value = "客户店名")
    private String customerStoreName;

    /**
     * 客户手机号
     */
    @ApiModelProperty(value = "客户联系电话")
    private String customerPhone;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "客户邮箱")
    @Column(name = "customer_email")
    private String customerEmail;

    /**
     * 客户积分
     */
    @ApiModelProperty(value = "客户积分")
    private Integer customerScore;

    /**
     * vip级别 0->5 默认0
     */
    @ApiModelProperty(value = "vip级别 0->5 默认0")
    private Integer customerLevel;

    /**
     * 客户地址
     */
    @ApiModelProperty(value = "客户地址")
    private String customerAddress;
}
