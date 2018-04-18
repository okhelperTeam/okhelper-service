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
public class StoreDto {
    /**
     * 主键
     */
    @Id
    private Long id;
    /**
     * 店铺名称
     */
    @NotNull(message = "商店名称不能为")
    @Column(name = "storeName")
    private String storeName;

    /**
     * 店铺地址
     */
    @NotNull(message = "商店地址不能为空")
    @Column(name = "storeAddress")
    private String storeAddress;

    /**
     * 店铺图像
     */
    @Column(name = "store_photo")
    private String storePhoto;

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
