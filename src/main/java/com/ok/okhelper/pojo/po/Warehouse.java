package com.ok.okhelper.pojo.po;

import java.util.Date;
import javax.persistence.*;

public class Warehouse {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Long id;

    /**
     * 仓库名称
     */
    @Column(name = "warehouse_name")
    private String warehouseName;

    /**
     * 描述
     */
    private String description;

    /**
     * 仓管员
     */
    @Column(name = "store_keeper")
    private Long storeKeeper;

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
     * 状态 0不可用，1可用
     */
    @Column(name = "delete_status")
    private Integer deleteStatus;

    /**
     * 所属商店Id
     */
    @Column(name = "store_id")
    private Long storeId;

    public Warehouse(Long id, String warehouseName, String description, Long storeKeeper, Long operator, Date createTime, Date updateTime, Integer deleteStatus, Long storeId) {
        this.id = id;
        this.warehouseName = warehouseName;
        this.description = description;
        this.storeKeeper = storeKeeper;
        this.operator = operator;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.deleteStatus = deleteStatus;
        this.storeId = storeId;
    }

    public Warehouse() {
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
     * 获取仓库名称
     *
     * @return warehouse_name - 仓库名称
     */
    public String getWarehouseName() {
        return warehouseName;
    }

    /**
     * 设置仓库名称
     *
     * @param warehouseName 仓库名称
     */
    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName == null ? null : warehouseName.trim();
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
     * 获取仓管员
     *
     * @return store_keeper - 仓管员
     */
    public Long getStoreKeeper() {
        return storeKeeper;
    }

    /**
     * 设置仓管员
     *
     * @param storeKeeper 仓管员
     */
    public void setStoreKeeper(Long storeKeeper) {
        this.storeKeeper = storeKeeper;
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
     * 获取状态 0不可用，1可用
     *
     * @return delete_status - 状态 0不可用，1可用
     */
    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    /**
     * 设置状态 0不可用，1可用
     *
     * @param deleteStatus 状态 0不可用，1可用
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