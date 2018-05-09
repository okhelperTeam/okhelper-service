package com.ok.okhelper.dao;

import com.ok.okhelper.pojo.po.StorageOrder;
import com.ok.okhelper.util.MyMapper;

public interface StorageOrderMapper extends MyMapper<StorageOrder> {
	
	StorageOrder getStorageOrderByOrderNumber(String orderNumber);
}