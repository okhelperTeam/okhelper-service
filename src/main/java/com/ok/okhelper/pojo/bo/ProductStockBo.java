package com.ok.okhelper.pojo.bo;

import lombok.Data;

/*
 *Author:zhangxin_an
 *Description:商品库存
 *Data:Created in 15:28 2018/5/14
 */
@Data
public class ProductStockBo {
	
	//商品ID
	private Long productId;
	//商品总库存
	private Long totalStock;
	
	
	
	public ProductStockBo(Long productId, Long totalStock) {
		this.productId = productId;
		this.totalStock = totalStock;
	}
	
	public ProductStockBo() {
	}
}
