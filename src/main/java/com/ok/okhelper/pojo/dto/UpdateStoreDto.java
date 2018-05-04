package com.ok.okhelper.pojo.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author: zc
 * @description:
 * @date: 2018/4/15
 */
@Data
public class UpdateStoreDto {

    /**
     * 店铺名称
     */
    @Column(name = "store_name")
    private String storeName;

    /**
     * 联系电话
     */
    @Column(name = "store_phone")
    private String storePhone;

    /**
     * 店铺地址
     */
    @Column(name = "store_address")
    private String storeAddress;

    /**
     * 店铺logo
     */
    @Column(name = "store_logo")
    private String storeLogo;

    /**
     * 描述
     */
    private String description;

}
