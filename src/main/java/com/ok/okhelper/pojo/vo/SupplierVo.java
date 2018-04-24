package com.ok.okhelper.pojo.vo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/*
*Author:zhangxin_an
*Description:
*Data:Created in 13:54 2018/4/24
*/
@Data
public class SupplierVo {
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
	private Long id;
	
	/**
	 * 供应商名称
	 */
	private String supplierName;
	
	/**
	 * 供应商手机号
	 */
	private String supplierPhone;
	
	/**
	 * 供应商地址
	 */
	private String supplierAddress;
	
	/**
	 * 供应商联系人姓名
	 */
	private String supplierContacts;
	
	/**
	 * 备注
	 */
	private String remarks;
	
	/**
	 * 操作者
	 */
	private Long operator;
	
	/**
	 * 创建日期
	 */
	private Date createTime;
}
