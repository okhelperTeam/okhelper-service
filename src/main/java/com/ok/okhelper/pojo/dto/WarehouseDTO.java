package com.ok.okhelper.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

/*
*Author:zhangxin_an
*Description:仓库前段视图
*Data:Created in 9:58 2018/4/24
*/
@Data
public class WarehouseDTO {
	/**
	 * 主键
	 */
	@ApiModelProperty(value = "仓库Id")
	private Long id;
	
	/**
	 * 仓库名称
	 */
	@ApiModelProperty(value = "仓库名称", required = true)
	private String warehouseName;
	
	/**
	 * 描述
	 */
	@ApiModelProperty(value = "仓库描述")
	private String description;
	
	/**
	 * 仓管员
	 */
	@ApiModelProperty(value = "仓管员",required = true)
	private Long storeKeeper;
	
	
	
	/**
	 * 状态 0不可用，1可用
	 */
	@ApiModelProperty(value = "仓库",required = true)
	private Integer deleteStatus;
	
}
