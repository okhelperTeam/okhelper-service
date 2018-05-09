package com.ok.okhelper.dao;

import com.ok.okhelper.pojo.bo.IdAndNameBo;
import com.ok.okhelper.pojo.po.Supplier;
import com.ok.okhelper.util.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SupplierMapper extends MyMapper<Supplier> {
	List<Supplier> getSupplierByStoreId(Long storeId);
	
	IdAndNameBo getIdAndName(Long id);
}