package com.ok.okhelper.controller;

import com.ok.okhelper.common.ServerResponse;
import com.ok.okhelper.pojo.po.Permission;
import com.ok.okhelper.pojo.vo.PermissionMenuVo;
import com.ok.okhelper.service.PermissionService;
import com.ok.okhelper.shiro.JWTUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Author: zc
 * Date: 2018/4/20
 * Description:
 */
@Api(tags = "权限模块")
@RestController
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @ApiOperation(value = "获取我的权限列表", notes = "根据我的userId获取我的权限列表")
    @GetMapping("/permission/me")
    public ServerResponse<List<Permission>> getPermissionListMe() {
        List<Permission> addPermission = permissionService.findAddPermission(JWTUtil.getUserId());
        return ServerResponse.createBySuccess(addPermission);
    }

    @ApiOperation(value = "获取我的菜单列表", notes = "根据我的userId获取我能访问的菜单项")
    @GetMapping("/permission/menu/me")
    public ServerResponse<List<PermissionMenuVo>> getPermissionMenuMe() {
        List<PermissionMenuVo> permissionMenuByUserId = permissionService.getPermissionMenuByUserId(JWTUtil.getUserId());
        return ServerResponse.createBySuccess(permissionMenuByUserId);
    }

}
