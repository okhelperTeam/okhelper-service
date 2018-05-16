package com.ok.okhelper.controller;

import com.ok.okhelper.common.PageModel;
import com.ok.okhelper.common.ServerResponse;
import com.ok.okhelper.pojo.dto.ProductDto;
import com.ok.okhelper.pojo.po.Product;
import com.ok.okhelper.pojo.vo.ProductNearDayVo;
import com.ok.okhelper.pojo.vo.ProductStockVo;
import com.ok.okhelper.pojo.vo.ProductsVo;
import com.ok.okhelper.pojo.vo.StockByBatchVo;
import com.ok.okhelper.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
	
	@RequiresPermissions("product:view")
	@ApiOperation(value = "商品搜索", notes = "指定查询条件搜索")
	@GetMapping("product/search")
	public ServerResponse<PageModel<ProductsVo>> searchProduct(String condition, @Valid PageModel pageModel) {
		logger.info("Enter method searchProduct params:productCondition:" + condition + "pageModel:" + pageModel);
		ServerResponse<PageModel<ProductsVo>> serverResponse;
		PageModel<ProductsVo> productsVoPageModel = productService.getProductsList(condition == null ? null : condition.trim(), pageModel);
		serverResponse = ServerResponse.createBySuccess(productsVoPageModel);
		
		
		logger.info("Exit method searchProduct params:" + serverResponse);
		return serverResponse;
	}
	
	/*
	 * @Author zhangxin_an
	 * @Date 2018/5/1 15:45
	 * @Params [categoryIds, orderBy, pageModel]
	 * @Return com.ok.okhelper.common.ServerResponse<com.ok.okhelper.common.PageModel<com.ok.okhelper.pojo.vo.ProductsVo>>
	 * @Description:分类选择商品
	 */
	@RequiresPermissions("product:view")
	@ApiOperation(value = "商品搜索", notes = "通过分类搜索")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "categoryId", value = "分类Id", required = true, paramType = "form"),
	})
	@GetMapping("product/category")
	public ServerResponse<PageModel<ProductsVo>> searchProductBycategory(Long categoryId, @Valid PageModel pageModel) {
		logger.info("Enter method searchProduct params:categoryId:" + categoryId + "pageModel:" + pageModel);
		
		
		ServerResponse<PageModel<ProductsVo>> serverResponse;
		PageModel<ProductsVo> productsVoPageModel = productService.getProductsListByCategory(categoryId, pageModel);
		serverResponse = ServerResponse.createBySuccess(productsVoPageModel);
		
		
		logger.info("Exit method searchProduct params:" + serverResponse);
		return serverResponse;
	}
	
	@RequiresPermissions("product:edit")
	@ApiOperation(value = "商品删除")
	@DeleteMapping("product/{id:\\d+}")
	public ServerResponse deleteProduct(@PathVariable Long id) {
		logger.info("Enter method deleteProduct params:productCondition:" + id);
		ServerResponse serverResponse;
		
		productService.deleteProduct(id);
		serverResponse = ServerResponse.createBySuccessMessage("删除成功");
		
		
		logger.info("Exit method deleteProduct params:" + serverResponse);
		return serverResponse;
	}
	
	@RequiresPermissions("product:edit")
	@ApiOperation(value = "商品添加")
	@PostMapping("product")
	public ServerResponse<Product> addProduct(@Valid ProductDto productDto) {
		logger.info("Enter method addProduct params:productDto:" + productDto);
		
		ServerResponse serverResponse;
		
		
		serverResponse = ServerResponse.createBySuccess("添加成功", productService.addProduct(productDto));
		
		
		logger.info("Exit method addProduct params:" + serverResponse);
		return serverResponse;
	}
	
	@RequiresPermissions("product:edit")
	@ApiOperation(value = "商品修改")
	@PutMapping("product")
	public ServerResponse<Product> updateProduct(ProductDto productDto) {
		logger.info("Enter method addProduct params:productDto:" + productDto);
		ServerResponse serverResponse;
		
		serverResponse = ServerResponse.createBySuccess("修改成功", productService.updateProduct(productDto));
		
		
		logger.info("Exit method addProduct params:" + serverResponse);
		return serverResponse;
	}
	
	
	@RequiresPermissions("product:view")
	@ApiOperation(value = "查询单个商品")
	@GetMapping("product/{id:\\d+}")
	public ServerResponse<Product> getProduct(@PathVariable Long id) {
		logger.info("Enter method getProduct params:id:" + id);
		Product products = productService.getProduct(id);
		
		ServerResponse<Product> serverResponse = ServerResponse.createBySuccess(products);
		logger.info("Exit method getProduct params:" + serverResponse);
		return serverResponse;
	}
	
	@RequiresPermissions("product:view")
	@ApiOperation(value = "查询临期商品")
	@GetMapping("product/nearDay/{days}")
	public ServerResponse<PageModel<ProductNearDayVo>> getNearDaysProduct(@PathVariable Integer days, @Valid PageModel pageModel) {
		logger.info("Enter method getNearDaysProduct params:days:" + days);
		PageModel<ProductNearDayVo> productNearDayVoPageModel = productService.getNearDaysProduct(days, pageModel);
		ServerResponse serverResponse = ServerResponse.createBySuccess(productNearDayVoPageModel);
		logger.info("Exit method getNearDaysProduct return:" + serverResponse);
		return serverResponse;
	}
	
	
	@RequiresPermissions("product:view")
	@ApiOperation(value = "商品搜索", notes = "通过条形码搜索")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "barCode", value = "商品条码", required = true, paramType = "form"),
	})
	@GetMapping("product/bar_code/{barCode}")
	public ServerResponse<ProductsVo> searchProductBybarCode(@PathVariable String barCode) {
		logger.info("Enter method searchProduct params:barCode:" + barCode);
		
		ServerResponse<ProductsVo> serverResponse;
		ProductsVo productsBybarCode = productService.getProductsListBybarCode(barCode);
		serverResponse = ServerResponse.createBySuccess(productsBybarCode);
		
		logger.info("Exit method searchProduct params:" + serverResponse);
		return serverResponse;
	}
	
	@RequiresPermissions("product:view")
	@ApiOperation(value = "商品库存预警")
	@GetMapping("product/lowWarning/{numbers}")
	public ServerResponse<PageModel<ProductStockVo>> getLowCountProduct(@PathVariable Integer numbers, @Valid PageModel pageModel) {
		logger.info("Enter method getNearDaysProduct params:days:" + numbers);
		PageModel<ProductStockVo> lowCountProducts = productService.getLowCountProduct(numbers, pageModel);
		ServerResponse serverResponse = ServerResponse.createBySuccess(lowCountProducts);
		logger.info("Exit method getNearDaysProduct return:" + serverResponse);
		return serverResponse;
	}
	
	@RequiresPermissions("stock:view")
	@ApiOperation(value = "单商品各批次库存")
	@GetMapping("product/stockWithBatch/{pid}")
	public ServerResponse<PageModel<StockByBatchVo>> getstockWithBatch(@PathVariable(required = true) Long pid) {
		logger.info("Enter method getstockWithBatch params:pid:" + pid);
		List<StockByBatchVo> stockByBatchVos = productService.getstockWithBatch(pid);
		ServerResponse serverResponse = ServerResponse.createBySuccess(stockByBatchVos);
		logger.info("Exit method getstockWithBatch return:" + serverResponse);
		return serverResponse;
	}
	
	
}
