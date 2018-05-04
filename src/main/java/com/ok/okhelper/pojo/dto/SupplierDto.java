package com.ok.okhelper.pojo.dto;

/*
*Author:zhangxin_an
*Description:
*Data:Created in 13:58 2018/4/24
*/

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/*
*Author:zhangxin_an
*Description:
*Data:Created in 13:54 2018/4/24
*/
@Data
public class SupplierDto {
	
	/**
	 * 供应商名称
	 */
	@NotNull(message = "供应商姓名不能为空")
	@ApiModelProperty(value = "供应商名称", required = true)
	private String supplierName;
	
	/**
	 * 供应商手机号
	 */
	@ApiModelProperty(value = "供应商联系方式", required = true)
	@NotNull(message = "供应商联系方式不能为空")
	private String supplierPhone;
	
	/**
	 * 供应商地址
	 */
	@ApiModelProperty(value = "供应商地址", required = true)
	@NotNull(message = "供应商地址不能为空")
	private String supplierAddress;
	
	/**
	 * 供应商联系人姓名
	 */
	@ApiModelProperty(value = "供应商联系人姓名", required = true)
	@NotNull(message = "供应商联系人姓名不能为空")
	private String supplierContacts;
	
	/**
	 * 备注
	 */
	private String remarks;
	
	/**
	 * 操作者
	 */
	private Long operator;
}
