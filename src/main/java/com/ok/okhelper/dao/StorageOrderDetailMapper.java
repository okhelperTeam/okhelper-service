package com.ok.okhelper.dao;

import com.ok.okhelper.pojo.po.StorageOrderDetail;
import com.ok.okhelper.util.MyMapper;

import java.util.List;

public interface StorageOrderDetailMapper extends MyMapper<StorageOrderDetail> {
	List<StorageOrderDetail> getStorageOrderDetailByOrderId(Long id);

//	int insertStorageDetail(StorageDetailDto storageDetailDto);
	
//	List<StorageOrderVo> getStorageOrderList(@Param("storeId") String storeId);

}