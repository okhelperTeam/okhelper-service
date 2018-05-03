package com.ok.okhelper.service;

/*
*Author:zhangxin_an
*Description:
*Data:Created in 10:11 2018/5/3
*/

import com.ok.okhelper.pojo.dto.StorageOrderDto;
import com.ok.okhelper.pojo.vo.StorageOrderVo;

public interface StorageOrderService {
	
	StorageOrderVo insertStorage(StorageOrderDto storageOrderDto);
	
	
	
	StorageOrderVo getStorageOrderByOrderNumber(String orderNumber);
	
	
	
}
