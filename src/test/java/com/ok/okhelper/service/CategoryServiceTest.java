package com.ok.okhelper.service;

/*
*Author:zhangxin_an
*Description:
*Data:Created in 16:23 2018/4/24
*/

import com.ok.okhelper.OkhelperApplication;
import com.ok.okhelper.pojo.vo.CategoryVo;
import com.ok.okhelper.service.impl.CategoryServiceIMpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OkhelperApplication.class)
public class CategoryServiceTest {
	
	@Autowired
	CategoryServiceIMpl categoryService;
	
	@Test
	public void testCategory(){
		List<CategoryVo> categoryVos =  categoryService.getCategoryList(0);
		categoryVos.forEach(categoryVo -> {
			System.out.println(categoryVo.getCategoryName());
		});
	}
	
}
