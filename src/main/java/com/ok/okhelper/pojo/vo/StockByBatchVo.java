package com.ok.okhelper.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ok.okhelper.pojo.bo.IdAndNameBo;
import lombok.Data;

import javax.annotation.security.DenyAll;
import javax.persistence.Column;
import java.util.Date;

/*
 *Author:zhangxin_an
 *Description:单批次商品库存
 *Data:Created in 16:28 2018/5/14
 */
@Data
public class StockByBatchVo {
	/**
	 * 仓库Id
	 */
	private IdAndNameBo warehouse;
	
	/**
	 * 商品Id
	 */
	private IdAndNameBo product;
	
	/**
	 * 生产日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date productDate;
	
	/**
	 * 保质期
	 */
	private Integer shelfLife;
	
	/**
	 * 库存数量
	 */
	private Long stockCount;
}
