package com.ok.okhelper.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;


/*
 *Author:zhangxin_an
 *Description:添加分类
 *Data:Created in 22:03 2018/5/9
 */
@Data
public class CategoryDto {
	/**
	 * 主键
	 */
	private Long id;
	
	/**
	 * 父类Id
	 */
	@NotNull
	@ApiModelProperty(value = "父类Id",required = true)
	private Long superId;
	
	/**
	 * 类别名称
	 */
	@NotNull
	@ApiModelProperty(value = "类名",required = true)
	private String categoryName;
	
	/**
	 * 备注
	 */
	private String remarks;
}

