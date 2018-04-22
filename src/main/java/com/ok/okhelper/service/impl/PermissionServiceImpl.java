package com.ok.okhelper.service.impl;

import com.ok.okhelper.dao.PermissionMapper;
import com.ok.okhelper.pojo.po.Permission;
import com.ok.okhelper.pojo.vo.PermissionMenuVo;
import com.ok.okhelper.pojo.vo.PermissionVo;
import com.ok.okhelper.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: zc
 * Date: 2018/4/20
 * Description:
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;


    @Cacheable(value = "permission_list_me", key = "#userId")
    public List<Permission> findAddPermission(Long userId) {
        return permissionMapper.findAddPermission(userId);
    }

    @CacheEvict(value = "permission_list_me", key = "#userId")
    public void clearPermissionListCacheByUserId(Long userId) {
    }

    @CachePut(value = "permission_list_me", key = "#userId")
    public List<Permission> updatePermissionCacheByUserId(Long userId) {
        return permissionMapper.findAddPermission(userId);
    }

    @Override
    public List<PermissionMenuVo> getPermissionMenuByUserId(Long userId) {
        return permissionMapper.findPermissionMenu(userId);
    }
}
