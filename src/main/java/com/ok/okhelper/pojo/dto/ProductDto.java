package com.ok.okhelper.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/*
*Author:zhangxin_an
*Description:添加修改商品
*Data:Created in 15:00 2018/4/30
*/
@Data
public class ProductDto {
	
	@ApiModelProperty(value = "商品id")
	private Long id;
	
	/**
	 * 商品名
	 */
	@ApiModelProperty(value = "商品名",required = true)
	@NotNull(message = "商品名为空")
	private String productName;
	
	/**
	 * 商品标题
	 */
	@ApiModelProperty(value = "商品标题")
	private String productTitle;
	
	/**
	 * 商品属性(使用json存储)
	 */
	@ApiModelProperty(value = "商品属性")
	private String productAttribute;
	
	/**
	 * 类别Id
	 */
	@ApiModelProperty(value = "类别Id",required = true)
	@NotNull(message = "商品类别为空")
	private Long categoryId;
	
	/**
	 * 规格
	 */
	@ApiModelProperty(value = "商品规格")
	private String specification;
	
	/**
	 * 规格单位
	 */
	@ApiModelProperty(value = "商品规格单位")
	private String unit;
	
	/**
	 * 零售价
	 */
	@ApiModelProperty(value = "商品零售价",required = true)
	@NotNull(message = "零售价为空")
	private BigDecimal retailPrice;
	
	/**
	 * 主图
	 */
	@ApiModelProperty(value = "商品主图")
	private String mainImg;
	
	/**
	 * 副图(数组)
	 */
	@ApiModelProperty(value = "商品副图（数组）")
	private String[] subImgs;
	
	/**
	 * 货号
	 */
	@ApiModelProperty(value = "货号")
	private String articleNumber;
	
	/**
	 * 条码
	 */
	@ApiModelProperty(value = "条码")
	private String barCode;
	
	
}
