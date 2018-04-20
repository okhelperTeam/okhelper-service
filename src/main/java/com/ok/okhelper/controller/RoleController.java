package com.ok.okhelper.controller;

import com.ok.okhelper.common.ServerResponse;
import com.ok.okhelper.exception.IllegalException;
import com.ok.okhelper.pojo.dto.RoleDto;
import com.ok.okhelper.pojo.po.Role;
import com.ok.okhelper.service.RoleService;
import com.ok.okhelper.shiro.JWTUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author: zc
 * @description:角色控制器
 * @date: 2018/4/14
 */
@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * @Author zc
     * @Date 2018/4/20 下午6:27
     * @Param [roleDto]
     * @Return com.ok.okhelper.common.ServerResponse<java.lang.String>
     * @Description:创建角色
     */
    @PostMapping("/role")
    @ResponseStatus(value = HttpStatus.CREATED)
    @RequiresPermissions("user:post")
    public ServerResponse<String> postRole(@Valid RoleDto roleDto) {
        Subject subject = SecurityUtils.getSubject();
        Long userId = JWTUtil.getUserId();
        Long storeId = JWTUtil.getStoreId();
        roleDto.setStoreId(storeId);
        roleDto.setOperator(userId);

        if (!roleService.postRole(roleDto)) {
            throw new IllegalException("创建失败");
        }

        // 清空缓存
        roleService.clearRoleListCache(storeId);
        return ServerResponse.createBySuccessCodeMessages(HttpStatus.CREATED.value(), "角色创建成功");
    }

    /**
     * @Author zc
     * @Date 2018/4/20 下午6:27
     * @Param []
     * @Return com.ok.okhelper.common.ServerResponse
     * @Description:获取当前商店角色列表
     */
    @GetMapping("/role")
    public ServerResponse getRoleList() {
        List<Role> roleList = roleService.getRoleListByStore(JWTUtil.getStoreId());
        return ServerResponse.createBySuccess(roleList);
    }

}
