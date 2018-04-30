package com.ok.okhelper.pojo.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/*
*Author:zhangxin_an
*Description:查询商品列表
*Data:Created in 14:49 2018/4/30
*/
@Data
public class ProductsVo {
	/**
	 * 主键
	 */
	private Long id;
	
	/**
	 * 商品名
	 */
	private String productName;
	
	/**
	 * 商品标题
	 */
	private String productTitle;
	
	
	
	/**
	 * 可销售库存(小于等于真是库存)
	 */
	private Integer salesStock;
	
	

	
	/**
	 * 零售价
	 */
	private BigDecimal retailPrice;
	
	/**
	 * 主图
	 */
	private String mainImg;
	
	/**
	 * 创建日期
	 */
	private Date createTime;
	
	/**
	 * 更新日期
	 */
	private Date updateTime;
	
	
	public ProductsVo(Long id, String productName, String productTitle, Integer salesStock, BigDecimal retailPrice, String mainImg, Date createTime, Date updateTime) {
		this.id = id;
		this.productName = productName;
		this.productTitle = productTitle;
		this.salesStock = salesStock;
		this.retailPrice = retailPrice;
		this.mainImg = mainImg;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}
	
	public ProductsVo() {
	}
}
