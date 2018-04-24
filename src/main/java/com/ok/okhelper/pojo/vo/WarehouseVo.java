package com.ok.okhelper.pojo.vo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/*
*Author:zhangxin_an
*Description:仓库前段视图
*Data:Created in 9:58 2018/4/24
*/
@Data
public class WarehouseVo {
	/**
	 * 主键
	 */
	private Long id;
	
	/**
	 * 仓库名称
	 */
	@Column(name = "warehouse_name")
	private String warehouseName;
	
	/**
	 * 描述
	 */
	private String description;
	
	/**
	 * 仓管员
	 */
	private Long storeKeeper;
	
	/**
	 * 操作者
	 */
	private Long operator;
	
	/**
	 * 创建日期
	 */
	private Date createTime;
	
	
	/**
	 * 状态 0不可用，1可用
	 */
	private Integer deleteStatus;
	
	public WarehouseVo(Long id, String warehouseName, String description, Long storeKeeper, Long operator, Date createTime, Integer deleteStatus) {
		this.id = id;
		this.warehouseName = warehouseName;
		this.description = description;
		this.storeKeeper = storeKeeper;
		this.operator = operator;
		this.createTime = createTime;
		this.deleteStatus = deleteStatus;
	}
	public  WarehouseVo(){
	
	}
}
