package com.ok.okhelper.service;

import com.ok.okhelper.common.ServerResponse;
import com.ok.okhelper.pojo.dto.SupplierDto;
import com.ok.okhelper.pojo.dto.WarehouseDTO;
import com.ok.okhelper.pojo.po.Supplier;
import com.ok.okhelper.pojo.vo.SupplierVo;
import com.ok.okhelper.pojo.vo.WarehouseVo;

import java.util.List;

/*
*Author:zhangxin_an
*Description:
*Data:Created in 13:53 2018/4/24
*/
public interface SupplierService {
	
	List<Supplier> getSupplierList();
	
	Supplier getSupplierById(Long whId);
	
	ServerResponse updateSupplier(SupplierDto SupplierDto);
	
	ServerResponse deleteSupplierById(Long whId);
	
	
	ServerResponse addSupplier(SupplierDto SupplierDto);
	
}
