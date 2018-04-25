package com.ok.okhelper.controller;

/*
*Author:zhangxin_an
*Description:仓库
*Data:Created in 9:53 2018/4/24
*/

import com.ok.okhelper.common.ServerResponse;
import com.ok.okhelper.pojo.dto.WarehouseDTO;
import com.ok.okhelper.pojo.vo.WarehouseVo;
import com.ok.okhelper.service.WareHouseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.xml.ws.Service;
import java.util.List;

@Api(tags = "仓库模块")
@RestController
public class WarehouseController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	WareHouseService wareHouseService;
	
	@RequiresPermissions("warehouse:view")
	@ApiOperation(value = "查询所有仓库",notes = "查询当前店铺所有仓库")
	@GetMapping("/warehouse")
	public ServerResponse<List<WarehouseVo>> getWarehouseList(){
		logger.info("Enter getWarehouseList()");
		
		List<WarehouseVo> warehouseVoList = wareHouseService.getWarehouseList();
		
		return ServerResponse.createBySuccess(warehouseVoList);
		
	}
	
	
	@RequiresPermissions("warehouse:view")
	@ApiOperation(value = "查询仓库",notes = "查询具体仓库")
	@GetMapping("/warehouse/{id}")
	public ServerResponse<WarehouseVo> getWarehouse(@PathVariable Long id){
		logger.info("Enter getWarehouse() Params::" + id);
		
		WarehouseVo warehouseVo= wareHouseService.getWarehouseById(id);
		logger.info("Exit getWarehouse() return :" + warehouseVo);
		
		return ServerResponse.createBySuccess(warehouseVo);
		
	}
	
	@RequiresPermissions("warehouse:edit")
	@ApiOperation(value = "修改仓库信息",notes = "修改具体仓库信息")
	@PutMapping("/warehouse")
	public ServerResponse updateWarehouse(WarehouseDTO warehouseDTO){
		logger.info("Enter getWarehouse() Params:" + warehouseDTO);
		return wareHouseService.updateWarehouse(warehouseDTO);
		
	}
	@RequiresPermissions("warehouse:edit")
	@ApiOperation(value = "删除仓库",notes = "删除指定仓库")
	@DeleteMapping("/warehouse/{id}")
	public ServerResponse updateWarehouse(@PathVariable Long id){
		logger.info("Enter updateWarehouse(Long id) Params:" + id);
		return wareHouseService.deleteWarehouseById(id);
		
	}
	
	@RequiresPermissions("warehouse:edit")
	@ApiOperation(value = "添加仓库",notes = "添加仓库")
	@PostMapping("/warehouse")
	public ServerResponse addWarehouse(@Valid WarehouseDTO warehouseDTO){
		logger.info("Enter updateWarehouse(Long id) Params:" + warehouseDTO);
		return wareHouseService.addWarehouse(warehouseDTO);
		
	}
	
}
