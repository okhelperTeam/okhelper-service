package com.ok.okhelper.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ok.okhelper.common.PageModel;
import com.ok.okhelper.dao.ProductMapper;
import com.ok.okhelper.exception.IllegalException;
import com.ok.okhelper.pojo.dto.ProductCondition;
import com.ok.okhelper.pojo.dto.ProductDto;
import com.ok.okhelper.pojo.po.Product;
import com.ok.okhelper.pojo.vo.CategoryVo;
import com.ok.okhelper.pojo.vo.ProductsVo;
import com.ok.okhelper.service.CategoryService;
import com.ok.okhelper.service.ProductService;
import com.ok.okhelper.shiro.JWTUtil;
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
	
	/*
	* @Author zhangxin_an 
	* @Date 2018/4/30 16:19
	* @Params [condition, pageModel]  
	* @Return com.ok.okhelper.common.PageModel<com.ok.okhelper.pojo.po.Supplier>  
	* @Description:查询商品
	*/
	@Override
	public PageModel<ProductsVo> getProductsList(ProductCondition condition, PageModel pageModel) {
		logger.info(" Enter getProductsList()  params:" + condition);
		if (StringUtils.isBlank(condition.getOrderBy())) {
			condition.setOrderBy("create_time desc");
		}
		//启动分页
		PageHelper.startPage(pageModel.getPageNum(), pageModel.getLimit());
		
		//启动排序
		PageHelper.orderBy(condition.getOrderBy());
		List<ProductsVo> productsVos = null;
		
		Long storeId = JWTUtil.getStoreId();
		if (storeId == null) {
			throw new AuthenticationException("登陆异常");
		}
		productsVos = productMapper.getProductsList(condition.getCondition(), storeId);
		
		
		PageInfo<ProductsVo> pageInfo = new PageInfo<>(productsVos);
		logger.info("Exit getProductsList() return:" + pageInfo);
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
	public PageModel<ProductsVo> getProductsListByCategory(Long[] categoryId, String orderBy, PageModel pageModel) {
		
		
		logger.info(" Enter getProductsListByCategory()  params: [categoryId,orderBy,pageModel]" + categoryId + orderBy + pageModel);
		if (categoryId == null) {
			//查询所有
			return getProductsList(new ProductCondition(), pageModel);
		}
		
		//启动分页
		PageHelper.startPage(pageModel.getPageNum(), pageModel.getLimit());
		if (StringUtils.isBlank(orderBy)) {
			orderBy = "create_time desc";
		}
		
		
		//启动排序
		PageHelper.orderBy(orderBy);
		
		Long storeId = JWTUtil.getStoreId();
		if (storeId == null) {
			throw new AuthenticationException("登陆异常");
		}
		//获取所有分类子类
		List<CategoryVo> categoryListTotal = new ArrayList<>();
		for (Long id : categoryId) {
			List<CategoryVo> categoryList = categoryService.getCategoryItems(id, storeId);
			categoryListTotal.addAll(categoryList);
		}
		//遍历获取每一个分类对应的商品
		List<ProductsVo> productsVos = new ArrayList<>();
		productsVos = productMapper.getProductsListByCategoryId(categoryListTotal);
		
		
		PageInfo<ProductsVo> pageInfo = new PageInfo<>(productsVos);
		logger.info("Exit getProductsListByCategory() return:" + pageInfo);
		return PageModel.convertToPageModel(pageInfo);
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
		
		logger.info("Exit getProduct(long pId) return:" + product);
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
		
		productMapper.updateStatus(pId);
		
		logger.info("Exit deleteProduct(long pId) return:");
	}
	
	/*
	* @Author zhangxin_an 
	* @Date 2018/4/30 15:47  
	* @Params [productDto]  
	* @Return void
	* @Description:添加商品
	*/
	@Override
	public void addProduct(ProductDto productDto) {
		logger.info(" Enter  params:" + productDto);
		
		Product product = new Product();
		BeanUtils.copyProperties(productDto, product);
		
		productMapper.insertSelective(product);
		
		logger.info("Exit addProduct(ProductDto productDto) return:");
		
	}
	
	/*
	* @Author zhangxin_an 
	* @Date 2018/4/30 15:47
	* @Params [productDto]  
	* @Return void  
	* @Description:修改商品
	*/
	@Override
	public void updateProduct(ProductDto productDto) {
		logger.info(" Enter updateProduct(ProductDto productDto) params:" + productDto);
		if (productDto.getId() == null) {
			throw new IllegalException("参数为空");
		}
		Product product = new Product();
		BeanUtils.copyProperties(productDto, product);
		
		productMapper.updateByPrimaryKeySelective(product);
		
		
		logger.info("Exit updateProduct(ProductDto productDto)  return:");
		
	}
}
