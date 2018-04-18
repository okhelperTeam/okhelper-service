package com.ok.okhelper.dao;

import com.ok.okhelper.OkhelperApplication;
import com.ok.okhelper.pojo.po.Store;
import com.ok.okhelper.pojo.po.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

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

    @Autowired
    StoreMapper storeMapper;
	
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


    @Test
    public void insertTest() {
        Store store = new Store();
        store.setStoreName("啦啦啦8");
        store.setStorePhone("12334567");
        store.setLeaderId((long) 2);
        storeMapper.insertSelective(store);

//		Store store1=new Store();
//		store1.setStoreAddress("青岛大学");
//		List<Store> stores = storeMapper.select(store1);
//
//		stores.forEach(store -> {
//			System.out.println(store.getStoreName());
//		});

        System.out.println(store.getId());
    }
	
}
