package com.ok.okhelper.util;

/*
*Author:zhangxin_an
*Description:
*Data:Created in 15:59 2018/4/12
*/

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PasswordHelp1.class)
public class PasswordHelp1 {
	
	
	@Test
	public void tsetUpload() {
		System.out.println(PasswordHelp.passwordSalt("ztt","12345"));
	}
	
	
}
