package com.ok.okhelper.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ok.okhelper.common.PageModel;
import com.ok.okhelper.dao.*;
import com.ok.okhelper.exception.IllegalException;
import com.ok.okhelper.exception.NotFoundException;
import com.ok.okhelper.pojo.bo.IdAndNameBo;
import com.ok.okhelper.pojo.bo.StorageDetailBo;
import com.ok.okhelper.common.constenum.ConstEnum;
import com.ok.okhelper.common.constenum.ConstStr;
import com.ok.okhelper.pojo.dto.StorageDetailDto;
import com.ok.okhelper.pojo.dto.StorageOrderDto;
import com.ok.okhelper.pojo.po.*;
import com.ok.okhelper.pojo.vo.StorageOrderVo;
import com.ok.okhelper.service.StockService;
import com.ok.okhelper.service.StorageOrderService;
import com.ok.okhelper.shiro.JWTUtil;
import com.ok.okhelper.util.NumberGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/*
*Author:zhangxin_an
*Description:
*Data:Created in 10:53 2018/5/3
*/
@Service
@Slf4j
public class StorageOrderServiceImpl implements StorageOrderService {
	
	@Autowired
	private StorageOrderMapper storageOrderMapper;
	
	@Autowired
	private StorageOrderDetailMapper storageOrderDetailMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private WarehouseMapper warehouseMapper;
	
	@Autowired
	private SupplierMapper supplierMapper;
	
	@Autowired
	ProductMapper productMapper;
	
	@Autowired
	StockService stockService;
	
	/*
	* @Author zhangxin_an 
	* @Date 2018/5/7 14:31  
	* @Params [storageOrderDto]  
	* @Return void
	* @Description:入库
	*/  
	@Override
	public void insertStorage(StorageOrderDto storageOrderDto) {
		log.info("Enter method insertStorage params:" + storageOrderDto);
		checkStorageOrderDto(storageOrderDto);
        storageOrderDto.setOrderNumber(NumberGenerator.generatorOrderNumber(ConstStr.ODERTR_NUM_PREFIX_INSTOCK, JWTUtil.getUserId()));
		storageOrderDto.setStockiner(JWTUtil.getUserId());
		//添加入库单
		StorageOrder storageOrder = new StorageOrder();
		BeanUtils.copyProperties(storageOrderDto, storageOrder);
		storageOrder.setStoreId(JWTUtil.getStoreId());
		
		List<StorageDetailDto> storageDetailDtos = storageOrderDto.getStorageDetail();
		//获取总价
		BigDecimal sum = storageDetailDtos.stream().map(storageDetailDto ->{
				return  storageDetailDto.getStoragePrice().multiply(new BigDecimal(storageDetailDto.getStorageCount()));
				}
				
			).reduce(BigDecimal.ZERO,BigDecimal::add);
		storageOrder.setTotalPrice(sum);
		
		storageOrderMapper.insertSelective(storageOrder);
		
		//遍历将订单子项插入数据库
		for(StorageDetailDto storageDetailDto : storageDetailDtos){
			sum = new BigDecimal(0);
			StorageOrderDetail storageOrderDetail = new StorageOrderDetail();
			BeanUtils.copyProperties(storageDetailDto, storageOrderDetail);
			storageOrderDetail.setStorageInId(storageOrder.getId());
			//子单价格求和
			sum = sum.add(storageDetailDto.getStoragePrice().multiply(new BigDecimal(storageDetailDto.getStorageCount())));
			
			// 增加库存(分别于库存表商品表修改)
			Stock stock = new Stock();
			stock.setProductDate(storageDetailDto.getProductDate());
			stock.setProductId(storageDetailDto.getProductId());
			stock.setWarehouseId(storageDetailDto.getWarehouseId());
			stock.setShelfLife(storageDetailDto.getShelfLife());
			stockService.updateOrAddStockNumber(stock,storageDetailDto.getStorageCount());
			
			productMapper.addSalesStock(storageDetailDto.getStorageCount(),storageDetailDto.getProductId());
			
			
			storageOrderDetailMapper.insertSelective(storageOrderDetail);
		}
		
		
		
//		StorageOrderVo storageOrderVo = getStorageOrderByOrderNumber(storageOrder.getOrderNumber());
		
		
		log.info("Exit method insertStorage params:" + storageOrderDto);
		
		
//		return storageOrderVo;
	}
	
	/*
	* @Author zhangxin_an 
	* @Date 2018/5/3 17:27
	* @Params [storageOrderDto]  
	* @Return boolean  
	* @Description:检查入库参数
	*/  
	private boolean checkStorageOrderDto(StorageOrderDto storageOrderDto){
		
		List<StorageDetailDto> storageDetailDtos = storageOrderDto.getStorageDetail();
		if (CollectionUtils.isEmpty(storageDetailDtos)) {
			throw new IllegalException("子项为空");
		}
		storageDetailDtos.forEach(storageDetailDto -> {
			//子项参数不为空
			if (storageDetailDto.getProductDate() == null
					|| storageDetailDto.getShelfLife() == null
					|| storageDetailDto.getProductId() == null
					|| storageDetailDto.getStorageCount() == null
					|| storageDetailDto.getStoragePrice() == null
					|| storageDetailDto.getProductId() == null
					|| storageDetailDto.getWarehouseId() == null) {
				throw new IllegalException("参数不完整");
			}
			
			Warehouse warehouse = warehouseMapper.selectByPrimaryKey(storageDetailDto.getWarehouseId());
			if(warehouse == null || warehouse.getDeleteStatus() == ConstEnum.STATUSENUM_UNAVAILABLE.getCode())
				throw new IllegalException("所选仓库不可用");
			
			Product product = productMapper.selectByPrimaryKey(storageDetailDto.getProductId());
			if(product == null || product.getDeleteStatus() == ConstEnum.STATUSENUM_UNAVAILABLE.getCode())
				throw new IllegalException("商品不存在");
			
		});
			
			return true;
	}
	
	
	/*
	* @Author zhangxin_an 
	* @Date 2018/5/3 18:55
	* @Params [orderNumber]  
	* @Return com.ok.okhelper.pojo.vo.StorageOrderVo  
	* @Description:获取订单
*/
	@Override
	public StorageOrderVo getStorageOrderByOrderNumber(String orderNumber) {
		log.info("Enter method getStorageOrderByOrderNUmber params:" + orderNumber);
		
		if (StringUtils.isBlank(orderNumber)) {
			throw new IllegalException("参数错误");
		}
		
		//前端订单数据
		StorageOrderVo storageOrderVo = new StorageOrderVo();
		StorageOrder storageOrder = storageOrderMapper.getStorageOrderByOrderNumber(orderNumber);
		if(storageOrder == null){
			throw new NotFoundException("未找到该订单");
		}
		BeanUtils.copyProperties(storageOrder, storageOrderVo);
		
		//获取子项
		List<StorageDetailBo> storageDetailBoList = getStorageDetailBo(storageOrder.getId());
		storageOrderVo.setStorageDetail(storageDetailBoList);
		
		//转换入库员，供应商id到name,前端识别
		
		IdAndNameBo stockiner = userMapper.getIdAndName(storageOrder.getStockiner());
		IdAndNameBo supplier = supplierMapper.getIdAndName(storageOrder.getSupplierId());
		storageOrderVo.setStockiner(stockiner);
		storageOrderVo.setSupplier(supplier);
		
		
		log.info("Exit method getStorageOrderByOrderNUmber params:" + storageOrderVo);
		return storageOrderVo;
	}
	
	
	/*
	* @Author zhangxin_an 
	* @Date 2018/5/3 14:03
	* @Params [id]  
	* @Return java.util.List<com.ok.okhelper.pojo.bo.StorageDetailBo>  
	* @Description:订单Id获取子项
	*/
	private List<StorageDetailBo> getStorageDetailBo(Long id) {
		
		log.info("Enter method getStorageDetailBo params:" + id);
		
		//获取入货单子项
		List<StorageOrderDetail> storageOrderDetailList = storageOrderDetailMapper.getStorageOrderDetailByOrderId(id);
		if(CollectionUtils.isEmpty(storageOrderDetailList)){
			throw new IllegalException("子项为空");
		}
		
		//前端展示子项数据
		List<StorageDetailBo> storageDetailBoList = new ArrayList<>(storageOrderDetailList.size());
		
		IdAndNameBo warehouse;
		IdAndNameBo product;
		StorageDetailBo storageDetailBo;
		for(StorageOrderDetail storageOrderDetail : storageOrderDetailList){
			warehouse = warehouseMapper.getIdAndName(storageOrderDetail.getWarehouseId());
			product = productMapper.getIdAndName(storageOrderDetail.getProductId());
			storageDetailBo = new StorageDetailBo();
			BeanUtils.copyProperties(storageOrderDetail, storageDetailBo);
			storageDetailBo.setWarehouse(warehouse);
			storageDetailBo.setProduct(product);
			storageDetailBoList.add(storageDetailBo);
		}
		
		log.info("Exit method getStorageDetailBo params:" + storageDetailBoList);
		return storageDetailBoList;
	}
	
	
	/*
	* @Author zhangxin_an 
	* @Date 2018/5/6 20:39
	* @Params [pageModel]  
	* @Return java.util.List<com.ok.okhelper.pojo.vo.StorageOrderVo>  
	* @Description:获取所有入库单
	*/  
	@Override
	public PageModel<StorageOrderVo> getStorageOrderList(PageModel pageModel) {
		
		log.info("Enter method getStorageOrderList params:" + pageModel);
		//启动分页
		PageHelper.startPage(pageModel.getPageNum(), pageModel.getLimit());
		
		
		//启动排序
		PageHelper.orderBy(pageModel.getOrderBy());
		
		StorageOrder storageOrder = new StorageOrder();
		storageOrder.setStoreId(JWTUtil.getStoreId());
		
		List<StorageOrder> storageOrderList = storageOrderMapper.select(storageOrder);
		
		if(CollectionUtils.isEmpty(storageOrderList)){
			throw new NotFoundException("没有入货单");
		}
		
		//前端订单数据
		List<StorageOrderVo> storageOrderVoList = new ArrayList<>(storageOrderList.size());
		//转换数据前段可读
		changeStorageOrederToVo(storageOrderList, storageOrderVoList);
		
		
		log.info("Exit method getStorageOrderList params:" + storageOrderVoList);
		PageInfo<StorageOrderVo> pageInfo = new PageInfo<>(storageOrderVoList);
		log.info("Exit method getProductsListByCategory() return:" + pageInfo);
		return PageModel.convertToPageModel(pageInfo);
		
	}
	/*
	* @Author zhangxin_an 
	* @Date 2018/5/12 9:56  
	* @Params [pageModel]
	* @Return com.ok.okhelper.common.PageModel<com.ok.okhelper.pojo.vo.StorageOrderVo>  
	* @Description:供应商订单
	*/  	
	@Override
	public PageModel<StorageOrderVo> getStorageOrderListBySupplierId(PageModel pageModel,Long supplierId) {
		log.info("Enter method  getStorageOrderListBySupplierId params:" + pageModel+supplierId);
		//启动分页
		PageHelper.startPage(pageModel.getPageNum(), pageModel.getLimit());
		
		
		//启动排序
		PageHelper.orderBy(pageModel.getOrderBy());
		
		StorageOrder storageOrderEg = new StorageOrder();
		storageOrderEg.setSupplierId(supplierId);
		storageOrderEg.setStoreId(JWTUtil.getStoreId());
		List<StorageOrder> storageOrderList = storageOrderMapper.select(storageOrderEg);
		
		if(CollectionUtils.isEmpty(storageOrderList)){
			throw new NotFoundException("没有该供应商记录");
		}
		
		//前端订单数据
		List<StorageOrderVo> storageOrderVoList = new ArrayList<>(storageOrderList.size());
		
		//转换数据前段可读
		changeStorageOrederToVo(storageOrderList, storageOrderVoList);
		
		
		log.info("Exit method getStorageOrderList params:" + storageOrderVoList);
		PageInfo<StorageOrderVo> pageInfo = new PageInfo<>(storageOrderVoList);
		log.info("Exit method getStorageOrderListBySupplierId() return:" + pageInfo);
		return PageModel.convertToPageModel(pageInfo);
	}
	
	private void changeStorageOrederToVo(List<StorageOrder> storageOrderList, List<StorageOrderVo> storageOrderVoList) {
		StorageOrderVo storageOrderVo;
		for(StorageOrder storageOrder : storageOrderList){
			storageOrderVo = new StorageOrderVo();
			
			BeanUtils.copyProperties(storageOrder, storageOrderVo);
			
			//转换入库员，供应商id到name,前端识别
			IdAndNameBo stockiner = userMapper.getIdAndName(storageOrder.getStockiner());
			IdAndNameBo supplier = supplierMapper.getIdAndName(storageOrder.getSupplierId());
			storageOrderVo.setStockiner(stockiner);
			storageOrderVo.setSupplier(supplier);
			
			storageOrderVoList.add(storageOrderVo);
		
		}
	}
	
	
}
