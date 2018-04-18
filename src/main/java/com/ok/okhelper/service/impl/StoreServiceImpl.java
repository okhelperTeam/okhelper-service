package com.ok.okhelper.service.impl;

import com.ok.okhelper.dao.RoleMapper;
import com.ok.okhelper.dao.StoreMapper;
import com.ok.okhelper.dao.UserMapper;
import com.ok.okhelper.pojo.constenum.ConstEnum;
import com.ok.okhelper.pojo.dto.StoreDto;
import com.ok.okhelper.pojo.po.Role;
import com.ok.okhelper.pojo.po.Store;
import com.ok.okhelper.pojo.po.User;
import com.ok.okhelper.service.StoreService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: zc
 * @description:
 * @date: 2018/4/15
 */
@Service
public class StoreServiceImpl implements StoreService {
    @Autowired
    private StoreMapper storeMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

}
