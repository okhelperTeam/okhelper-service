package com.ok.okhelper.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: zc
 * Date: 2018/4/18
 * Description:
 */
@Data
public class UserAndRoleDto {

    //角色列表
    @ApiModelProperty(value = "角色列表(传角色id数组,不传则取消全部角色)", dataType = "Array")
    private List<Long> roles=new ArrayList<>();
}
