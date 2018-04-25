package com.ok.okhelper.dao;

import com.ok.okhelper.pojo.po.Supplier;
import com.ok.okhelper.until.MyMapper;

import java.util.List;

public interface SupplierMapper extends MyMapper<Supplier> {
	List<Supplier> getSupplierByStoreId(Long storeId);
}