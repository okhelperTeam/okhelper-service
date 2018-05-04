package com.ok.okhelper.controller;

/*
*Author:zhangxin_an
*Description:存储模块
*Data:Created in 10:09 2018/5/3
*/

import com.ok.okhelper.common.ServerResponse;
import com.ok.okhelper.pojo.dto.StorageOrderDto;
import com.ok.okhelper.pojo.vo.StorageOrderVo;
import com.ok.okhelper.service.StorageOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Api(tags = "存储模块")
@Slf4j
public class StorageController {

	@Autowired
	private StorageOrderService storageOrderService;
	
	@ApiOperation(value = "入库操作",notes = "添加商品到仓库")
	@PostMapping("/storage")
	@Transactional
	public ServerResponse<StorageOrderVo> storageIn(@Valid StorageOrderDto storageOrderDto){
		log.info("Enter method storageIn params:"+ storageOrderDto);
		storageOrderService.insertStorage(storageOrderDto);
		ServerResponse<StorageOrderVo> serverResponse = ServerResponse.createBySuccess();
		
		log.info("Exit method storageIn params:"+ serverResponse);
		return serverResponse;
	}
	
	@ApiOperation(value = "入库订单",notes = "通过入库单号查询")
	@GetMapping("/storage/{number}")
	public ServerResponse<StorageOrderVo> storageOrder(@PathVariable String number){
		log.info("Enter method storageOrder params:"+ number);
		
		StorageOrderVo storageOrderVo = storageOrderService.getStorageOrderByOrderNumber(number);
		ServerResponse<StorageOrderVo> serverResponse = ServerResponse.createBySuccess(storageOrderVo);
		
		log.info("Exit method storageIn params:"+ serverResponse);
		return serverResponse;
	}


}
