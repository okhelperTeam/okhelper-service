package com.ok.okhelper.controller;

import com.ok.okhelper.common.ServerResponse;
import com.ok.okhelper.exception.IllegalException;
import com.ok.okhelper.pojo.dto.RoleAndPermissionDto;
import com.ok.okhelper.pojo.dto.RoleDto;
import com.ok.okhelper.pojo.dto.UserAndRoleDto;
import com.ok.okhelper.pojo.vo.RolePermissionVo;
import com.ok.okhelper.service.RoleService;
import com.ok.okhelper.service.UserService;
import com.ok.okhelper.shiro.JWTUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

/**
 * @author: zc
 * @description:角色控制器
 * @date: 2018/4/14
 */
@Api(tags = "角色模块")
@RestController
@Slf4j
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
    @RequiresPermissions("role:edit")
    @PostMapping("/role")
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiOperation(value = "添加角色", code = 201)
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
//        roleService.clearRoleListCache(storeId);
        return ServerResponse.createBySuccessCodeMessages(HttpStatus.CREATED.value(), "角色创建成功");
    }

    /**
     * @Author zc
     * @Date 2018/4/20 下午6:27
     * @Param []
     * @Return com.ok.okhelper.common.ServerResponse
     * @Description:获取当前商店角色列表
     */
    @RequiresPermissions("role:view")
    @GetMapping("/role")
    @ApiOperation(value = "获取角色权限列表", notes = "获取当前店铺所有角色，不含店长")
    public ServerResponse<List<RolePermissionVo>> getRoleList() {
        List<RolePermissionVo> roleList = roleService.getRoleListByStore(JWTUtil.getStoreId());
        return ServerResponse.createBySuccess(roleList);
    }


    /**
     * @Author zc
     * @Date 2018/4/18 上午10:59
     * @Param [userAndRoleDto]
     * @Return com.ok.okhelper.common.ServerResponse
     * @Description: 变更角色 (给员工赋角色)
     */
    @RequiresPermissions("role:view")
    @PutMapping("/role/change_role/{userName}")
    @ApiOperation(value = "变更角色")
    public ServerResponse changeRoleFromUser(@ApiParam(value = "员工ID", required = true) @PathVariable String userName,
                                             UserAndRoleDto userAndRoleDto) {
        log.info("角色变更：用户userName：{} ----> 角色列表：{}======> 操作者Id：{}",userName,userAndRoleDto.getRoles().toString(),JWTUtil.getUserId());
        return roleService.changeRole(userName, userAndRoleDto.getRoles());
    }


    /**
     * @Author zc
     * @Date 2018/4/18 上午10:59
     * @Param [userAndRoleDto]
     * @Return com.ok.okhelper.common.ServerResponse
     * @Description: FIXME 变更权限 (给员工赋角色)   通用角色无权修改，二期再说
     */
    @PutMapping("/role/change_permission/{id:\\d+}")
    @ApiOperation(value = "变更权限",notes = "给角色重新赋权")
    @ApiIgnore
    public ServerResponse changeRoleFromUser(@ApiParam(value = "角色ID", required = true) @PathVariable Long id,
                                             RoleAndPermissionDto roleAndPermissionDto) {
        log.info("角色变更：角色Id：{} ----> 权限列表：{}======> 操作者Id：{}",id,roleAndPermissionDto.getPermissions().toString(),JWTUtil.getUserId());
        return roleService.changePermission(id,roleAndPermissionDto.getPermissions());
    }

}
