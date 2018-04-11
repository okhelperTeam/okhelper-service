package com.ok.okhelper.dao;

import com.ok.okhelper.po.User;
import com.ok.okhelper.until.MyMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends MyMapper<User> {
}