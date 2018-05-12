package com.ok.okhelper.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Author: zc
 * Date: 2018/5/4
 * Description:
 */
@Data
public class CustomerDto {
    /**
     * 客户姓名
     */
    @NotNull
    @Column(name = "customer_name")
    @ApiModelProperty(value = "客户姓名", required = true)
    private String customerName;

    /**
     * 客户店名
     */
    @NotNull
    @Column(name = "customer_store_name")
    @ApiModelProperty(value = "客户店名", required = true)
    private String customerStoreName;

    /**
     * 客户手机号
     */
    @NotNull
    @Column(name = "customer_phone")
    @ApiModelProperty(value = "客户联系电话", required = true)
    private String customerPhone;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "客户邮箱", required = false)
    @Column(name = "customer_email")
    private String customerEmail;

    /**
     * 客户积分
     */
    @Column(name = "customer_score")
    @ApiModelProperty(value = "客户积分", required = false)
    private Integer customerScore;

    /**
     * vip级别 0->5 默认0
     */
    @Column(name = "customer_level")
    @ApiModelProperty(value = "vip级别 0->5 默认0", required = false)
    private Integer customerLevel;

    /**
     * 客户地址
     */
    @NotNull
    @ApiModelProperty(value = "客户地址", required = true)
    @Column(name = "customer_address")
    private String customerAddress;

    /**
     * 备注
     */
    private String remarks;
}
