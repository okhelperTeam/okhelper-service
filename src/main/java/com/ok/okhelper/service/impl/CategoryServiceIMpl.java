package com.ok.okhelper.service.impl;

import com.ok.okhelper.dao.CategoryMapper;
import com.ok.okhelper.pojo.po.Category;
import com.ok.okhelper.pojo.vo.CategoryVo;
import com.ok.okhelper.service.CategoryService;
import com.ok.okhelper.shiro.JWTUtil;
import org.apache.commons.collections.CollectionUtils;
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
		
		logger.info("Enter method getCategoryList() params：");
		
		Long storeId = JWTUtil.getStoreId();
		if(storeId == null){
			throw new AuthenticationException("登陆异常");
		}
		
		List<CategoryVo> categoryVoList = getCategoryItems1(superId, storeId);
		
		logger.info("Exit method getCategoryList() return：" + categoryVoList);
		return categoryVoList;
	}
	
	
	
	
	/*
	 * @Author zhangxin_an
	 * @Date 2018/4/24 16:56
	 * @Params [superId, storeId]
	 * @Return java.util.List<com.ok.okhelper.pojo.vo.CategoryVo>
	 * @Description:递归调用获取子分类
	 */
	private  List<CategoryVo> getCategoryItems1(long superId,Long storeId) {
		List<CategoryVo> categorieList = categoryMapper.getCategoryListBySuperId(superId, storeId);
		if (CollectionUtils.isEmpty(categorieList)) {
			return null;
		} else {
			categorieList.forEach(category -> {
				List<CategoryVo> categorieSonList = getCategoryItemsNoSelf(category.getId(), storeId);
				category.setCategoryVoList(categorieSonList);
			});
			return categorieList;
			
		}
		
	}
	
	
	/*
	* @Author zhangxin_an 
	* @Date 2018/4/24 16:56
	* @Params [superId, storeId]  
	* @Return java.util.List<com.ok.okhelper.pojo.vo.CategoryVo>  
	* @Description:获取当前类的所有子类
	*/
	public List<CategoryVo> getCategoryItems(long superId, Long storeId) {
		
		logger.info("Enter method getCategoryItems() params："+superId);
		
		//存储所有包含
		List<CategoryVo> categorieListTotal = new ArrayList<>();
		
		//添加自身
		Category category = categoryMapper.selectByPrimaryKey(superId);
		CategoryVo categoryVo1 = new CategoryVo();
		BeanUtils.copyProperties(category,categoryVo1);
		categorieListTotal.add(categoryVo1);
		
		
		List<CategoryVo> categorieList = categoryMapper.getCategoryListBySuperId(superId, storeId);
		
		categorieListTotal.addAll(categorieList);
		while (!CollectionUtils.isEmpty(categorieList)) {
			if (!CollectionUtils.isEmpty(categorieList)) {
				
				for (CategoryVo categoryVo : categorieList) {
					
					categorieList = categoryMapper.getCategoryListBySuperId(categoryVo.getId(), storeId);
					if (CollectionUtils.isEmpty(categorieList)) {
						continue;
					}
					
					categorieListTotal.addAll(categorieList);
					
				}
			}
		}
		
		logger.info("Enter method getCategoryItems() params："+categorieListTotal);
		return categorieListTotal;
		
	}

	public List<CategoryVo> getCategoryItemsNoSelf(long superId, Long storeId) {
		
		logger.info("Enter method getCategoryItems() params："+superId);
		
		//存储所有包含
		List<CategoryVo> categorieListTotal = new ArrayList<>();
		
		
		
		
		List<CategoryVo> categorieList = categoryMapper.getCategoryListBySuperId(superId, storeId);
		
		categorieListTotal.addAll(categorieList);
		while (!CollectionUtils.isEmpty(categorieList)) {
			if (!CollectionUtils.isEmpty(categorieList)) {
				
				for (CategoryVo categoryVo : categorieList) {
					
					categorieList = categoryMapper.getCategoryListBySuperId(categoryVo.getId(), storeId);
					if (CollectionUtils.isEmpty(categorieList)) {
						continue;
					}
					
					categorieListTotal.addAll(categorieList);
					
				}
			}
		}
		
		logger.info("Enter method getCategoryItems() params："+categorieListTotal);
		return categorieListTotal;
		
	}
	
	
}


