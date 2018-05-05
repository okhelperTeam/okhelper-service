package com.ok.okhelper.pojo.po;

import java.util.Date;
import javax.persistence.*;

public class Customer {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Long id;

    /**
     * 客户姓名
     */
    @Column(name = "customer_name")
    private String customerName;

    /**
     * 客户店名
     */
    @Column(name = "customer_store_name")
    private String customerStoreName;

    /**
     * 客户手机号
     */
    @Column(name = "customer_phone")
    private String customerPhone;

    /**
     * 邮箱
     */
    @Column(name = "customer_email")
    private String customerEmail;

    /**
     * 客户积分(每次消费取整)
     */
    @Column(name = "customer_score")
    private Integer customerScore;

    /**
     * vip级别 0->5 默认0
     */
    @Column(name = "customer_level")
    private Integer customerLevel;

    /**
     * 客户地址
     */
    @Column(name = "customer_address")
    private String customerAddress;

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
     * 状态 0不启用，1启用
     */
    @Column(name = "delete_status")
    private Integer deleteStatus;

    /**
     * 所属商店Id 
     */
    @Column(name = "store_id")
    private Long storeId;

    public Customer(Long id, String customerName, String customerStoreName, String customerPhone, String customerEmail, Integer customerScore, Integer customerLevel, String customerAddress, String remarks, Long operator, Date createTime, Date updateTime, Integer deleteStatus, Long storeId) {
        this.id = id;
        this.customerName = customerName;
        this.customerStoreName = customerStoreName;
        this.customerPhone = customerPhone;
        this.customerEmail = customerEmail;
        this.customerScore = customerScore;
        this.customerLevel = customerLevel;
        this.customerAddress = customerAddress;
        this.remarks = remarks;
        this.operator = operator;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.deleteStatus = deleteStatus;
        this.storeId = storeId;
    }

    public Customer() {
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
     * 获取客户姓名
     *
     * @return customer_name - 客户姓名
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * 设置客户姓名
     *
     * @param customerName 客户姓名
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    /**
     * 获取客户店名
     *
     * @return customer_store_name - 客户店名
     */
    public String getCustomerStoreName() {
        return customerStoreName;
    }

    /**
     * 设置客户店名
     *
     * @param customerStoreName 客户店名
     */
    public void setCustomerStoreName(String customerStoreName) {
        this.customerStoreName = customerStoreName == null ? null : customerStoreName.trim();
    }

    /**
     * 获取客户手机号
     *
     * @return customer_phone - 客户手机号
     */
    public String getCustomerPhone() {
        return customerPhone;
    }

    /**
     * 设置客户手机号
     *
     * @param customerPhone 客户手机号
     */
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone == null ? null : customerPhone.trim();
    }

    /**
     * 获取邮箱
     *
     * @return customer_email - 邮箱
     */
    public String getCustomerEmail() {
        return customerEmail;
    }

    /**
     * 设置邮箱
     *
     * @param customerEmail 邮箱
     */
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail == null ? null : customerEmail.trim();
    }

    /**
     * 获取客户积分(每次消费取整)
     *
     * @return customer_score - 客户积分(每次消费取整)
     */
    public Integer getCustomerScore() {
        return customerScore;
    }

    /**
     * 设置客户积分(每次消费取整)
     *
     * @param customerScore 客户积分(每次消费取整)
     */
    public void setCustomerScore(Integer customerScore) {
        this.customerScore = customerScore;
    }

    /**
     * 获取vip级别 0->5 默认0
     *
     * @return customer_level - vip级别 0->5 默认0
     */
    public Integer getCustomerLevel() {
        return customerLevel;
    }

    /**
     * 设置vip级别 0->5 默认0
     *
     * @param customerLevel vip级别 0->5 默认0
     */
    public void setCustomerLevel(Integer customerLevel) {
        this.customerLevel = customerLevel;
    }

    /**
     * 获取客户地址
     *
     * @return customer_address - 客户地址
     */
    public String getCustomerAddress() {
        return customerAddress;
    }

    /**
     * 设置客户地址
     *
     * @param customerAddress 客户地址
     */
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress == null ? null : customerAddress.trim();
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
     * 获取状态 0不启用，1启用
     *
     * @return delete_status - 状态 0不启用，1启用
     */
    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    /**
     * 设置状态 0不启用，1启用
     *
     * @param deleteStatus 状态 0不启用，1启用
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