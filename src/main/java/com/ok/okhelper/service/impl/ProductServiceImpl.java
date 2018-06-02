package com.ok.okhelper.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ok.okhelper.common.PageModel;
import com.ok.okhelper.dao.ProductMapper;
import com.ok.okhelper.dao.StockMapper;
import com.ok.okhelper.dao.WarehouseMapper;
import com.ok.okhelper.exception.IllegalException;
import com.ok.okhelper.exception.NotFoundException;
import com.ok.okhelper.pojo.bo.IdAndNameBo;
import com.ok.okhelper.pojo.bo.ProductStockBo;
import com.ok.okhelper.pojo.bo.StockBo;
import com.ok.okhelper.pojo.dto.ProductDto;
import com.ok.okhelper.pojo.po.Product;
import com.ok.okhelper.pojo.vo.*;
import com.ok.okhelper.service.CategoryService;
import com.ok.okhelper.service.ProductService;
import com.ok.okhelper.service.StockService;
import com.ok.okhelper.shiro.JWTUtil;
import com.ok.okhelper.util.NumberGenerator;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
 *Author:zhangxin_an
 *Description:商品
 *Data:Created in 15:06 2018/4/30
 */
@Service
public class ProductServiceImpl implements ProductService {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ProductMapper productMapper;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	StockMapper stockMapper;
	
	@Autowired
	WarehouseMapper warehouseMapper;
	
	@Autowired
	StockService stockService;
	
	
	/*
	 * @Author zhangxin_an
	 * @Date 2018/4/30 16:19
	 * @Params [condition, pageModel]
	 * @Return com.ok.okhelper.common.PageModel<com.ok.okhelper.pojo.po.Supplier>
	 * @Description:查询商品
	 */
	@Override
	public PageModel<ProductsVo> getProductsList(String condition, PageModel pageModel) {
		logger.info(" Enter getProductsList()  params:" + condition);
		
		//启动分页
		PageHelper.startPage(pageModel.getPageNum(), pageModel.getLimit());
		
		//启动排序
		PageHelper.orderBy(pageModel.getOrderBy());
		List<ProductsVo> productsVos = null;
		
		Long storeId = JWTUtil.getStoreId();
		if (storeId == null) {
			throw new AuthenticationException("登陆异常");
		}
		productsVos = productMapper.getProductsList(condition, storeId);
		
		PageInfo<ProductsVo> pageInfo = new PageInfo<>(productsVos);
		logger.info("Exit method getProductsList() return:" + pageInfo);
		return PageModel.convertToPageModel(pageInfo);
	}
	
	/*
	 * @Author zhangxin_an
	 * @Date 2018/4/30 23:15
	 * @Params [categoryId, orderBy, pageModel]
	 * @Return com.ok.okhelper.common.PageModel<com.ok.okhelper.pojo.vo.ProductsVo>
	 * @Description:根据分类查询商品
	 */
	@Override
	public PageModel<ProductsVo> getProductsListByCategory(Long categoryId, PageModel pageModel) {
		
		
		logger.info(" Enter getProductsListByCategory()  params: [categoryId,orderBy,pageModel]" + categoryId + pageModel);
		
		if (categoryId == null) {
			return getProductsList(null, pageModel);
		}
		
		Long storeId = JWTUtil.getStoreId();
		if (storeId == null) {
			throw new AuthenticationException("登陆异常");
		}
		
		//获取分类子类
		List<CategoryVo> categoryList = categoryService.getCategoryAllItems(categoryId, storeId);
		
		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setId(categoryId);
		categoryList.add(categoryVo);
		//启动分页
		PageHelper.startPage(pageModel.getPageNum(), pageModel.getLimit());
		
		//启动排序
		PageHelper.orderBy(pageModel.getOrderBy());
		
		
		//遍历获取每一个分类对应的商品
		List<ProductsVo> productsVos = productMapper.getProductsListByCategoryId(JWTUtil.getStoreId(),categoryList);
		
		
		PageInfo<ProductsVo> pageInfo = new PageInfo<>(productsVos);
		logger.info("Exit method getProductsListByCategory() return:" + pageInfo);
		return PageModel.convertToPageModel(pageInfo);
	}
	
	@Override
	public ProductsVo getProductsListBybarCode(String barCode) {
		Product product = new Product();
		product.setStoreId(JWTUtil.getStoreId());
		product.setBarCode(barCode);
		Product dbproduct = productMapper.selectOne(product);
		
		if (dbproduct == null) {
			throw new NotFoundException("没有找到相关商品");
		}
		
		ProductsVo productsVo = new ProductsVo();
		BeanUtils.copyProperties(dbproduct, productsVo);
		
		return productsVo;
	}
	
	/*
	 * @Author zhangxin_an
	 * @Date 2018/4/30 15:47
	 * @Params [pId]
	 * @Return com.ok.okhelper.pojo.po.Product
	 * @Description:获取单个商品
	 */
	@Override
	public Product getProduct(Long pId) {
		logger.info(" Enter  params:" + pId);
		
		if (pId == null) {
			throw new IllegalException("参数为空");
		}
		
		Product product = productMapper.selectByPrimaryKey(pId);
		
		if (product == null || product.getDeleteStatus() == 0 || !product.getStoreId().equals(JWTUtil.getStoreId())) {
			throw new NotFoundException("查询不存在");
		}
		
		logger.info("Exit method getProduct(long pId) return:" + product);
		return product;
	}
	
	/*
	 * @Author zhangxin_an
	 * @Date 2018/4/30 15:47
	 * @Params [pId]
	 * @Return void
	 * @Description:删除商品
	 */
	@Override
	public void deleteProduct(Long pId) {
		logger.info(" Enter  params:" + pId);
		
		if (pId == null) {
			throw new IllegalException("参数为空");
		}
		
		productMapper.updateStatus(pId,JWTUtil.getStoreId());
		
		logger.info("Exit method deleteProduct(long pId) return:");
	}
	
	/*
	 * @Author zhangxin_an
	 * @Date 2018/4/30 15:47
	 * @Params [productDto]
	 * @Return void
	 * @Description:添加商品
	 */
	@Override
	public Product addProduct(ProductDto productDto) {
		logger.info(" Enter  params:" + productDto);
		
		
		Product product = covertProduct(productDto);
		
		//没有条码，生成
		if (StringUtils.isBlank(product.getBarCode())) {
			product.setBarCode(NumberGenerator.generatorBarCode());
		}
		
		if (StringUtils.isBlank(product.getUnit())) {
			product.setUnit("个");
		}
		
		product.setStoreId(JWTUtil.getStoreId());
		product.setOperator(JWTUtil.getUserId());
		productMapper.insertSelective(product);
		
		product = productMapper.selectByPrimaryKey(product.getId());
		
		logger.info("Exit method addProduct(ProductDto productDto) return:" + product);
		return product;
		
	}
	
	/*
	 * @Author zhangxin_an
	 * @Date 2018/4/30 15:47
	 * @Params [productDto]
	 * @Return void
	 * @Description:修改商品
	 */
	@Override
	public Product updateProduct(ProductDto productDto) {
		logger.info(" Enter updateProduct(ProductDto productDto) params:" + productDto);
		if (productDto.getId() == null) {
			throw new IllegalException("参数为空");
		}
		
		Product product = covertProduct(productDto);
		
		
		productMapper.updateByPrimaryKeySelective(product);
		
		product = productMapper.selectByPrimaryKey(product.getId());
		
		
		logger.info("Exit method updateProduct(ProductDto productDto)  return:" + product);
		
		return product;
		
	}
	
	private Product covertProduct(ProductDto productDto) {
		
		Product product = new Product();
		BeanUtils.copyProperties(productDto, product);
		
		String[] subImg = productDto.getSubImgs();
		if(subImg == null){
			return product;
		}
		String img = String.join(",", subImg);
		product.setSubImgs(img);
		
		return product;
	}
	
	/*
	 * @Author zhangxin_an
	 * @Date 2018/5/7 15:06
	 * @Params [days, pageModel]临近天数
	 * @Return java.util.List<com.ok.okhelper.pojo.po.Product>
	 * @Description:获取临期商品
	 */
	@Override
	public PageModel<ProductNearDayVo> getNearDaysProduct(Integer days, PageModel pageModel) {
		logger.info("Enter getNearDaysProduct params:days:" + days + "+pageModel:" + pageModel);
		
		if (days == null) {
			days = 0;
		}
		//启动分页
		PageHelper.startPage(pageModel.getPageNum(), pageModel.getLimit());
		
		//启动排序
		PageHelper.orderBy(pageModel.getOrderBy());
		
		//临期商品
		List<StockBo> stocksList = stockMapper.getNearDaysProduct(days, JWTUtil.getStoreId());
		
		if (CollectionUtils.isEmpty(stocksList)) {
			return null;
		}
		
		List<ProductNearDayVo> productNearDayVoList = new ArrayList<>(stocksList.size());
		ProductNearDayVo productNearDayVo;
		
		for (StockBo stock : stocksList) {
			
			productNearDayVo = new ProductNearDayVo();
			//获取仓库名
			IdAndNameBo warehouseMapperIdAndName = warehouseMapper.getIdAndName(stock.getWarehouseId());
			Product product = productMapper.selectByPrimaryKey(stock.getProductId());
			BeanUtils.copyProperties(product, productNearDayVo);
			productNearDayVo.setProductDate(stock.getProductDate());
			productNearDayVo.setShelfLife(stock.getShelfLife());
			productNearDayVo.setWarehouse(warehouseMapperIdAndName);
			productNearDayVo.setStockCount(stock.getStockCount());
			productNearDayVo.setOverDay(stock.getOverDay());
			
			productNearDayVoList.add(productNearDayVo);
		}
		
		
		PageInfo pageInfo = new PageInfo<>(stocksList);
		pageInfo.setList(productNearDayVoList);
		logger.info("Exit method getNearDaysProduct params: return:" + pageInfo);
		return PageModel.convertToPageModel(pageInfo);
		
	}
	
	/*
	 * @Author zhangxin_an
	 * @Date 2018/5/14 16:43
	 * @Params [numbers, pageModel]
	 * @Return com.ok.okhelper.common.PageModel<com.ok.okhelper.pojo.vo.ProductStockVo>
	 * @Description:低库存商品
	 */
	@Override
	public PageModel<ProductStockVo> getLowCountProduct(Integer numbers, PageModel pageModel) {
		
		logger.info("Enter method getLowCountProduct params: " + numbers);
		
		//启动分页
		PageHelper.startPage(pageModel.getPageNum(), pageModel.getLimit());
		
		//启动排序
		PageHelper.orderBy("total_stock");
		
		//商品对应库存
		List<ProductStockBo> productStockBos = stockMapper.getLowCountProductsList(numbers, JWTUtil.getStoreId());
		if (CollectionUtils.isEmpty(productStockBos)) {
			PageInfo pageInfo = new PageInfo<>(null);
			return PageModel.convertToPageModel(pageInfo);
		}
		
		//前端视图
		List<ProductStockVo> productStockVos = new ArrayList<>(productStockBos.size());
		ProductStockVo productStockVo;
		for (ProductStockBo productStockBo : productStockBos) {
			productStockVo = productMapper.getProductStockVoByPK(productStockBo.getProductId());
			productStockVo.setTotalStock(productStockBo.getTotalStock());
			productStockVos.add(productStockVo);
		}
		
		
		PageInfo pageInfo = new PageInfo<>(productStockVos);
		logger.info("Exit method getLowCountProduct  return:" + pageInfo);
		return PageModel.convertToPageModel(pageInfo);
		
		
	}
	
	/*
	 * @Author zhangxin_an
	 * @Date 2018/5/14 16:43
	 * @Params [pid]
	 * @Return com.ok.okhelper.common.PageModel<com.ok.okhelper.pojo.vo.StockByBatchVo>
	 * @Description:商品各批次库存
	 */
	@Override
	public List<StockByBatchVo> getstockWithBatch(Long pid) {
		
		logger.info("Enter method getLowCountProduct params: " + pid);
		
		if( pid == null){
			throw new IllegalException("商品id为空");
		}
		
		//获取该商品批次信息
		List<StockBo> stockBos = stockService.getStockBoByPid(pid);
		
		
		
		//前端视图
		List<StockByBatchVo> stockByBatchVos = new ArrayList<>(stockBos.size());
		stockBos.forEach(stockBo -> {
			IdAndNameBo warehouse = warehouseMapper.getIdAndName(stockBo.getWarehouseId());
			IdAndNameBo product = productMapper.getIdAndName(stockBo.getProductId());
			StockByBatchVo stockByBatchVo = new StockByBatchVo();
			stockByBatchVo.setProduct(product);
			stockByBatchVo.setWarehouse(warehouse);
			stockByBatchVo.setProductDate(stockBo.getProductDate());
			stockByBatchVo.setShelfLife(stockBo.getShelfLife());
			stockByBatchVo.setStockCount(stockBo.getStockCount());
			stockByBatchVos.add(stockByBatchVo);
		});
		
		
		
		logger.info("Exit method getLowCountProduct  return:" + stockByBatchVos);
		
		return stockByBatchVos;
	}
}
