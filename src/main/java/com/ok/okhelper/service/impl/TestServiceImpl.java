package com.ok.okhelper.service.impl;

import com.ok.okhelper.common.ServerResponse;
import com.ok.okhelper.dao.SysUserMapper;
import com.ok.okhelper.po.SysUser;
import com.ok.okhelper.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zc on 2018/4/10.
 */
@Service("testService")
public class TestServiceImpl implements TestService {

    @Autowired
    private SysUserMapper userMapper;


    @Override
    public ServerResponse<List<SysUser>> get() {
        List<SysUser> sysUsers = userMapper.selectAll();
        return ServerResponse.createBySuccess(sysUsers);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean update(Long userId) {
        SysUser user1 = userMapper.selectByPrimaryKey(userId);
        user1.setUserNick("lalala");
        int count1 = userMapper.updateByPrimaryKey(user1);
        int a = 1 / 0;
        user1.setUserEmail("zc1217zc@126.com");
        int count2 = userMapper.updateByPrimaryKey(user1);
        return count1 > 0 && count2 > 0;
    }

}
