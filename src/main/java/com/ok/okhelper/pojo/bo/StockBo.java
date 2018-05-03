package com.ok.okhelper.pojo.bo;

import lombok.Data;

import java.util.Date;

/*
*Author:zhangxin_an
*Description:根据商品,生产日期，保质期，仓库，确定库存
*Data:Created in 17:04 2018/5/3
*/
@Data
public class StockBo {
	/**
	 * 仓库Id
	 */
	private Long warehouseId;
	
	/**
	 * 商品Id
	 */
	private Long productId;
	
	/**
	 * 生产日期
	 */
	private Date productDate;
	
	/**
	 * 保质期
	 */
	private Integer shelfLife;
}
