package com.ok.okhelper.pojo.vo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author: zc
 * @description:
 * @date: 2018/4/15
 */
@Data
public class OpenStore {
    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 店铺名称
     */
    @Column(name = "store_name")
    private String storeName;

    /**
     * 店铺地址
     */
    @Column(name = "store_address")
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
     * 创建日期
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新日期
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 状态 0废除，1激活
     */
    @Column(name = "delete_status")
    private String deleteStatus;

    /**
     * 操作者
     */
    private Long operator;

    /**
     * 负责人
     */
    @Column(name = "leader_id")
    private Long leaderId;

    private String token;
}
