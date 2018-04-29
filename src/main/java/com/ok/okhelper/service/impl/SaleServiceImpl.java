package com.ok.okhelper.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ok.okhelper.common.PageModel;
import com.ok.okhelper.dao.ProductMapper;
import com.ok.okhelper.dao.SalesOrderDetailMapper;
import com.ok.okhelper.dao.SalesOrderMapper;
import com.ok.okhelper.exception.IllegalException;
import com.ok.okhelper.pojo.constenum.ConstEnum;
import com.ok.okhelper.pojo.dto.PlaceOrderDto;
import com.ok.okhelper.pojo.dto.PlaceOrderItemDto;
import com.ok.okhelper.pojo.dto.SaleOrderDto;
import com.ok.okhelper.pojo.dto.SaleTotalVo;
import com.ok.okhelper.pojo.po.Product;
import com.ok.okhelper.pojo.po.SalesOrder;
import com.ok.okhelper.pojo.po.SalesOrderDetail;
import com.ok.okhelper.service.SaleService;
import com.ok.okhelper.shiro.JWTUtil;
import com.ok.okhelper.until.NumberGenerator;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.util.Date;
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

    @Autowired
    private ProductMapper productMapper;

    /**
     * 库存不足
     */
    public static final int LOW_STOCK = 0;


    /**
     * @Author zc
     * @Date 2018/4/29 上午11:01
     * @Param [storeId, saleOrderDto, pageNum, limit]
     * @Return com.ok.okhelper.common.PageModel<com.ok.okhelper.pojo.po.SalesOrder>
     * @Description:获取指定时间内的历史订单(包含已关闭订单)
     */
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

    /**
     * @Author zc
     * @Date 2018/4/29 上午11:00
     * @Param [storeId, startDate, endDate]
     * @Return com.ok.okhelper.pojo.dto.SaleTotalVo
     * @Description:获取指定时间范围的销售聚合(去除关闭订单)
     */
    @Override
    @Transactional
    public SaleTotalVo getSaleTotalVo(Long storeId, Date startDate, Date endDate) {
        Example example = new Example(SalesOrder.class);
        example.createCriteria()
                .andBetween("createdTime", startDate, endDate)
                .andEqualTo("storeId", storeId)
                .andNotEqualTo("orderStatus", ConstEnum.SALESTATUS_CLOSE.getCode());
        List<SalesOrder> salesOrders = salesOrderMapper.selectByExample(example);


        SaleTotalVo saleTotalVo = new SaleTotalVo();
        saleTotalVo.setSaleCount(salesOrders.size());
        BigDecimal total = new BigDecimal(0.0);

        if (salesOrders.size() > 0) {
            salesOrders.forEach(salesOrder -> {
                total.add(salesOrder.getSumPrice());
            });
        }
        saleTotalVo.setTotalSales(total);

        return saleTotalVo;
    }

    /**
     * @Author zc
     * @Date 2018/4/29 上午11:00
     * @Param [storeId, seller, placeOrderDto]
     * @Return java.lang.String
     * @Description:下单并付款
     */
    @Override
    @Transactional
    public String placeOrder(Long storeId, Long seller, PlaceOrderDto placeOrderDto) {
        List<PlaceOrderItemDto> placeOrderItemDtos = placeOrderDto.getPlaceOrderItemDtos();

        Boolean isStock = checkAndCutStock(placeOrderItemDtos);
        if (!isStock) {
            throw new IllegalException("库存不足,下单失败");
        }

        placeOrderDto.setSeller(seller);
        placeOrderDto.setStoreId(storeId);
        placeOrderDto.setOrderNumber(NumberGenerator.generatorPlaceOrderNumber(seller));

        BigDecimal toBePaid = placeOrderDto.getToBePaid();
        if (toBePaid != null && toBePaid.doubleValue() > 0.0) {
            placeOrderDto.setOrderStatus(ConstEnum.SALESTATUS_DEBT.getCode());
        } else {
            placeOrderDto.setOrderStatus(ConstEnum.SALESTATUS_PAID.getCode());
        }

        placeOrderDto.setLogisticsStatus(ConstEnum.LOGISTICSSTATUS_NOSEND.getCode());

        SalesOrder salesOrder = new SalesOrder();
        BeanUtils.copyProperties(placeOrderDto, salesOrder);

        salesOrderMapper.insertSelective(salesOrder);

        Long saleOrderId = salesOrder.getId();

        if (CollectionUtils.isNotEmpty(placeOrderItemDtos)) {
            placeOrderItemDtos.forEach(placeOrderItemDto -> {
               SalesOrderDetail salesOrderDetail=new SalesOrderDetail();
               BeanUtils.copyProperties(placeOrderDto,salesOrderDetail);
                salesOrderDetail.setSalesOrderId(saleOrderId);
                Product product = productMapper.selectByPrimaryKey(salesOrderDetail.getProductId());
                salesOrderDetail.setMainImg(product.getMainImg());
                salesOrderDetail.setProductName(product.getProductName());
                salesOrderDetail.setProductTitle(product.getProductTitle());
                salesOrderDetailMapper.insertSelective(salesOrderDetail);
            });
        }

        //TODO
        return null;
    }


    /**
     * @Author zc
     * @Date 2018/4/28 下午11:57
     * @Param [salesOrderDetails]
     * @Return void
     * @Description:检测并减库存
     */
    @Transactional
    public Boolean checkAndCutStock(List<PlaceOrderItemDto> placeOrderItemDtos) {
        placeOrderItemDtos.forEach(placeOrderItemDto -> {
            int i = productMapper.cutSalesStock(placeOrderItemDto.getSalesCount(), placeOrderItemDto.getProductId());
            if (i <= 0) {
                throw new IllegalException("商品id：" + placeOrderItemDto.getProductId() + "库存不足下单失败");
            }
        });
        return true;
    }

}
