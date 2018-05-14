package com.ok.okhelper.dao;

import com.ok.okhelper.pojo.bo.ProductStockBo;
import com.ok.okhelper.pojo.bo.StockBo;
import com.ok.okhelper.pojo.po.Stock;
import com.ok.okhelper.pojo.vo.ProductsVo;
import com.ok.okhelper.shiro.JWTUtil;
import com.ok.okhelper.util.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockMapper extends MyMapper<Stock> {
	List<StockBo> getNearDaysProduct(@Param("days") Integer days, @Param("storeId") Long storeId);
	
	List<ProductStockBo> getLowCountProductsList(@Param("numbers") Integer numbers, @Param("storeId") Long storeId);
	
	List<StockBo> getStockBoByPid(Long pid);
}