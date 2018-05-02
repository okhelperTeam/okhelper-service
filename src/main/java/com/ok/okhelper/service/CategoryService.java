package com.ok.okhelper.service;

import com.ok.okhelper.pojo.vo.CategoryVo;

import java.util.List;

/*
*Author:zhangxin_an
*Description:
*Data:Created in 15:57 2018/4/24
*/
public interface CategoryService {
	List<CategoryVo> getCategoryList(long supId);
	List<CategoryVo> getCategoryItems(long superId, Long storeId);
}
