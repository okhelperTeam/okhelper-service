package com.ok.okhelper.dao;

import com.ok.okhelper.pojo.po.Customer;
import com.ok.okhelper.util.MyMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerMapper extends MyMapper<Customer> {
}