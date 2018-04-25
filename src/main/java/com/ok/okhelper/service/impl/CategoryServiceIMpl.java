package com.ok.okhelper.service.impl;

import com.ok.okhelper.dao.CategoryMapper;
import com.ok.okhelper.pojo.po.Category;
import com.ok.okhelper.pojo.vo.CategoryVo;
import com.ok.okhelper.service.CategoryService;
import com.ok.okhelper.service.SupplierService;
import com.ok.okhelper.shiro.JWTUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
*Author:zhangxin_an
*Description:
*Data:Created in 15:58 2018/4/24
*/
@Service
public class CategoryServiceIMpl implements CategoryService {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CategoryMapper categoryMapper;
	
	
	/*
	* @Author zhangxin_an 
	* @Date 2018/4/24 16:00  
	* @Params []  
	* @Return java.util.List<com.ok.okhelper.pojo.po.Category>
	* @Description:查询分类
	*/  
	@Override
	public List<CategoryVo> getCategoryList(long superId) {
		
		logger.info("Enter getCategoryList() params：");
		
		Long storeId = JWTUtil.getStoreId();
		
		
		List<CategoryVo> categoryVoList = categoryMapper.getCategoryListBySuperId(superId,storeId);
		
		logger.info("Exit getCategoryList() return："+categoryVoList);
		return categoryVoList;
	}
	
	/*
	* @Author zhangxin_an 
	* @Date 2018/4/24 16:56
	* @Params [superId, storeId]  
	* @Return java.util.List<com.ok.okhelper.pojo.vo.CategoryVo>  
	* @Description:递归调用获取子分类
	*/  
	 private  List<CategoryVo> getCategoryItems(long superId,Long storeId){
		List<CategoryVo> categorieList = categoryMapper.getCategoryListBySuperId(superId,storeId);
		if (CollectionUtils.isEmpty(categorieList)){
			return null;
		}else {
			categorieList.forEach(category -> {
				List<CategoryVo> categorieSonList = getCategoryItems(category.getId(),storeId);
				category.setCategoryVoList(categorieSonList);
			});
			return categorieList;
			
		}
	}
}

