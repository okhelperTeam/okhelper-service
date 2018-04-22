package com.ok.okhelper.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author: zc
 * @description: 角色dto
 * @date: 2018/4/14
 */
@Data
public class RoleDto {


    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称", required = true)
    @NotNull(message = "角色名称不能为空")
    private String roleName;

    /**
     * 状态 0废除，1激活
     */
    @ApiModelProperty(value = "状态", notes = "0废除，1激活")
    private String deleteStatus;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String description;

    /**
     * 店铺id
     */
    @ApiModelProperty("店铺ID")
    private Long storeId;

    /**
     * 操作者
     */
    @ApiModelProperty(value = "操作者")
    private Long operator;
}
