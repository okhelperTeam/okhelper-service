package com.ok.okhelper.service.impl;

import com.ok.okhelper.dao.RoleMapper;
import com.ok.okhelper.dao.StoreMapper;
import com.ok.okhelper.dao.UserMapper;
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

    @Override
    @Transactional
    public Store postStore(StoreDto storeDto) {
        Store store = new Store();
        BeanUtils.copyProperties(storeDto, store);
        int i = storeMapper.insertSelective(store);
        Store newStore = storeMapper.selectOne(store);
        Long store_id = newStore.getId();

        User user = new User();
        Long user_id = storeDto.getLeaderId();
        user.setId(user_id);
        user.setStoreId(store_id);
        userMapper.updateByPrimaryKeySelective(user);

        roleMapper.insertUserRole(user_id, (long) 2, user_id);

        return newStore;
    }
}
