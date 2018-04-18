package com.ok.okhelper.controller;

import com.ok.okhelper.dao.UserMapper;
import com.ok.okhelper.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
