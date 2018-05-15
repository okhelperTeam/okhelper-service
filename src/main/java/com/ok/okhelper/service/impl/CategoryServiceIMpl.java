package com.ok.okhelper.service.impl;

import com.ok.okhelper.dao.CategoryMapper;
import com.ok.okhelper.exception.IllegalException;
import com.ok.okhelper.exception.NotFoundException;
import com.ok.okhelper.pojo.bo.IdAndNameBo;
import com.ok.okhelper.pojo.dto.CategoryDto;
import com.ok.okhelper.pojo.po.Category;
import com.ok.okhelper.pojo.vo.CategoryVo;
import com.ok.okhelper.service.CategoryService;
import com.ok.okhelper.shiro.JWTUtil;
import io.netty.util.internal.StringUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		
		List<CategoryVo> categoryVoList = getCategoryItems(superId, storeId);
		if(CollectionUtils.isEmpty(categoryVoList)){
			throw new NotFoundException("没有相关分类");
		}
		
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
	public  List<CategoryVo> getCategoryItems(long superId,Long storeId) {
		List<CategoryVo> categorieList = categoryMapper.getCategoryListBySuperId(superId, storeId);
		if (CollectionUtils.isEmpty(categorieList)) {
			return null;
		} else {
			categorieList.forEach(category -> {
				List<CategoryVo> categorieSonList = getCategoryItems(category.getId(), storeId);
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
	public List<CategoryVo> getCategoryAllItems(long superId, Long storeId) {
		
		logger.info("Enter method getCategoryItems() params："+superId);
		
		//存储所有包含
		List<CategoryVo> categorieListTotal = new ArrayList<>();
		
		//添加自身
		Category category = categoryMapper.selectByPrimaryKey(superId);
		if(category != null) {
			CategoryVo categoryVo1 = new CategoryVo();
			BeanUtils.copyProperties(category, categoryVo1);
			categorieListTotal.add(categoryVo1);
		}
		
		
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
	
	/*
	* @Author zhangxin_an 
	* @Date 2018/5/9 21:46  
	* @Params [id]  
	* @Return void
	* @Description:删除分类包括子类
	*/  
	@Override
	@Transactional
	public int deleteCategory(long id) {
		List<CategoryVo> categorieList = categoryMapper.getCategoryListBySuperId(id, JWTUtil.getStoreId());
		if(!CollectionUtils.isEmpty(categorieList)){
			throw new IllegalException("存在子类不能删除");
		}
		try {
			categoryMapper.setDeleteStatus(id);
		}catch (Exception e){
			throw  new IllegalException("分类不存在或已被删除");
		}
		return 1;
	}
	
	/*
	* @Author zhangxin_an 
	* @Date 2018/5/9 22:07  
	* @Params [categoryDto]  
	* @Return void
	* @Description:添加分类
	*/  
	@Override
	public void addCategory(CategoryDto categoryDto) {
		
		logger.info("Enter method addCategory() params："+categoryDto);
		if(StringUtils.isBlank(categoryDto.getCategoryName())
				|| categoryDto.getSuperId() == null
				){
			throw new IllegalException("参数不全");
		}
		
//		Category c = categoryMapper.selectByPrimaryKey(categoryDto.getSuperId());
////		if( c == null || c.getDeleteStatus() == 0)
////		{
////			throw new IllegalException("父类不存在");
////		}
		Category category = new Category();
		BeanUtils.copyProperties(categoryDto,category);
		category.setStoreId(JWTUtil.getStoreId());
		category.setOperator(JWTUtil.getUserId());
		categoryMapper.insertSelective(category);
		
		
		
		logger.info("Exit method addCategory() return：");
		
	}
	
	@Override
	public List<IdAndNameBo> getCategoryIdAndNameList() {
		
		
		List<IdAndNameBo>  idAndNameBos = categoryMapper.selectAllIdAndName(JWTUtil.getStoreId());
		return idAndNameBos;
	}
	
	/*
	* @Author zhangxin_an 
	* @Date 2018/5/9 22:41
	* @Params []  
	* @Return java.util.List<com.ok.okhelper.pojo.bo.IdAndNameBo>  
	* @Description:查询所有类名
	*/
	
	/*
	* @Author zhangxin_an 
	* @Date 2018/5/15 8:32
	* @Params [cId]  
	* @Return com.ok.okhelper.pojo.po.Category  
	* @Description:查询单个分类
	*/  
	@Override
	public Category getCategoryById(Long cId) {
		
		logger.info("Enter method getCategoryById() params："+cId);
		
		if( cId == null){
			throw new IllegalException("参数错误");
			
		}
		Category category = categoryMapper.selectByPrimaryKey(cId);
		if( !category.getStoreId().equals(JWTUtil.getStoreId())){
			throw new IllegalException("分类不存在");
		}
		return category;
	}
	
	/*
	 * @Author zhangxin_an
	 * @Date 2018/4/24 16:56
	 * @Params [superId, storeId]
	 * @Return java.util.List<com.ok.okhelper.pojo.vo.CategoryVo>
	 * @Description:获取当前类的所有子类
	 */
	public List<CategoryVo> getCategoryAllItems1(long superId, Long storeId) {
		
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
		
		logger.info("Exit method getCategoryItems() return："+categorieListTotal);
		return categorieListTotal;
		
	}
	
}


