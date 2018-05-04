package com.ok.okhelper.pojo.po;

import java.util.Date;
import javax.persistence.*;

public class Store {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Long id;

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
     * 收款码
     */
    @Column(name = "money_code")
    private String moneyCode;

    /**
     * 描述
     */
    private String description;

    /**
     * 操作者
     */
    private Long operator;

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
    private Integer deleteStatus;

    /**
     * 负责人
     */
    @Column(name = "leader_id")
    private Long leaderId;

    public Store(Long id, String storeName, String storePhone, String storeAddress, String storeLogo, String moneyCode, String description, Long operator, Date createTime, Date updateTime, Integer deleteStatus, Long leaderId) {
        this.id = id;
        this.storeName = storeName;
        this.storePhone = storePhone;
        this.storeAddress = storeAddress;
        this.storeLogo = storeLogo;
        this.moneyCode = moneyCode;
        this.description = description;
        this.operator = operator;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.deleteStatus = deleteStatus;
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
     * 获取联系电话
     *
     * @return store_phone - 联系电话
     */
    public String getStorePhone() {
        return storePhone;
    }

    /**
     * 设置联系电话
     *
     * @param storePhone 联系电话
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
     * 获取店铺logo
     *
     * @return store_logo - 店铺logo
     */
    public String getStoreLogo() {
        return storeLogo;
    }

    /**
     * 设置店铺logo
     *
     * @param storeLogo 店铺logo
     */
    public void setStoreLogo(String storeLogo) {
        this.storeLogo = storeLogo == null ? null : storeLogo.trim();
    }

    /**
     * 获取收款码
     *
     * @return money_code - 收款码
     */
    public String getMoneyCode() {
        return moneyCode;
    }

    /**
     * 设置收款码
     *
     * @param moneyCode 收款码
     */
    public void setMoneyCode(String moneyCode) {
        this.moneyCode = moneyCode == null ? null : moneyCode.trim();
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
    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    /**
     * 设置状态 0废除，1激活
     *
     * @param deleteStatus 状态 0废除，1激活
     */
    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
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