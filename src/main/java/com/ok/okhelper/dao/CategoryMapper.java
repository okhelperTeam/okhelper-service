package com.ok.okhelper.dao;

import com.ok.okhelper.pojo.po.Category;
import com.ok.okhelper.pojo.vo.CategoryVo;
import com.ok.okhelper.until.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryMapper extends MyMapper<Category> {
	
	List<CategoryVo>  getCategoryListBySuperId(@Param("superId") Long superId, @Param("storeId") Long storeId);
	
}