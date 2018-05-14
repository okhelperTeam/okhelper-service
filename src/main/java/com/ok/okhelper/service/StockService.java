package com.ok.okhelper.service;

import com.ok.okhelper.pojo.bo.StockBo;
import com.ok.okhelper.pojo.po.Stock;

import java.util.List;

/*
*Author:zhangxin_an
*Description:库存
*Data:Created in 16:59 2018/5/3
*/
public interface StockService {
	
	Stock updateOrAddStockNumber(Stock stock,int addNumber);
	
	List<StockBo> getStockBoByPid(Long pid);
}
