package com.ok.okhelper.service.impl;

import com.ok.okhelper.OkhelperApplication;
import com.ok.okhelper.common.ServerResponse;
import com.ok.okhelper.po.SysUser;
import com.ok.okhelper.service.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author: zc
 * @description:
 * @date: 2018/4/11
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OkhelperApplication.class)
public class TestServiceImplTest {

    @Autowired
    private TestService testService;

    @Test
    public void test() {
        testService.get()


    }
}
