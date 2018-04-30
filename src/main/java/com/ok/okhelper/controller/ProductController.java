package com.ok.okhelper.controller;

import com.ok.okhelper.common.PageModel;
import com.ok.okhelper.common.ServerResponse;
import com.ok.okhelper.pojo.dto.ProductCondition;
import com.ok.okhelper.pojo.vo.ProductsVo;
import com.ok.okhelper.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
*Author:zhangxin_an
*Description:
*Data:Created in 16:36 2018/4/30
*/
@Api(tags = "商品模块")
@RestController
public class ProductController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ProductService productService;
	
	@ApiOperation(value = "商品搜索",notes = "指定查询条件搜索")
	@GetMapping("product/search")
	public ServerResponse<PageModel<ProductsVo>> searchProduct(ProductCondition productCondition, PageModel pageModel){
		logger.info("Enter searchProduct params:productCondition:"+productCondition+"pageModel:"+pageModel);
		PageModel<ProductsVo> productsVoPageModel = productService.getProductsList(productCondition,pageModel);
		ServerResponse<PageModel<ProductsVo>> serverResponse = ServerResponse.createBySuccess(productsVoPageModel);
		logger.info("Exit searchProduct params:"+ serverResponse);
		return serverResponse;
	}
	
	
	
	
}
