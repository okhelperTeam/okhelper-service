package com.ok.okhelper.pojo.po;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

public class Permission implements Serializable {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Long id;

    /**
     * 归属菜单,前端判断并展示菜单使用,
     */
    @Column(name = "menu_code")
    private String menuCode;

    /**
     * 菜单的中文释义
     */
    @Column(name = "menu_name")
    private String menuName;

    /**
     * 权限的代码/通配符,对应代码中@RequiresPermissions 的value
     */
    @Column(name = "permission_code")
    private String permissionCode;

    /**
     * 本权限的中文释义
     */
    @Column(name = "permission_name")
    private String permissionName;

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

    public Permission(Long id, String menuCode, String menuName, String permissionCode, String permissionName, Long operator, Date createTime, Date updateTime, Integer deleteStatus) {
        this.id = id;
        this.menuCode = menuCode;
        this.menuName = menuName;
        this.permissionCode = permissionCode;
        this.permissionName = permissionName;
        this.operator = operator;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.deleteStatus = deleteStatus;
    }

    public Permission() {
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
     * 获取归属菜单,前端判断并展示菜单使用,
     *
     * @return menu_code - 归属菜单,前端判断并展示菜单使用,
     */
    public String getMenuCode() {
        return menuCode;
    }

    /**
     * 设置归属菜单,前端判断并展示菜单使用,
     *
     * @param menuCode 归属菜单,前端判断并展示菜单使用,
     */
    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode == null ? null : menuCode.trim();
    }

    /**
     * 获取菜单的中文释义
     *
     * @return menu_name - 菜单的中文释义
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * 设置菜单的中文释义
     *
     * @param menuName 菜单的中文释义
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    /**
     * 获取权限的代码/通配符,对应代码中@RequiresPermissions 的value
     *
     * @return permission_code - 权限的代码/通配符,对应代码中@RequiresPermissions 的value
     */
    public String getPermissionCode() {
        return permissionCode;
    }

    /**
     * 设置权限的代码/通配符,对应代码中@RequiresPermissions 的value
     *
     * @param permissionCode 权限的代码/通配符,对应代码中@RequiresPermissions 的value
     */
    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode == null ? null : permissionCode.trim();
    }

    /**
     * 获取本权限的中文释义
     *
     * @return permission_name - 本权限的中文释义
     */
    public String getPermissionName() {
        return permissionName;
    }

    /**
     * 设置本权限的中文释义
     *
     * @param permissionName 本权限的中文释义
     */
    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName == null ? null : permissionName.trim();
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
}