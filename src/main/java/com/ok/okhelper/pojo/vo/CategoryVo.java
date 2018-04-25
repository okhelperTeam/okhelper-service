package com.ok.okhelper.pojo.vo;


import lombok.Data;

import java.util.List;

/*
*Author:zhangxin_an
*Description:
*Data:Created in 16:06 2018/4/24
*/
@Data
public class CategoryVo {
	/**
	 * 主键
	 */
	private Long id;
	
	/**
	 * 父类Id
	 */
	private Long superId;
	
	/**
	 * 类别名称
	 */
	private String categoryName;
	
	/**
	 * 备注
	 */
	private String remarks;
	
	/**
	 * 创建者Id
	 */
	private Long operator;
	
	
	/**
	 * 状态 0不启用，1启用
	 */
	private Integer deleteStatus;
	
	/**
	 * 商店Id
	 */
	private Long storeId;
	
	private List<CategoryVo> categoryVoList;
	
	public CategoryVo() {
	}
	
	public CategoryVo(Long id, Long superId, String categoryName, String remarks, Long operator, Integer deleteStatus, Long storeId) {
		this.id = id;
		this.superId = superId;
		this.categoryName = categoryName;
		this.remarks = remarks;
		this.operator = operator;
		this.deleteStatus = deleteStatus;
		this.storeId = storeId;
	}
}
