package com.ok.okhelper.pojo.vo;

import com.ok.okhelper.pojo.bo.IdAndNameBo;
import com.ok.okhelper.pojo.bo.StorageDetailBo;
import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/*
*Author:zhangxin_an
*Description:入库单
*Data:Created in 10:23 2018/5/3
*/
@Data
public class StorageOrderVo {
	
	/**
	 * 主键
	 */
	private Long id;
	
	/**
	 * 入库单号
	 */
	private String orderNumber;
	
	/**
	 * 供应商Id
	 */
	private IdAndNameBo supplier;
	
	/**
	 * 入库员
	 */
	private IdAndNameBo stockiner;
	
	/**
	 * 备注
	 */
	private String remarks;
	
	/**
	 * 创建日期
	 */
	private Date createTime;
	
	/**
	 * 更新日期
	 */
	private Date updateTime;
	
	//入库单详情
	private List<StorageDetailBo> storageDetail;
	
	/**
	 * 总金额
	 */
	private BigDecimal totalPrice;
	
	public StorageOrderVo(Long id, String orderNumber, IdAndNameBo supplier, IdAndNameBo stockiner, String remarks, Date createTime, Date updateTime) {
		this.id = id;
		this.orderNumber = orderNumber;
		this.supplier = supplier;
		this.stockiner = stockiner;
		this.remarks = remarks;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}
	
	public StorageOrderVo() {
	}
}
