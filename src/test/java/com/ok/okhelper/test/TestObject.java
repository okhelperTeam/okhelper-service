package com.ok.okhelper.test;

import com.ok.okhelper.pojo.po.User;
import org.apache.commons.lang3.ObjectUtils;

/*
 *Author:zhangxin_an
 *Description:
 *Data:Created in 13:51 2018/5/14
 */
public class TestObject {
	public static void main(String[] args) {
		User u = new User();
		System.out.println(ObjectUtils.allNotNull(u));
//		u.setUserAvatar("z");
		System.out.println(ObjectUtils.anyNotNull(u));
	}
}
