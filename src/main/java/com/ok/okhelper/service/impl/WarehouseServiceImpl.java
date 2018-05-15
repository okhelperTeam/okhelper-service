package com.ok.okhelper.service.impl;

import com.auth0.jwt.JWT;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ok.okhelper.common.PageModel;
import com.ok.okhelper.common.ServerResponse;
import com.ok.okhelper.dao.WarehouseMapper;
import com.ok.okhelper.exception.IllegalException;
import com.ok.okhelper.pojo.bo.IdAndNameBo;
import com.ok.okhelper.pojo.dto.WarehouseDTO;
import com.ok.okhelper.pojo.po.Supplier;
import com.ok.okhelper.pojo.po.Warehouse;
import com.ok.okhelper.pojo.vo.WarehouseVo;
import com.ok.okhelper.service.WareHouseService;
import com.ok.okhelper.shiro.JWTUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.AuthorizationException;
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
	* @Params: []  
	* @Return java.util.List<com.ok.okhelper.pojo.vo.WarehouseVo>  
	* @Description:查询当前店铺所有仓库
	*/  
	@Override
	public PageModel<WarehouseVo> getWarehouseList(PageModel pageModel) {
		logger.info("Enter method getWarehouseList()");
		//启动分页
		PageHelper.startPage(pageModel.getPageNum(), pageModel.getLimit());
		Long storeId = JWTUtil.getStoreId();
		if(storeId == null){
			throw new IllegalException("参数异常");
		}
		List<WarehouseVo> warehouseVoList = warehouseMapper.getWarehouseByStoreId(storeId);
		PageInfo<WarehouseVo> pageInfo = new PageInfo<>(warehouseVoList);
		
		logger.info("Exit method getWarehouseList() return :"+pageInfo);
		return PageModel.convertToPageModel(pageInfo);
	}
	
	@Override
	public WarehouseVo getWarehouseById(Long whId) {
		
		logger.info("Enter method getWarehouseById() Params:"+ whId);
		if(whId == null){
			throw new IllegalException("参数异常");
		}
		
		Warehouse warehouse = warehouseMapper.selectByPrimaryKey(whId);
		if (warehouse == null){
			throw new IllegalException("未找到当前仓库");
			
		}
		if (ObjectUtils.notEqual(warehouse.getStoreId(), JWTUtil.getStoreId())) {
			throw new AuthorizationException("资源不在你当前商铺查看范围");
		}
		
		WarehouseVo warehouseVo = new WarehouseVo();
		BeanUtils.copyProperties(warehouse,warehouseVo);
		
		logger.info("Exit method getWarehouseById() return:"+ warehouseVo);
		return warehouseVo;
	}
	
	
	/*
	* @Author zhangxin_an 
	* @Date 2018/4/24 10:37
	* @Params: [warehouseDTO]  
	* @Return com.ok.okhelper.common.ServerResponse  
	* @Description:更新仓库信息
	*/  
	@Override
	public ServerResponse updateWarehouse(WarehouseDTO warehouseDTO) {
		
		logger.info("Enter method updateWarehouse(WarehouseDTO warehouseDTO) Params:"+ warehouseDTO);
		if( !ObjectUtils.anyNotNull(warehouseDTO)){
			logger.debug("warehouseDTO 为空");
			throw  new IllegalException("参数为空");
		}
		
		Warehouse warehouse = new Warehouse();
		BeanUtils.copyProperties(warehouseDTO,warehouse);
		warehouse.setOperator(JWTUtil.getUserId());
		warehouse.setStoreId(JWTUtil.getStoreId());
		ServerResponse serverResponse = null;
		try {
			int i = warehouseMapper.updateByPrimaryKeySelective(warehouse);
			serverResponse = ServerResponse.createBySuccess(i);
		}catch (Exception e){
			logger.debug("更新数据库异常");
			 serverResponse = ServerResponse.createBySuccessMessage(e.getMessage());
		}
		
		
		logger.info("Exit method updateWarehouse(WarehouseDTO warehouseDTO) return:"+serverResponse);
		return serverResponse;
	}
	/*
	* @Author zhangxin_an 
	* @Date 2018/4/24 10:49  
	* @Params: [whId]  
	* @Return com.ok.okhelper.common.ServerResponse  
	* @Description:删除仓库
	*/  
	@Override
	public ServerResponse deleteWarehouseById(Long whId) {
		
		logger.info("Enter method deleteWarehouseById(Long whId) Params:"+ whId);
		if(whId == null){
			throw new IllegalException("请求参数异常");
		}
		ServerResponse serverResponse;
		try {
			Long storeId = warehouseMapper.selectByPrimaryKey(whId).getStoreId();
			if (ObjectUtils.notEqual(storeId, JWTUtil.getStoreId())) {
				throw new AuthorizationException("资源不在你当前商铺查看范围");
			}
			int i = warehouseMapper.deleteByPrimaryKey(whId);
			serverResponse = ServerResponse.createBySuccess("删除成功",i);
			
		}catch (Exception e){
			logger.debug("删除数据库异常");
			serverResponse = ServerResponse.createDefaultErrorMessage(e.getMessage());
		}
		
		logger.info("Exit method deleteWarehouseById(Long whId)  return:"+serverResponse);
		return serverResponse;
	}
	/*
	* @Author zhangxin_an 
	* @Date 2018/4/24 10:49
	* @Params: [warehouseDTO]  
	* @Return com.ok.okhelper.common.ServerResponse  
	* @Description:添加仓库
	*/  
	@Override
	public ServerResponse addWarehouse(WarehouseDTO warehouseDTO) {
		logger.info("Enter method addWarehouse(WarehouseDTO warehouseDTO) Params:"+ warehouseDTO);
		
		if( !ObjectUtils.anyNotNull(warehouseDTO)){
			logger.debug("warehouseDTO 为空");
			throw  new IllegalException("参数为空");
		}
		
		
		Warehouse warehouse = new Warehouse();
		BeanUtils.copyProperties(warehouseDTO,warehouse);
		warehouse.setOperator(JWTUtil.getUserId());
		ServerResponse serverResponse;
		try{
			warehouse.setStoreId(JWTUtil.getStoreId());
			int i = warehouseMapper.insertSelective(warehouse);
			serverResponse = ServerResponse.createBySuccess("添加成功",i);
		}catch (Exception e){
			serverResponse = ServerResponse.createDefaultErrorMessage("数据库添加失败，请检查信息");
		}
		
		
		logger.info("Exit method addWarehouse(WarehouseDTO warehouseDTO) return:"+ serverResponse);
		return serverResponse;
	}
	
	/*
	* @Author zhangxin_an 
	* @Date 2018/5/15 10:15  
	* @Params []  
	* @Return java.util.List<com.ok.okhelper.pojo.bo.IdAndNameBo>  
	* @Description:查询所有可用仓库
	*/  
	@Override
	public List<IdAndNameBo> getWarehouseNameList() {
		logger.info("Enter method getWarehouseNameList");
		List<IdAndNameBo> warehouseName = warehouseMapper.getWarehouseNameList(JWTUtil.getStoreId());
		
		logger.info("Wxit method getWarehouseNameList return "+warehouseName);
		return warehouseName;
	}
}
