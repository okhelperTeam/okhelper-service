package com.ok.okhelper.service.impl;

import com.ok.okhelper.common.ServerResponse;
import com.ok.okhelper.dao.SysUserMapper;
import com.ok.okhelper.po.SysUser;
import com.ok.okhelper.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zc on 2018/4/10.
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private SysUserMapper userMapper;

    public ServerResponse<List<SysUser>> get(){
        List<SysUser> sysUsers = userMapper.selectAll();
        return ServerResponse.createBySuccess(sysUsers);
    }
}
