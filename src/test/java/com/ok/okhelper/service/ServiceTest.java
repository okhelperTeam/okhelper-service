package com.ok.okhelper.service;

import com.ok.okhelper.OkhelperApplication;
import com.ok.okhelper.pojo.dto.UpdateStoreDto;
import com.ok.okhelper.pojo.po.Permission;
import com.ok.okhelper.pojo.po.Role;
import com.ok.okhelper.pojo.po.Store;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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


    @Test
    public void getRoleList() {
        List<Role> roles = roleService.getRoleListByStore((long) 3);
        roles.forEach(role -> log.debug(role.getRoleName()));
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

}
