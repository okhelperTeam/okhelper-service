package com.ok.okhelper.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ok.okhelper.common.PageModel;
import com.ok.okhelper.dao.SalesOrderDetailMapper;
import com.ok.okhelper.dao.SalesOrderMapper;
import com.ok.okhelper.pojo.dto.SaleOrderDto;
import com.ok.okhelper.pojo.po.SalesOrder;
import com.ok.okhelper.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Author: zc
 * Date: 2018/4/23
 * Description:
 */
@Service
public class SaleServiceImpl implements SaleService {
    @Autowired
    private SalesOrderMapper salesOrderMapper;

    @Autowired
    private SalesOrderDetailMapper salesOrderDetailMapper;

    @Override
    @Transactional
    public PageModel<SalesOrder> getSaleOrderRecords(Long storeId, SaleOrderDto saleOrderDto, Integer pageNum, Integer limit) {
        //启动分页
        PageHelper.startPage(pageNum, limit);

        //启动排序
        PageHelper.orderBy(saleOrderDto.getOrderBy());

        Example example = new Example(SalesOrder.class);
        example.createCriteria()
                .andBetween("createdTime", saleOrderDto.getStartDate(), saleOrderDto.getEndDate())
                .andEqualTo("storeId", storeId);
        List<SalesOrder> salesOrders = salesOrderMapper.selectByExample(example);

        PageInfo<SalesOrder> pageInfo = new PageInfo<>(salesOrders);

        return PageModel.convertToPageModel(pageInfo);
    }

}
