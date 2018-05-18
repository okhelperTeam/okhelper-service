package com.ok.okhelper.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/*
*Author:zhangxin_an
*Description:入库单
*Data:Created in 10:23 2018/5/3
*/
@Data
public class StorageOrderDto {

	/**
	 * 入库单号
	 */
	@ApiModelProperty(value = "后端生成")
	private String orderNumber;
	
	/**
	 * 供应商Id
	 */
	@ApiModelProperty(value = "供应商Id",required = true)
	@NotNull
	private Long supplierId;
	
	/**
	 * 入库员
	 */
	@ApiModelProperty(value = "入库员Id")
	private Long stockiner;
	
	/**
	 * 备注
	 */
	@ApiModelProperty(value = "其他备注")
	private String remarks;
	
	//入库单详情
	@ApiModelProperty(value = "详情")
	private List<StorageDetailDto> storageDetail;
	
}
