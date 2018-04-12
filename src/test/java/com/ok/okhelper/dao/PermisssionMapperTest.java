package com.ok.okhelper.dao;

import com.ok.okhelper.OkhelperApplication;
import com.ok.okhelper.common.ServerResponse;
import com.ok.okhelper.po.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/*
*Author:zhangxin_an
*Description:
*Data:Created in 15:34 2018/4/12
*/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OkhelperApplication.class)
public class PermisssionMapperTest {
	@Autowired
	PermissionMapper permissionMapper;
	
	@Autowired
	UserMapper userMapper;
	
	@Test
	public void test() {
		List<String> listServerResponse = permissionMapper.findAddPermissionCode((long) 1);
		listServerResponse.forEach(a->{
			
			System.out.println(a+"test");
		});
	}
	
	@Test
	public void test1() {
		Long l = userMapper.findUserIdByName("ztt");
			System.out.println(l+"test");
	
	}
	
}
