package com.ok.okhelper.service;

import com.ok.okhelper.common.PageModel;
import com.ok.okhelper.common.ServerResponse;
import com.ok.okhelper.pojo.bo.IdAndNameBo;
import com.ok.okhelper.pojo.dto.WarehouseDTO;
import com.ok.okhelper.pojo.po.Warehouse;
import com.ok.okhelper.pojo.vo.WarehouseVo;

import java.util.List;

/*
*Author:zhangxin_an
*Description:
*Data:Created in 9:55 2018/4/24
*/
public interface WareHouseService {
	
	PageModel<WarehouseVo> getWarehouseList(PageModel pageModel);
	
	WarehouseVo getWarehouseById(Long whId);
	
	ServerResponse updateWarehouse(WarehouseDTO warehouseDTO);
	
	ServerResponse deleteWarehouseById(Long whId);
	
	
	ServerResponse addWarehouse(WarehouseDTO warehouseDTO);
	
	
	List<IdAndNameBo> getWarehouseNameList();
}
