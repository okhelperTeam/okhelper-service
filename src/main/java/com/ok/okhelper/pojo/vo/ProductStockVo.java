package com.ok.okhelper.pojo.vo;

import lombok.Data;

import java.util.List;

/*
 *Author:zhangxin_an
 *Description:单个商品总库存
 *Data:Created in 15:20 2018/5/14
 */
@Data
public class ProductStockVo {
	
	/**
	 * 主键
	 */
	private Long id;
	
	/**
	 * 商品名
	 */
	private String productName;
	
	/**
	 * 库存数量
	 */
	private Long totalStock;
	/**
	 * 商品标题
	 */
	private String productTitle;
	
	/**
	 * 主图
	 */
	private String mainImg;
	
	
	public ProductStockVo(Long id, String productName, String productTitle, String mainImg) {
		this.id = id;
		this.productName = productName;
		this.productTitle = productTitle;
		this.mainImg = mainImg;
	}
}
