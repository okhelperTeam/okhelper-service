package com.ok.okhelper.dao;

import com.ok.okhelper.pojo.po.Stock;
import com.ok.okhelper.until.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockMapper extends MyMapper<Stock> {
	List<Stock> getNearDaysProduct(Integer days);
}