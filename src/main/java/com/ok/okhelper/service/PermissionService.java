package com.ok.okhelper.service;

import com.ok.okhelper.pojo.po.Permission;

import java.util.List;

/**
 * Author: zc
 * Date: 2018/4/20
 * Description:
 */
public interface PermissionService {
    List<Permission> findAddPermission(Long userId);

    void clearPermissionListCacheByUserId(Long userId);

    List<Permission> updatePermissionCacheByUserId(Long userId);
}
