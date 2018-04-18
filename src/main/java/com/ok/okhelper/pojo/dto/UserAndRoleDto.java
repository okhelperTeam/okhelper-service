package com.ok.okhelper.pojo.dto;

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
    @NotNull(message = "员工Id不能为空")
    private Long employeeId;

    //角色列表
    private List<Long> roles;
}
