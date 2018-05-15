package com.ok.okhelper.service;

import com.ok.okhelper.pojo.bo.IdAndNameBo;
import com.ok.okhelper.pojo.dto.CategoryDto;
import com.ok.okhelper.pojo.po.Category;
import com.ok.okhelper.pojo.vo.CategoryVo;

import java.util.List;

/*
*Author:zhangxin_an
*Description:
*Data:Created in 15:57 2018/4/24
*/
public interface CategoryService {
	List<CategoryVo> getCategoryList(long supId);
	List<CategoryVo> getCategoryAllItems(long superId, Long storeId);
	
	int deleteCategory(long id);
	
	void addCategory(CategoryDto categoryDto);
	
	List<IdAndNameBo> getCategoryIdAndNameList();
	
	Category getCategoryById(Long cId);
}
