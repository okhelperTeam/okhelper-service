package com.ok.okhelper.controller;

import com.ok.okhelper.common.ServerResponse;
import com.ok.okhelper.pojo.po.Permission;
import com.ok.okhelper.service.PermissionService;
import com.ok.okhelper.shiro.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Author: zc
 * Date: 2018/4/20
 * Description:
 */
@RestController
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping("/permission/me")
    public ServerResponse<List<Permission>> getPermissionListMe() {
        List<Permission> addPermission = permissionService.findAddPermission(JWTUtil.getUserId());
        return ServerResponse.createBySuccess(addPermission);
    }

    @GetMapping("/clear")
    public String clear() {
        permissionService.clearPermissionListCacheByUserId((long) 1);
        return "clear ok";
    }
}
