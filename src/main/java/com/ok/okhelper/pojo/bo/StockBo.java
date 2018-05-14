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
	
	private Long id;
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
	
	
	/**
	 * 库存量
	 */
	private Long stockCount;
	
	/*
	*剩余过期天数
	 */
	private Integer overDay;
	
	public StockBo(Long id, Long warehouseId, Long productId, Date productDate, Integer shelfLife, Long stockCount) {
		this.id = id;
		this.warehouseId = warehouseId;
		this.productId = productId;
		this.productDate = productDate;
		this.shelfLife = shelfLife;
		this.stockCount = stockCount;
	}
	
	public StockBo(Long id, Long warehouseId, Long productId, Date productDate, Integer shelfLife, Long stockCount, Integer overDay) {
		this.id = id;
		this.warehouseId = warehouseId;
		this.productId = productId;
		this.productDate = productDate;
		this.shelfLife = shelfLife;
		this.stockCount = stockCount;
		this.overDay = overDay;
	}
	
	public StockBo(){
	
	}
}
