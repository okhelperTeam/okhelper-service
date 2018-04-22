package com.ok.okhelper.pojo.vo;

import lombok.Data;

import javax.persistence.Column;

/**
 * Author: zc
 * Date: 2018/4/21
 * Description:
 */
@Data
public class PermissionMenuVo {
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
}
