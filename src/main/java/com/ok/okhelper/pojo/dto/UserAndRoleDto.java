package com.ok.okhelper.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Author: zc
 * Date: 2018/4/18
 * Description:
 */
@Data
public class UserAndRoleDto {
    //员工Id
    @ApiModelProperty(value = "员工Id", required = true)
    @NotNull(message = "员工Id不能为空")
    private Long employeeId;

    //角色列表
    @ApiModelProperty(value = "角色列表", dataType = "Array")
    private List<Long> roles;
}
