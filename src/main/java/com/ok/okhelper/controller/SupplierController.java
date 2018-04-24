package com.ok.okhelper.controller;

import com.ok.okhelper.common.ServerResponse;
import com.ok.okhelper.pojo.dto.SupplierDto;
import com.ok.okhelper.pojo.po.Supplier;
import com.ok.okhelper.pojo.vo.SupplierVo;
import com.ok.okhelper.service.SupplierService;
import com.ok.okhelper.service.WareHouseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
	
	@ApiOperation(value = "查询所有供应商",notes = "查询当前店铺所有供应商")
	@GetMapping("/supplier")
	public ServerResponse<List<Supplier>> getSupplierList(){
		logger.info("Enter getSupplierList()");
		
		List<Supplier> supplierList = supplierService.getSupplierList();
		
		return ServerResponse.createBySuccess(supplierList);
		
	}
	
	
	@ApiOperation(value = "查询供应商",notes = "查询具体供应商")
	@GetMapping("/supplier/{id}")
	public ServerResponse<Supplier> getSupplier(@PathVariable Long id){
		logger.info("Enter getSupplier() Params" + id);
		
		Supplier supplier= supplierService.getSupplierById(id);
		logger.info("Exit getSupplier() Params" + supplier);
		
		return ServerResponse.createBySuccess(supplier);
		
	}
	
	@ApiOperation(value = "修改供应商信息",notes = "修改具体供应商信息")
	@PutMapping("/supplier")
	public ServerResponse updateSupplier(@Valid  SupplierDto supplierDto){
		logger.info("Enter getSupplier() Params" + supplierDto);
		return supplierService.updateSupplier(supplierDto);
		
	}
	@ApiOperation(value = "删除供应商",notes = "删除指定供应商")
	@DeleteMapping("/supplier/{id}")
	public ServerResponse updateSupplier(@PathVariable Long id){
		logger.info("Enter updateSupplier(Long id) Params" + id);
		return supplierService.deleteSupplierById(id);
		
	}
	
	@ApiOperation(value = "添加供应商",notes = "添加供应商")
	@PostMapping("/supplier")
	public ServerResponse addSupplier(@Valid SupplierDto supplierDto){
		logger.info("Enter updateSupplier(Long id) Params" + supplierDto);
		return supplierService.addSupplier(supplierDto);
		
	}
	
}
