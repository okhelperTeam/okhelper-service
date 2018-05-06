package com.ok.okhelper.dao;

import com.ok.okhelper.pojo.po.StorageOrderDetail;
import com.ok.okhelper.pojo.vo.StorageOrderVo;
import com.ok.okhelper.until.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StorageOrderDetailMapper extends MyMapper<StorageOrderDetail> {
	List<StorageOrderDetail> getStorageOrderDetailByOrderId(Long id);

//	int insertStorageDetail(StorageDetailDto storageDetailDto);
	
//	List<StorageOrderVo> getStorageOrderList(@Param("storeId") String storeId);

}