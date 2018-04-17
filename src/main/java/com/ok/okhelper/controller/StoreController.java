package com.ok.okhelper.controller;

import com.ok.okhelper.common.ServerResponse;
import com.ok.okhelper.dao.UserMapper;
import com.ok.okhelper.pojo.dto.StoreDto;
import com.ok.okhelper.pojo.po.Store;
import com.ok.okhelper.pojo.vo.OpenStore;
import com.ok.okhelper.service.StoreService;
import com.ok.okhelper.shiro.JWTUtil;
import com.ok.okhelper.until.PasswordHelp;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author: zc
 * @description:
 * @date: 2018/4/15
 */

@RestController
@RequestMapping("/store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping
//    @RequiresPermissions("store:post")
    public ServerResponse<OpenStore> postStore(@Valid StoreDto storeDto) {
        storeDto.setLeaderId(JWTUtil.getUserId());
        storeDto.setOperator(JWTUtil.getUserId());

        String password = userMapper.getPassWordByUserId(JWTUtil.getUserId());

        Store store = storeService.postStore(storeDto);

        String token = JWTUtil.sign(JWTUtil.getUserId(), JWTUtil.getUsername(), password, store.getId());

        OpenStore openStore = new OpenStore();
        BeanUtils.copyProperties(store, openStore);
        openStore.setToken(token);


        return ServerResponse.createBySuccess("店铺创建成功", openStore);

    }
}
