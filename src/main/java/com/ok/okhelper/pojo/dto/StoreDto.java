package com.ok.okhelper.pojo.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author: zc
 * @description:
 * @date: 2018/4/15
 */
@Data
public class StoreDto {
    /**
     * 店铺名称
     */
    @NotNull(message = "store_name不能为null")
    @Column(name = "storeName")
    private String storeName;

    /**
     * 店铺地址
     */
    @NotNull(message = "store_address不能为null")
    @Column(name = "storeAddress")
    private String storeAddress;

    /**
     * 店铺图像
     */
    @Column(name = "store_phtoo")
    private String storePhtoo;

    /**
     * 描述
     */
    private String description;

    /**
     * 操作者
     */
    private Long operator;

    /**
     * 负责人
     */
    @Column(name = "leader_id")
    private Long leaderId;


}
