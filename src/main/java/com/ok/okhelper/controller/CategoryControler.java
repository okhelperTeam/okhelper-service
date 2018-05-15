package com.ok.okhelper.controller;

/*
*Author:zhangxin_an
*Description:分类
*Data:Created in 15:55 2018/4/24
*/

import com.ok.okhelper.common.ServerResponse;
import com.ok.okhelper.pojo.bo.IdAndNameBo;
import com.ok.okhelper.pojo.dto.CategoryDto;
import com.ok.okhelper.pojo.po.Category;
import com.ok.okhelper.pojo.vo.CategoryVo;
import com.ok.okhelper.service.CategoryService;
import com.ok.okhelper.service.SaleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "分类模块")
@RestController
public class CategoryControler {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CategoryService categoryService;
	
	@RequiresPermissions("category:view")
	@ApiOperation(value = "分类查询",notes = "分类查询，父类包含子类")
	@GetMapping("/categorys/{id:\\d+}")
	public ServerResponse<List<CategoryVo>> getAllCategory(@PathVariable long id){
		logger.info("Enter method getAllCategory() params：");
		List<CategoryVo> categoryVoList = categoryService.getCategoryList(id);
		
		logger.info("Exit method getAllCategory() return："+categoryVoList);
		return ServerResponse.createBySuccess(categoryVoList);
	}
	
	@RequiresPermissions("category:view")
	@ApiOperation(value = "所有类查询",notes = "只查询类名id")
	@GetMapping("/categorys")
	public ServerResponse<List<IdAndNameBo>> getAllCategory(){
		logger.info("Enter method getAllCategory() params：");
		List<IdAndNameBo> categoryVoList = categoryService.getCategoryIdAndNameList();
		
		logger.info("Exit method getAllCategory() return："+categoryVoList);
		return ServerResponse.createBySuccess(categoryVoList);
	}
	
	
	@RequiresPermissions("category:edit")
	@ApiOperation(value = "分类删除",notes = "分类删除，只能删除子类")
	@DeleteMapping("/categorys/{id:\\d+}")
	public ServerResponse<Integer> deleteCategory(@PathVariable long id){
		logger.info("Enter method getAllCategory() params：");
		int i = categoryService.deleteCategory(id);
		ServerResponse<Integer> serverResponse = ServerResponse.createBySuccess(i);
		logger.info("Exit method getAllCategory() return："+serverResponse);
		return serverResponse;
	}
	
	@RequiresPermissions("category:edit")
	@ApiOperation(value = "分类添加",notes = "分类添加")
	@PostMapping("/categorys")
	public ServerResponse<String> addCategory(@Valid CategoryDto categoryDto){
		logger.info("Enter method addCategory() params："+categoryDto);
		categoryService.addCategory(categoryDto);
		logger.info("Exit method getAllCategory() return：");
		return ServerResponse.createBySuccess("添加成功");
	}
	
	@RequiresPermissions("category:view")
	@ApiOperation(value = "当前分类查询")
	@GetMapping("/categoryself/{id:\\d+}")
	public ServerResponse<Category> getCategoryById(@PathVariable Long  id){
		logger.info("Enter method getAllCategory() params：");
		Category category = categoryService.getCategoryById(id);
		
		logger.info("Exit method getAllCategory() return："+category);
		return ServerResponse.createBySuccess(category);
	}
	
	
	
	
}
