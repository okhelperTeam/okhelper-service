package com.ok.okhelper.dao;

import com.ok.okhelper.OkhelperApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Author: zc
 * Date: 2018/4/21
 * Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OkhelperApplication.class)
@Slf4j
public class DaoTest {
    @Autowired
    private RoleMapper roleMapper;

    @Test
    public void insetRolePermission() {
        for (int i = 1; i <= 34; i++) {
            Long k = (long) i;
            roleMapper.insertRolePermission((long) 2, k);
        }


    }
}
