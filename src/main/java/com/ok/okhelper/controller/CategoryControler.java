package com.ok.okhelper.controller;

/*
*Author:zhangxin_an
*Description:分类
*Data:Created in 15:55 2018/4/24
*/

import com.ok.okhelper.common.ServerResponse;
import com.ok.okhelper.pojo.vo.CategoryVo;
import com.ok.okhelper.service.CategoryService;
import com.ok.okhelper.service.SupplierService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "分类模块")
@RestController
public class CategoryControler {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CategoryService categoryService;
	
	@ApiOperation(value = "分类查询",notes = "分类查询，父类包含子类")
	@GetMapping("/categorys")
	public ServerResponse<List<CategoryVo>> getAllCategory(){
		logger.info("Enter getAllCategory() params：");
		List<CategoryVo> categoryVoList = categoryService.getCategoryList();
		
		logger.info("Exit getAllCategory() return："+categoryVoList);
		return ServerResponse.createBySuccess(categoryVoList);
	}
	
	
}
