package com.ok.okhelper.controller;

import com.ok.okhelper.common.PageModel;
import com.ok.okhelper.common.ServerResponse;
import com.ok.okhelper.pojo.dto.SupplierDto;
import com.ok.okhelper.pojo.po.Supplier;
import com.ok.okhelper.service.SupplierService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/*
*Author:zhangxin_an
*Description:
*Data:Created in 13:40 2018/4/24
*/
@Api(tags = "供应商模块")
@RestController
public class SupplierController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	SupplierService supplierService;
	
	@RequiresPermissions("supplier:view")
	@ApiOperation(value = "查询所有供应商",notes = "查询当前店铺所有供应商")
	@GetMapping("/supplier")
	public ServerResponse<PageModel<Supplier>> getSupplierList(@Valid PageModel pageModel){
		logger.info("Enter method getSupplierList()");
		
		PageModel<Supplier> supplierList = supplierService.getSupplierList(pageModel);
		
		logger.info("Exit method getSupplierList() return :"+supplierList);
		return ServerResponse.createBySuccess(supplierList);
		
	}
	
	
	@RequiresPermissions("supplier:view")
	@ApiOperation(value = "查询供应商",notes = "查询具体供应商")
	@GetMapping("/supplier/{id:\\d+}")
	public ServerResponse<Supplier> getSupplier(@PathVariable Long id){
		logger.info("Enter method getSupplier() Params:" + id);
		
		Supplier supplier= supplierService.getSupplierById(id);
		logger.info("Exit method getSupplier() return :" + supplier);
		
		return ServerResponse.createBySuccess(supplier);
		
	}
	
	@RequiresPermissions("supplier:edit")
	@ApiOperation(value = "修改供应商信息",notes = "修改具体供应商信息")
	@PutMapping("/supplier/{id:\\d+}")
	public ServerResponse updateSupplier(@PathVariable Long id, @Valid SupplierDto supplierDto) {
		logger.info("Enter method getSupplier() Params:" + supplierDto);
		return supplierService.updateSupplier(id, supplierDto);
		
	}
	
	@RequiresPermissions("supplier:edit")
	@ApiOperation(value = "删除供应商",notes = "删除指定供应商")
	@DeleteMapping("/supplier/{id:\\d+}")
	public ServerResponse updateSupplier(@PathVariable Long id){
		logger.info("Enter method updateSupplier(Long id) Params:" + id);
		return supplierService.deleteSupplierById(id);
		
	}
	
	@RequiresPermissions("supplier:edit")
	@ApiOperation(value = "添加供应商",notes = "添加供应商")
	@PostMapping("/supplier")
	public ServerResponse addSupplier(@Valid SupplierDto supplierDto){
		logger.info("Enter method updateSupplier(Long id) Params:" + supplierDto);
		return supplierService.addSupplier(supplierDto);
		
	}
	
}
