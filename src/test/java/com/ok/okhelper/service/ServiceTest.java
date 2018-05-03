package com.ok.okhelper.service;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ok.okhelper.OkhelperApplication;
import com.ok.okhelper.pojo.dto.PlaceOrderItemDto;
import com.ok.okhelper.pojo.dto.UpdateStoreDto;
import com.ok.okhelper.pojo.po.*;
import com.ok.okhelper.pojo.vo.RolePermissionVo;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: zc
 * Date: 2018/4/20
 * Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OkhelperApplication.class)
@Slf4j
public class ServiceTest {

    @Autowired
    private RoleService roleService;

    @Autowired
    private StoreService storeService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private ProductService productService;

    @Autowired
    private DeliveryService deliveryService;


    @Autowired
    private SaleService saleService;

    @Autowired
    private OtherService otherService;


    @Test
    public void getRoleList() {
        List<RolePermissionVo> roles = roleService.getRoleListByStore((long) 0);
//        roles.forEach(role -> log.debug(role.getRoleName()));

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String s = objectMapper.writeValueAsString(roles);//直接输出到控制台
            log.debug(s);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void getPerssionTest() {
        List<Permission> addPermission = permissionService.findAddPermission((long) 1);
        addPermission.forEach(a -> log.debug(a.getPermissionCode()));
    }


    @Test
    public void updateStoreTest() {
        UpdateStoreDto updateStoreDto = new UpdateStoreDto();
        updateStoreDto.setDescription("哩哩啦啦");
        Store store = storeService.updateStore((long) 24, updateStoreDto);
        log.debug(store.getStoreName());
    }

    @Test
    public void testProduct() {
        Product product = productService.getProduct((long) 10);
        log.debug(product.toString());
    }


    @Test
    public void testForUpdate() {
        PlaceOrderItemDto placeOrderItemDto = new PlaceOrderItemDto();
        placeOrderItemDto.setProductId(Long.valueOf(686));
        placeOrderItemDto.setSaleCount(10);
        placeOrderItemDto.setSalePrice(BigDecimal.valueOf(100));
        List list = new ArrayList();
        list.add(placeOrderItemDto);
        otherService.checkAndCutStock(list);
    }

}
