package com.ok.okhelper.pojo.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ok.okhelper.pojo.po.Permission;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

/**
 * Author: zc
 * Date: 2018/4/21
 * Description:
 */
@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class RolePermissionVo {
    /**
     * 主键  角色id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Long id;

    /**
     * 角色名称
     */
    @Column(name = "role_name")
    private String roleName;

    /**
     * 描述
     */
    private String description;

    /**
     * 店铺id
     */
    @Column(name = "store_id")
    private Long storeId;

    /**
     * 权限列表
     */
    private List<PermissionVo> permissions;


    /**
     * 拥有人数
     */
    private Integer userCount;
}
