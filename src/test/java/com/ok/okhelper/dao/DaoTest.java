package com.ok.okhelper.dao;

import com.ok.okhelper.OkhelperApplication;
import com.ok.okhelper.pojo.po.Product;
import com.ok.okhelper.util.NumberGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

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

    @Autowired
    private ProductMapper productMapper;

    @Test
    public void insetRolePermission() {
        int[] permissions={35,36};
        for (Integer per:permissions) {
            Long k = (long) per;
            roleMapper.insertRolePermission((long) 1, k);
            roleMapper.insertRolePermission((long) 2, k);
        }
    }

    @Test
    public void insertProduct() {
        for (int i = 1; i <= 1000; i++) {
            Product product = new Product();
            product.setProductName(RandomStringUtils.randomNumeric(11));
            product.setSalesStock(100);
            product.setBarCode(NumberGenerator.generatorBarCode());
            product.setRetailPrice(BigDecimal.valueOf(10.0));
            product.setUnit("箱");
            product.setCategoryId((long) 2);
            product.setStoreId((long) 2);
            productMapper.insertSelective(product);
        }
    }
}
