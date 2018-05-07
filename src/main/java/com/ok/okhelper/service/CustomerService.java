package com.ok.okhelper.service;

import com.ok.okhelper.common.PageModel;
import com.ok.okhelper.common.ServerResponse;
import com.ok.okhelper.pojo.dto.CustomerConditionDto;
import com.ok.okhelper.pojo.dto.CustomerDto;
import com.ok.okhelper.pojo.dto.SupplierDto;
import com.ok.okhelper.pojo.po.Customer;
import com.ok.okhelper.pojo.po.Supplier;

/**
 * Author: zc
 * Date: 2018/5/4
 * Description:
 */
public interface CustomerService {
    PageModel<Customer> getCustomerList(CustomerConditionDto customerConditionDto,PageModel pageModel);

    Customer getCustomerById(Long customerId);

    void updateCustomer(Long customerId, CustomerDto customerDto);

    void deleteCustomerById(Long customerId);

    void addCustomer(CustomerDto customerDto);
}
