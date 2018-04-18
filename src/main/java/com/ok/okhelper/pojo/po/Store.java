package com.ok.okhelper.pojo.po;

import java.util.Date;
import javax.persistence.*;

public class Store {
    /**
     * 主键
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    /**
     * 店铺名称
     */
    @Column(name = "store_name")
    private String storeName;

    @Column(name = "store_phone")
    private String storePhone;

    /**
     * 店铺地址
     */
    @Column(name = "store_address")
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

    public Store(Long id, String storeName, String storePhone, String storeAddress, String storePhoto, String description, Date createTime, Date updateTime, String deleteStatus, Long operator, Long leaderId) {
        this.id = id;
        this.storeName = storeName;
        this.storePhone = storePhone;
        this.storeAddress = storeAddress;
        this.storePhoto = storePhoto;
        this.description = description;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.deleteStatus = deleteStatus;
        this.operator = operator;
        this.leaderId = leaderId;
    }

    public Store() {
        super();
    }

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取店铺名称
     *
     * @return store_name - 店铺名称
     */
    public String getStoreName() {
        return storeName;
    }

    /**
     * 设置店铺名称
     *
     * @param storeName 店铺名称
     */
    public void setStoreName(String storeName) {
        this.storeName = storeName == null ? null : storeName.trim();
    }

    /**
     * @return store_phone
     */
    public String getStorePhone() {
        return storePhone;
    }

    /**
     * @param storePhone
     */
    public void setStorePhone(String storePhone) {
        this.storePhone = storePhone == null ? null : storePhone.trim();
    }

    /**
     * 获取店铺地址
     *
     * @return store_address - 店铺地址
     */
    public String getStoreAddress() {
        return storeAddress;
    }

    /**
     * 设置店铺地址
     *
     * @param storeAddress 店铺地址
     */
    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress == null ? null : storeAddress.trim();
    }

    /**
     * 获取店铺图像
     *
     * @return store_photo - 店铺图像
     */
    public String getStorePhoto() {
        return storePhoto;
    }

    /**
     * 设置店铺图像
     *
     * @param storePhoto 店铺图像
     */
    public void setStorePhoto(String storePhoto) {
        this.storePhoto = storePhoto == null ? null : storePhoto.trim();
    }

    /**
     * 获取描述
     *
     * @return description - 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述
     *
     * @param description 描述
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 获取创建日期
     *
     * @return create_time - 创建日期
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建日期
     *
     * @param createTime 创建日期
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新日期
     *
     * @return update_time - 更新日期
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新日期
     *
     * @param updateTime 更新日期
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取状态 0废除，1激活
     *
     * @return delete_status - 状态 0废除，1激活
     */
    public String getDeleteStatus() {
        return deleteStatus;
    }

    /**
     * 设置状态 0废除，1激活
     *
     * @param deleteStatus 状态 0废除，1激活
     */
    public void setDeleteStatus(String deleteStatus) {
        this.deleteStatus = deleteStatus == null ? null : deleteStatus.trim();
    }

    /**
     * 获取操作者
     *
     * @return operator - 操作者
     */
    public Long getOperator() {
        return operator;
    }

    /**
     * 设置操作者
     *
     * @param operator 操作者
     */
    public void setOperator(Long operator) {
        this.operator = operator;
    }

    /**
     * 获取负责人
     *
     * @return leader_id - 负责人
     */
    public Long getLeaderId() {
        return leaderId;
    }

    /**
     * 设置负责人
     *
     * @param leaderId 负责人
     */
    public void setLeaderId(Long leaderId) {
        this.leaderId = leaderId;
    }
}