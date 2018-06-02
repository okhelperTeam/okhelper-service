package com.ok.okhelper.service.impl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ok.okhelper.common.PageModel;
import com.ok.okhelper.common.ServerResponse;
import com.ok.okhelper.dao.SupplierMapper;
import com.ok.okhelper.exception.IllegalException;
import com.ok.okhelper.common.constenum.ConstEnum;
import com.ok.okhelper.pojo.dto.SupplierDto;
import com.ok.okhelper.pojo.po.Supplier;
import com.ok.okhelper.service.SupplierService;
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
*Data:Created in 14:05 2018/4/24
*/
@Service
public class SupplierServiceImpl implements SupplierService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SupplierMapper supplierMapper;
	/*
	* @Author zhangxin_an
	* @Date 2018/4/24 14:09
	* @Params: []
	* @Return java.util.List<com.ok.okhelper.pojo.vo.SupplierVo>
	* @Description:查询当前店铺所有供应商
	*/
	@Override
	public PageModel<Supplier> getSupplierList(PageModel pageModel) {
		logger.info("Enter method getSupplierList()");
		//启动分页
		PageHelper.startPage(pageModel.getPageNum(), pageModel.getLimit());

		//启动排序
		PageHelper.orderBy(pageModel.getOrderBy());
		
		Long storeId = JWTUtil.getStoreId();
		if(storeId == null){
			throw new IllegalException("参数异常");
		}
		List<Supplier> supplierList = supplierMapper.getSupplierByStoreId(storeId);
		
		PageInfo<Supplier> pageInfo = new PageInfo<>(supplierList);
		logger.info("Exit method getSupplierList() Params:"+pageInfo);
		return PageModel.convertToPageModel(pageInfo);
	}
	
	@Override
	public Supplier getSupplierById(Long supplierId) {
		
		logger.info("Enter method getSupplierById() Params:"+ supplierId);
		if(supplierId == null){
			throw new IllegalException("参数异常");
		}
		
		Supplier supplier = supplierMapper.selectByPrimaryKey(supplierId);
		if (supplier == null){
			throw new IllegalException("未找到当前供应商");
		}

		if (ObjectUtils.notEqual(supplier.getStoreId(), JWTUtil.getStoreId())) {
			throw new AuthorizationException("资源不在你当前商铺查看范围");
		}
		if (ConstEnum.STATUSENUM_UNAVAILABLE.getCode() == supplier.getDeleteStatus()) {
			throw new IllegalException("当前资源状态不可用");
		}

		logger.info("Exit method getSupplierById() return:"+ supplier);
		return supplier;
	}
	
	
	/*
	* @Author zhangxin_an
	* @Date 2018/4/24 14:37
	* @Params: [supplierDto]
	* @Return com.ok.okhelper.common.ServerResponse
	* @Description:更新供应商信息
	*/
	@Override
	public ServerResponse updateSupplier(Long id, SupplierDto supplierDto) {
		
		logger.info("Enter method updateSupplier(SupplierDto supplierDto) Params:"+ supplierDto);
		if( !ObjectUtils.anyNotNull(supplierDto)){
			logger.debug("supplierDto 为空");
			throw  new IllegalException("参数为空");
		}

		if (id == null) {
			
			logger.debug("供应商id 为空");
			throw  new IllegalException("更新时Id为空");
		}
		
		Supplier supplier = new Supplier();
		BeanUtils.copyProperties(supplierDto,supplier);
		supplier.setOperator(JWTUtil.getUserId());
		supplier.setStoreId(JWTUtil.getStoreId());
		ServerResponse serverResponse;
		try {
			
			int i = supplierMapper.updateByPrimaryKeySelective(supplier);
			serverResponse = ServerResponse.createBySuccess();
		}catch (Exception e){
			logger.debug("更新数据库异常");
			serverResponse = ServerResponse.createBySuccessMessage(e.getMessage());
		}
		
		
		logger.info("Exit method updateSupplier(SupplierDto supplierDto) return:"+serverResponse);
		return serverResponse;
	}
	/*
	* @Author zhangxin_an
	* @Date 2018/4/24 14:49
	* @Params: [supplierId]
	* @Return com.ok.okhelper.common.ServerResponse
	* @Description:删除供应商
	*/
	@Override
	public ServerResponse deleteSupplierById(Long supplierId) {
		
		logger.info("Enter method deleteSupplierById(Long supplierId) Params:"+ supplierId);
		if(supplierId == null){
			throw new IllegalException("请求参数异常");
		}
		ServerResponse serverResponse;
		
		//供应商is_Delete置0；
		try {
			
			Supplier supplier = supplierMapper.selectByPrimaryKey(supplierId);
			if (ObjectUtils.notEqual(supplier.getStoreId(), JWTUtil.getStoreId())) {
				throw new AuthorizationException("资源不在你当前商铺查看范围");
			}
			
			supplier.setOperator(JWTUtil.getUserId());
			supplier.setDeleteStatus(ConstEnum.STATUSENUM_UNAVAILABLE.getCode());
			supplierMapper.updateByPrimaryKeySelective(supplier);
			
			serverResponse = ServerResponse.createBySuccess("删除成功");
			
		}catch (Exception e){
			logger.debug("删除数据库异常");
			serverResponse = ServerResponse.createDefaultErrorMessage(e.getMessage());
		}
		
		logger.info("Exit method deleteSupplierById(Long supplierId)  return:"+serverResponse);
		return serverResponse;
	}
	/*
	* @Author zhangxin_an
	* @Date 2018/4/24 14:49
	* @Params: [supplierDto]
	* @Return com.ok.okhelper.common.ServerResponse
	* @Description:添加供应商
	*/
	@Override
	public ServerResponse addSupplier(SupplierDto supplierDto) {
		logger.info("Enter method addSupplier(SupplierDto supplierDto) Params:"+ supplierDto);
		
		if( !ObjectUtils.anyNotNull(supplierDto)){
			logger.debug("supplierDto 为空");
			throw  new IllegalException("参数为空");
		}
		
		if(StringUtils.isBlank(supplierDto.getSupplierName())
				||StringUtils.isBlank(supplierDto.getSupplierAddress())
				||StringUtils.isBlank(supplierDto.getSupplierPhone())
				||StringUtils.isBlank(supplierDto.getSupplierContacts())
				){
			logger.debug("供应商名称，地址，联系人，电话不能为空");
			throw  new IllegalException("供应商名称，地址，联系人，电话不能为空");
		}
		
		
		Supplier supplier = new Supplier();
		BeanUtils.copyProperties(supplierDto,supplier);
		
		supplier.setOperator(JWTUtil.getUserId());
		ServerResponse serverResponse;
		try{
			supplier.setStoreId(JWTUtil.getStoreId());
			int i = supplierMapper.insertSelective(supplier);
			serverResponse = ServerResponse.createBySuccess("添加成功",i);
		}catch (Exception e){
			serverResponse = ServerResponse.createByErrorCodeMessage(400, "数据库添加失败，请检查信息");
		}
		
		
		logger.info("Exit method addSupplier(SupplierDto supplierDto) return:"+ serverResponse);
		return serverResponse;
	}
}
