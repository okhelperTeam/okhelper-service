package com.ok.okhelper.service;

import com.ok.okhelper.common.ServerResponse;
import com.ok.okhelper.po.User;

import java.util.List;

/**
 * Created by zc on 2018/4/10.
 */

public interface TestService {
    ServerResponse<List<User>> get();

    boolean update(Long userId);
}
