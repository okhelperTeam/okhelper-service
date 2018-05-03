package com.ok.okhelper.pojo.bo;

import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.Date;

/*
*Author:zhangxin_an
*Description:入库单子项
*Data:Created in 10:14 2018/5/3
*/
@Data
public class StorageDetailBo {
	/**
	 * 主键
	 */
	private Long id;
	
	/**
	 * 供应商
	 */
	private IdAndNameBo supplier;
	
	/**
	 * 仓库
	 */
	private IdAndNameBo warehouse;
	
	/**
	 * 商品
	 */
	private IdAndNameBo product;
	
	/**
	 * 商品数量(最小单位)
	 */
	@Column(name = "storage_count")
	private Integer storageCount;
	
	/**
	 * 进价 (最小单位)
	 */
	@Column(name = "storage_price")
	private BigDecimal storagePrice;
	
	/**
	 * 生产日期
	 */
	@Column(name = "product_date")
	private Date productDate;
	
	/**
	 * 保质期
	 */
	@Column(name = "shelf_life")
	private Integer shelfLife;
	
	/**
	 * 备注
	 */
	private String remarks;
	
	/**
	 * 创建日期
	 */
	@Column(name = "create_time")
	private Date createTime;
	
	/**
	 * 更新日期
	 */
	@Column(name = "update_time")
	private Date updateTime;
}
