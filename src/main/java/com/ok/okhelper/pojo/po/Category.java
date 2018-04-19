package com.ok.okhelper.pojo.po;

import java.util.Date;
import javax.persistence.*;

public class Category {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Long id;

    /**
     * 父类Id
     */
    @Column(name = "super_id")
    private Long superId;

    /**
     * 类别名称
     */
    @Column(name = "category_name")
    private String categoryName;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 创建者Id
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
     * 商店Id
     */
    @Column(name = "store_id")
    private Long storeId;

    public Category(Long id, Long superId, String categoryName, String remarks, Long operator, Date createTime, Date updateTime, Integer deleteStatus, Long storeId) {
        this.id = id;
        this.superId = superId;
        this.categoryName = categoryName;
        this.remarks = remarks;
        this.operator = operator;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.deleteStatus = deleteStatus;
        this.storeId = storeId;
    }

    public Category() {
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
     * 获取父类Id
     *
     * @return super_id - 父类Id
     */
    public Long getSuperId() {
        return superId;
    }

    /**
     * 设置父类Id
     *
     * @param superId 父类Id
     */
    public void setSuperId(Long superId) {
        this.superId = superId;
    }

    /**
     * 获取类别名称
     *
     * @return category_name - 类别名称
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * 设置类别名称
     *
     * @param categoryName 类别名称
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
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
     * 获取创建者Id
     *
     * @return operator - 创建者Id
     */
    public Long getOperator() {
        return operator;
    }

    /**
     * 设置创建者Id
     *
     * @param operator 创建者Id
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
     * 获取商店Id
     *
     * @return store_id - 商店Id
     */
    public Long getStoreId() {
        return storeId;
    }

    /**
     * 设置商店Id
     *
     * @param storeId 商店Id
     */
    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }
}