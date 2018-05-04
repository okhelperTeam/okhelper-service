package com.ok.okhelper.controller;

import com.ok.okhelper.common.PageModel;
import com.ok.okhelper.common.ServerResponse;
import com.ok.okhelper.pojo.dto.CustomerDto;
import com.ok.okhelper.pojo.po.Customer;
import com.ok.okhelper.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Author: zc
 * Date: 2018/5/4
 * Description:
 */
@RestController
@Api(tags = "客户模块")
@Slf4j
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @RequiresPermissions("customer:view")
    @ApiOperation(value = "查询所有客户", notes = "查询当前店铺所有客户")
    @GetMapping("/customer")
    public ServerResponse<PageModel<Customer>> getCustomerList(@Valid PageModel pageModel) {
        PageModel<Customer> customerList = customerService.getCustomerList(pageModel);
        return ServerResponse.createBySuccess(customerList);

    }


    @RequiresPermissions("customer:view")
    @ApiOperation(value = "查询供应商", notes = "查询具体客户")
    @GetMapping("/customer/{customerId}")
    public ServerResponse<Customer> getCustomer(@PathVariable Long customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        return ServerResponse.createBySuccess(customer);
    }

    @RequiresPermissions("customer:edit")
    @ApiOperation(value = "修改客户信息", notes = "修改具体客户信息")
    @PutMapping("/customer/{customerId}")
    public ServerResponse<String> updateCustomer(@PathVariable Long customerId, @Valid CustomerDto customerDto) {
        customerService.updateCustomer(customerId, customerDto);
        return ServerResponse.createBySuccessMessage("更新客户信息成功");

    }

    @RequiresPermissions("customer:edit")
    @ApiOperation(value = "删除客户", notes = "删除指定客户")
    @DeleteMapping("/customer/{customerId}")
    public ServerResponse<String> deleteCustomer(@PathVariable Long customerId) {
        customerService.deleteCustomerById(customerId);
        return ServerResponse.createBySuccessMessage("删除客户成功");

    }

    @RequiresPermissions("customer:edit")
    @ApiOperation(value = "添加客户")
    @PostMapping("/customer")
    public ServerResponse<String> addCustomer(@Valid CustomerDto customerDto) {
        customerService.addCustomer(customerDto);
        return ServerResponse.createBySuccessMessage("添加客户成功");

    }
}
