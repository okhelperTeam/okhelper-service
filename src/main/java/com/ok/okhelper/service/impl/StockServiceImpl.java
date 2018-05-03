package com.ok.okhelper.service.impl;

import com.ok.okhelper.dao.StockMapper;
import com.ok.okhelper.exception.IllegalException;
import com.ok.okhelper.pojo.po.Stock;
import com.ok.okhelper.service.StockService;
import com.ok.okhelper.shiro.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
*Author:zhangxin_an
*Description:
*Data:Created in 17:02 2018/5/3
*/
@Slf4j
@Service
public class StockServiceImpl implements StockService {
	
	@Autowired
	StockMapper stockMapper;
	
	/*
	* @Author zhangxin_an 
	* @Date 2018/5/3 17:25  
	* @Params [stock, addNumber]  
	* @Return int
	* @Description:更新库存
	*/  
	@Override
	public Stock updateOrAddStockNumber(Stock stock,int addNumber) {
		stock.setStoreId(JWTUtil.getStoreId());
		
		Stock stock1 = getOnlyOneStock(stock);
		
		
		
		stock.setOperator(JWTUtil.getUserId());
		if( stock1 == null ){
			if(addNumber < 0){
				throw  new IllegalException("库存不足");
			}
			stock.setStockCount((long)addNumber);
			stockMapper.insertSelective(stock);
		}else {
			stock1.setStockCount(stock1.getStockCount()+addNumber);
			stockMapper.updateByPrimaryKeySelective(stock1);
		}
		
		return stock1;
	}
	
	
	
	private Stock getOnlyOneStock(Stock stock){
		List<Stock> stockList =  stockMapper.select(stock);
		if(CollectionUtils.isEmpty(stockList)){
			return null;
		}
		return stockList.get(0);
	}
	
	
}
