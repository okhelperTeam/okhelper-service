package com.ok.okhelper.dao;

import com.ok.okhelper.pojo.bo.CustomerDebtBo;
import com.ok.okhelper.pojo.dto.SaleOrderDto;
import com.ok.okhelper.pojo.po.SaleOrder;
import com.ok.okhelper.pojo.vo.ProductCountMapVo;
import com.ok.okhelper.pojo.vo.SaleOrderVo;
import com.ok.okhelper.pojo.vo.SaleTotalVo;
import com.ok.okhelper.util.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SaleOrderMapper extends MyMapper<SaleOrder> {
    List<CustomerDebtBo> getCustomerDebtBo(@Param("storeId") Long storeId, @Param("condition") String condition);

//    List<CustomerDebtGroupBo> getCustomerDebtGroupBo(@Param("storeId") Long storeId, @Param("condition") String condition);

    SaleTotalVo getSaleTotal(@Param("storeId") Long storeId, @Param("startDate") Date startDate,@Param("endDate") Date endDate);

    List<SaleOrderVo> getSaleOrderVo(@Param("storeId") Long storeId, @Param("saleOrderDto")SaleOrderDto saleOrderDto);

    List<ProductCountMapVo> getSaleTotalProductCount(@Param("storeId") Long storeId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    List<SaleOrder> getUnSendOrder(Long storeId);
}