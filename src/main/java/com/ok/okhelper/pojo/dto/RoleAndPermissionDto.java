package com.ok.okhelper.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: zc
 * Date: 2018/5/16
 * Description:
 */
@Data
public class RoleAndPermissionDto {
    //角色列表
    @ApiModelProperty(value = "权限列表(传权限id数组,不传则取消全部权限)", dataType = "Array")
    private List<Long> permissions=new ArrayList<>();
}
