package com.ok.okhelper.service.impl;

import com.ok.okhelper.common.ServerResponse;
import com.ok.okhelper.dao.WarehouseMapper;
import com.ok.okhelper.exception.IllegalException;
import com.ok.okhelper.pojo.dto.WarehouseDTO;
import com.ok.okhelper.pojo.po.Warehouse;
import com.ok.okhelper.pojo.vo.WarehouseVo;
import com.ok.okhelper.service.WareHouseService;
import com.ok.okhelper.shiro.JWTUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
*Author:zhangxin_an
*Description:
*Data:Created in 9:57 2018/4/24
*/
@Service
public class WarehouseServiceImpl implements WareHouseService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private WarehouseMapper warehouseMapper;
	/*
	* @Author zhangxin_an 
	* @Date 2018/4/24 10:09
	* @Params []  
	* @Return java.util.List<com.ok.okhelper.pojo.vo.WarehouseVo>  
	* @Description:查询当前店铺所有仓库
	*/  
	@Override
	public List<WarehouseVo> getWarehouseList() {
		logger.info("Enter getWarehouseList()");
		Long storeId = JWTUtil.getStoreId();
		if(storeId == null){
			throw new IllegalException("参数异常");
		}
		List<WarehouseVo> warehouseVoList = warehouseMapper.getWarehouseByStoreId(storeId);
		
		
		logger.info("Exit getWarehouseList() Params"+warehouseVoList);
		return warehouseVoList;
	}
	
	@Override
	public WarehouseVo getWarehouseById(Long whId) {
		
		logger.info("Enter getWarehouseById() Params"+ whId);
		if(whId == null){
			throw new IllegalException("参数异常");
		}
		
		Warehouse warehouse = warehouseMapper.selectByPrimaryKey(whId);
		if (warehouse == null){
			throw new IllegalException("未找到当前仓库");
			
		}
		WarehouseVo warehouseVo = new WarehouseVo();
		BeanUtils.copyProperties(warehouse,warehouseVo);
		
		logger.info("Exit getWarehouseById() Params"+ warehouseVo);
		return warehouseVo;
	}
	
	
	/*
	* @Author zhangxin_an 
	* @Date 2018/4/24 10:37
	* @Params [warehouseDTO]  
	* @Return com.ok.okhelper.common.ServerResponse  
	* @Description:更新仓库信息
	*/  
	@Override
	public ServerResponse updateWarehouse(WarehouseDTO warehouseDTO) {
		
		logger.info("Enter updateWarehouse(WarehouseDTO warehouseDTO) Params"+ warehouseDTO);
		if( !ObjectUtils.anyNotNull(warehouseDTO)){
			logger.debug("warehouseDTO 为空");
			throw  new IllegalException("参数为空");
		}
		
		Warehouse warehouse = new Warehouse();
		BeanUtils.copyProperties(warehouseDTO,warehouse);
		ServerResponse serverResponse = null;
		try {
			int i = warehouseMapper.updateByPrimaryKeySelective(warehouse);
			serverResponse = ServerResponse.createBySuccess(i);
		}catch (Exception e){
			logger.debug("更新数据库异常");
			 serverResponse = ServerResponse.createBySuccessMessage(e.getMessage());
		}
		
		
		logger.info("Exit updateWarehouse(WarehouseDTO warehouseDTO) Params"+serverResponse);
		return serverResponse;
	}
	/*
	* @Author zhangxin_an 
	* @Date 2018/4/24 10:49  
	* @Params [whId]  
	* @Return com.ok.okhelper.common.ServerResponse  
	* @Description:删除仓库
	*/  
	@Override
	public ServerResponse deleteWarehouseById(Long whId) {
		
		logger.info("Enter deleteWarehouseById(Long whId) Params"+ whId);
		if(whId == null){
			throw new IllegalException("请求参数异常");
		}
		ServerResponse serverResponse;
		try {
			int i = warehouseMapper.deleteByPrimaryKey(whId);
			serverResponse = ServerResponse.createBySuccess("删除成功",i);
			
		}catch (Exception e){
			logger.debug("删除数据库异常");
			serverResponse = ServerResponse.createDefaultErrorMessage(e.getMessage());
		}
		
		logger.info("Exit deleteWarehouseById(Long whId)  Params"+serverResponse);
		return serverResponse;
	}
	/*
	* @Author zhangxin_an 
	* @Date 2018/4/24 10:49
	* @Params [warehouseDTO]  
	* @Return com.ok.okhelper.common.ServerResponse  
	* @Description:添加仓库
	*/  
	@Override
	public ServerResponse addWarehouse(WarehouseDTO warehouseDTO) {
		logger.info("Enter addWarehouse(WarehouseDTO warehouseDTO) Params"+ warehouseDTO);
		
		if( !ObjectUtils.anyNotNull(warehouseDTO)){
			logger.debug("warehouseDTO 为空");
			throw  new IllegalException("参数为空");
		}
		
		if(StringUtils.isBlank(warehouseDTO.getWarehouseName()) ){
			logger.debug("仓库名不能为空");
			throw  new IllegalException("仓库名为空");
		}
		
		Warehouse warehouse = new Warehouse();
		BeanUtils.copyProperties(warehouseDTO,warehouse);
		
		ServerResponse serverResponse;
		try{
			warehouse.setStoreId(JWTUtil.getStoreId());
			int i = warehouseMapper.insertSelective(warehouse);
			serverResponse = ServerResponse.createBySuccess("添加成功",i);
		}catch (Exception e){
			serverResponse = ServerResponse.createDefaultErrorMessage("数据库添加失败，请检查信息");
		}
		
		
		logger.info("Exit addWarehouse(WarehouseDTO warehouseDTO) Params"+ serverResponse);
		return serverResponse;
	}
}
