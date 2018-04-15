package com.ok.okhelper.controller;

import com.ok.okhelper.common.ServerResponse;
import com.ok.okhelper.exception.IllegalException;
import com.ok.okhelper.pojo.dto.RoleDto;
import com.ok.okhelper.service.RoleService;
import com.ok.okhelper.shiro.JWTUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author: zc
 * @description:角色控制器
 * @date: 2018/4/14
 */
@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @RequiresPermissions("user:post")
    public ServerResponse<String> postRole(@Valid RoleDto roleDto) {
        Subject subject = SecurityUtils.getSubject();
        Long userId = JWTUtil.getUserId(subject.getPrincipal().toString());
        Long storeId = JWTUtil.getStoreId(subject.getPrincipal().toString());
        roleDto.setStoreId(storeId);
        roleDto.setOperator(userId);


        if (!roleService.postRole(roleDto)) {
            throw new IllegalException("创建失败");
        }
        return ServerResponse.createBySuccessCodeMessages(HttpStatus.CREATED.value(), "角色创建成功");
    }


}
