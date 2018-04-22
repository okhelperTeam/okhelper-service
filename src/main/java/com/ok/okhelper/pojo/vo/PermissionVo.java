package com.ok.okhelper.pojo.vo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Author: zc
 * Date: 2018/4/21
 * Description:
 */
@Data
public class PermissionVo {
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

}
