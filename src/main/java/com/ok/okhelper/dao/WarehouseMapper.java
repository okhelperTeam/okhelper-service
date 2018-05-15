package com.ok.okhelper.dao;

import com.ok.okhelper.pojo.bo.IdAndNameBo;
import com.ok.okhelper.pojo.po.Warehouse;
import com.ok.okhelper.pojo.vo.WarehouseVo;
import com.ok.okhelper.util.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseMapper extends MyMapper<Warehouse> {

	List<WarehouseVo> getWarehouseByStoreId(Long storeId);
	
	IdAndNameBo getIdAndName(Long id);
	
	List<IdAndNameBo> getWarehouseNameList(Long storeId);
	
//	Warehouse getUserWarehouse(Long id);

}