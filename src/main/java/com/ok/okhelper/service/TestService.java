package com.ok.okhelper.service;

import com.ok.okhelper.common.ServerResponse;
import com.ok.okhelper.po.SysUser;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zc on 2018/4/10.
 */

public interface TestService {
    public ServerResponse<List<SysUser>> get();
}
