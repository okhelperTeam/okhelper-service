package com.ok.okhelper.pojo.po;

import java.util.Date;
import javax.persistence.*;

public class Supplier {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Long id;

    /**
     * 供应商名称
     */
    @Column(name = "supplier_name")
    private String supplierName;

    /**
     * 供应商手机号
     */
    @Column(name = "supplier_phone")
    private String supplierPhone;

    /**
     * 供应商地址
     */
    @Column(name = "supplier_address")
    private String supplierAddress;

    /**
     * 供应商联系人姓名
     */
    @Column(name = "supplier_contacts")
    private String supplierContacts;

    /**
     * 备注
     */
    private String remarks;

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
     * 状态 0禁用，1可以
     */
    @Column(name = "delete_status")
    private Integer deleteStatus;

    /**
     * 所属商店Id 
     */
    @Column(name = "store_id")
    private Long storeId;

    public Supplier(Long id, String supplierName, String supplierPhone, String supplierAddress, String supplierContacts, String remarks, Long operator, Date createTime, Date updateTime, Integer deleteStatus, Long storeId) {
        this.id = id;
        this.supplierName = supplierName;
        this.supplierPhone = supplierPhone;
        this.supplierAddress = supplierAddress;
        this.supplierContacts = supplierContacts;
        this.remarks = remarks;
        this.operator = operator;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.deleteStatus = deleteStatus;
        this.storeId = storeId;
    }

    public Supplier() {
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
     * 获取供应商名称
     *
     * @return supplier_name - 供应商名称
     */
    public String getSupplierName() {
        return supplierName;
    }

    /**
     * 设置供应商名称
     *
     * @param supplierName 供应商名称
     */
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName == null ? null : supplierName.trim();
    }

    /**
     * 获取供应商手机号
     *
     * @return supplier_phone - 供应商手机号
     */
    public String getSupplierPhone() {
        return supplierPhone;
    }

    /**
     * 设置供应商手机号
     *
     * @param supplierPhone 供应商手机号
     */
    public void setSupplierPhone(String supplierPhone) {
        this.supplierPhone = supplierPhone == null ? null : supplierPhone.trim();
    }

    /**
     * 获取供应商地址
     *
     * @return supplier_address - 供应商地址
     */
    public String getSupplierAddress() {
        return supplierAddress;
    }

    /**
     * 设置供应商地址
     *
     * @param supplierAddress 供应商地址
     */
    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress == null ? null : supplierAddress.trim();
    }

    /**
     * 获取供应商联系人姓名
     *
     * @return supplier_contacts - 供应商联系人姓名
     */
    public String getSupplierContacts() {
        return supplierContacts;
    }

    /**
     * 设置供应商联系人姓名
     *
     * @param supplierContacts 供应商联系人姓名
     */
    public void setSupplierContacts(String supplierContacts) {
        this.supplierContacts = supplierContacts == null ? null : supplierContacts.trim();
    }

    /**
     * 获取备注
     *
     * @return remarks - 备注
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置备注
     *
     * @param remarks 备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
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
     * 获取状态 0禁用，1可以
     *
     * @return delete_status - 状态 0禁用，1可以
     */
    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    /**
     * 设置状态 0禁用，1可以
     *
     * @param deleteStatus 状态 0禁用，1可以
     */
    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    /**
     * 获取所属商店Id 
     *
     * @return store_id - 所属商店Id 
     */
    public Long getStoreId() {
        return storeId;
    }

    /**
     * 设置所属商店Id 
     *
     * @param storeId 所属商店Id 
     */
    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }
}