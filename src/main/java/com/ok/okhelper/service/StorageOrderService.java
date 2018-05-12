package com.ok.okhelper.service;

/*
*Author:zhangxin_an
*Description:
*Data:Created in 10:11 2018/5/3
*/

import com.ok.okhelper.common.PageModel;
import com.ok.okhelper.pojo.dto.StorageOrderDto;
import com.ok.okhelper.pojo.vo.StorageOrderVo;

public interface StorageOrderService {
	
	void insertStorage(StorageOrderDto storageOrderDto);
	
	
	
	StorageOrderVo getStorageOrderByOrderNumber(String orderNumber);
	
	PageModel<StorageOrderVo> getStorageOrderList(PageModel pageModel);
	
	PageModel<StorageOrderVo> getStorageOrderListBySupplierId(PageModel pageModel, Long supplierId);
}
