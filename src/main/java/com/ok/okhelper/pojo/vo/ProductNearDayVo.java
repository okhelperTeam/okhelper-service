package com.ok.okhelper.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ok.okhelper.pojo.bo.IdAndNameBo;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.Date;

/*
 *Author:zhangxin_an
 *Description:临期商品前端视图
 *Data:Created in 15:30 2018/5/7
 */
@Data
public class ProductNearDayVo {
	/**
	 * 主键
	 */
	private Long id;
	
	/**
	 * 商品名
	 */
	private String productName;
	
	/**
	 * 所在仓库
	 */
	private IdAndNameBo warehouse;
	/**
	 * 生产日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	private Date productDate;
	
	/**
	 * 保质期
	 */
	private Integer shelfLife;
	
	/**
	 * 库存数量
	 */
	private Long stockCount;
	/**
	 * 商品标题
	 */
	private String productTitle;
	
	
	/**
	 * 零售价
	 */
	private BigDecimal retailPrice;
	
	/**
	 * 主图
	 */
	private String mainImg;
	
	/*
	 *剩余过期天数
	 */
	private Integer overDay;
}
