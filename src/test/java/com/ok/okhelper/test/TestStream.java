package com.ok.okhelper.test;

import com.ok.okhelper.pojo.bo.UserBo;
import com.ok.okhelper.pojo.po.User;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
*Author:zhangxin_an
*Description:
*Data:Created in 9:30 2018/5/4
*/
public class TestStream {
	public static void main(String[] args) {
//		List<User> users = new ArrayList<>();
//		for(int i = 0;i<100;i++) {
//			User user = new User();
//			user.setId((long) 1+i);
//			user.setUserPhone("nb"+i);
//			users.add(user);
//		}
//		long start = System.currentTimeMillis();
//		List<UserBo> userBos = users.stream().map(u->getBo(u)).collect(Collectors.toList());
//		long end = System.currentTimeMillis();
//		System.out.println(userBos.size()+"strean last======:"+(end - start));
//
//		System.out.println("");
//		userBos = new ArrayList<>();
//		long start1 = System.currentTimeMillis();
//		for(User user : users){
//			UserBo bos = getBo(user);
//			userBos.add(bos);
//		}
//		long end1 = System.currentTimeMillis();
//		System.out.println(userBos.size()+"for each last======:"+(end1 - start1));
		
		List<String> s1s = new ArrayList<>(1000);
		for(int i= 0;i<3000000;i++){
			s1s.add(i+"aafasfsd");
		}

		long start = System.currentTimeMillis();
		List<String> s2s = s1s.stream().filter(s->s.startsWith("1")).collect(Collectors.toList());
		long end = System.currentTimeMillis();
		System.out.println(s2s.size()+"strean last======:"+(end - start));
		
		
		long start2 = System.currentTimeMillis();
		List<String> s4s = s1s.parallelStream().filter(s->s.startsWith("1")).collect(Collectors.toList());;
		long end2 = System.currentTimeMillis();
		System.out.println(s4s.size()+" paralleltrean last======:"+(end2 - start2));
		
		long start1 = System.currentTimeMillis();
		List<String> s3s = new ArrayList<>();
		for (String s: s1s){
			if(s.startsWith("1"))
				s3s.add(s);
		}
		long end1 = System.currentTimeMillis();
		System.out.println(s3s.size()+"for last======:"+(end1 - start1));
		
		
		
	}
	
	private static UserBo getBo(User user){
		UserBo userBo = new UserBo();
		BeanUtils.copyProperties(user,userBo);
		try {
			Thread.sleep(20);
		}catch (Exception e){
		
		}
		return userBo;
	}
	
	
}
