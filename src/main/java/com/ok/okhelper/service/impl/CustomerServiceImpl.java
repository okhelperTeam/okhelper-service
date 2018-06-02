package com.ok.okhelper.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ok.okhelper.common.PageModel;
import com.ok.okhelper.dao.CustomerMapper;
import com.ok.okhelper.exception.IllegalException;
import com.ok.okhelper.common.constenum.ConstEnum;
import com.ok.okhelper.pojo.dto.CustomerConditionDto;
import com.ok.okhelper.pojo.dto.CustomerDto;
import com.ok.okhelper.pojo.po.Customer;
import com.ok.okhelper.service.CustomerService;
import com.ok.okhelper.shiro.JWTUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: zc
 * Date: 2018/5/4
 * Description:
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public PageModel<Customer> getCustomerList(CustomerConditionDto customerConditionDto,PageModel pageModel) {
        //启动分页
        PageHelper.startPage(pageModel.getPageNum(), pageModel.getLimit());

        //启动排序
        PageHelper.orderBy(pageModel.getOrderBy());

        Customer customer = new Customer();
        customer.setStoreId(JWTUtil.getStoreId());
        customer.setDeleteStatus(ConstEnum.STATUSENUM_AVAILABLE.getCode());
        BeanUtils.copyProperties(customerConditionDto,customer);
        List<Customer> customers = customerMapper.select(customer);

        PageInfo<Customer> pageInfo = new PageInfo<>(customers);

        return PageModel.convertToPageModel(pageInfo);
    }

    @Override
    public Customer getCustomerById(Long customerId) {
        Customer customer = customerMapper.selectByPrimaryKey(customerId);
        if (customer == null) {
            throw new IllegalException("客户不存在");
        }
        if (ObjectUtils.notEqual(customer.getStoreId(), JWTUtil.getStoreId())) {
            throw new AuthorizationException("资源不在你当前商铺查看范围");
        }
        if (ConstEnum.STATUSENUM_UNAVAILABLE.getCode() == customer.getDeleteStatus()) {
            throw new IllegalException("当前资源状态不可用");
        }
        return customer;
    }

    @Override
    public void updateCustomer(Long customerId, CustomerDto customerDto) {
        Customer dbcustomer = customerMapper.selectByPrimaryKey(customerId);
        if (dbcustomer == null) {
            throw new IllegalException("客户不存在");
        }
        if (ObjectUtils.notEqual(dbcustomer.getStoreId(), JWTUtil.getStoreId())) {
            throw new AuthorizationException("资源不在你当前商铺查看范围");
        }
        if (ConstEnum.STATUSENUM_UNAVAILABLE.getCode() == dbcustomer.getDeleteStatus()) {
            throw new IllegalException("当前资源状态不可用");
        }

        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setOperator(JWTUtil.getUserId());
        BeanUtils.copyProperties(customerDto, customer);

        int i = customerMapper.updateByPrimaryKeySelective(customer);

        if (i <= 0) {
            throw new IllegalException("更新失败");
        }

    }

    @Override
    public void deleteCustomerById(Long customerId) {
        Customer dbcustomer = customerMapper.selectByPrimaryKey(customerId);
        if (dbcustomer == null) {
            throw new IllegalException("客户不存在");
        }
        if (ObjectUtils.notEqual(dbcustomer.getStoreId(), JWTUtil.getStoreId())) {
            throw new AuthorizationException("资源不在你当前商铺查看范围");
        }
        if (ConstEnum.STATUSENUM_UNAVAILABLE.getCode() == dbcustomer.getDeleteStatus()) {
            throw new IllegalException("当前资源状态不可用");
        }
        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setOperator(JWTUtil.getUserId());
        customer.setDeleteStatus(ConstEnum.STATUSENUM_UNAVAILABLE.getCode());

        int i = customerMapper.updateByPrimaryKeySelective(customer);

        if (i <= 0) {
            throw new IllegalException("删除失败");
        }

    }

    @Override
    public void addCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setOperator(JWTUtil.getUserId());
        customer.setStoreId(JWTUtil.getStoreId());
        BeanUtils.copyProperties(customerDto, customer);

        int i = customerMapper.insertSelective(customer);
        if (i <= 0) {
            throw new IllegalException("添加客户失败");
        }
    }
}
