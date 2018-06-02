package com.ok.okhelper.service.impl;

import com.ok.okhelper.dao.ProductMapper;
import com.ok.okhelper.dao.SaleOrderDetailMapper;
import com.ok.okhelper.dao.SaleOrderMapper;
import com.ok.okhelper.dao.StockMapper;
import com.ok.okhelper.exception.IllegalException;
import com.ok.okhelper.exception.NotFoundException;
import com.ok.okhelper.common.constenum.ConstEnum;
import com.ok.okhelper.pojo.dto.DeliverItemDto;
import com.ok.okhelper.pojo.dto.DeliveryDto;
import com.ok.okhelper.pojo.dto.PlaceOrderItemDto;
import com.ok.okhelper.pojo.po.SaleOrder;
import com.ok.okhelper.pojo.po.SaleOrderDetail;
import com.ok.okhelper.service.OtherService;
import com.ok.okhelper.shiro.JWTUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Author: zc
 * Date: 2018/5/2
 * Description:
 */
@Service
public class OtherServiceImpl implements OtherService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private SaleOrderMapper saleOrderMapper;

    @Autowired
    private SaleOrderDetailMapper saleOrderDetailMapper;

    @Autowired
    private StockMapper stockMapper;

    /**
     * @Author zc
     * @Date 2018/4/28 下午11:57
     * @Param [placeOrderItemDtos]
     * @Return void
     * @Description:检测并减库存
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void checkAndCutStock(List<PlaceOrderItemDto> placeOrderItemDtos) {
        placeOrderItemDtos.forEach(placeOrderItemDto -> {
            int i = productMapper.cutSalesStock(placeOrderItemDto.getSaleCount(), placeOrderItemDto.getProductId());
            if (i <= 0) {
                throw new IllegalException("下单失败：" + placeOrderItemDto.getProductName() + "库存不足或已经下架");
            }
        });
    }


    /**
     * @Author zc
     * @Date 2018/5/2 下午4:49
     * @Param [deliveryDto]
     * @Return void
     * @Description:校验发货单 //FIXME 放到这里是为了关闭事务提高运行效率
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public void checkDelivery(DeliveryDto deliveryDto) {

        SaleOrder saleOrder = saleOrderMapper.selectByPrimaryKey(deliveryDto.getSaleOrderId());
        if (saleOrder == null) {
            throw new NotFoundException("无此订单");
        }
        if (ObjectUtils.notEqual(saleOrder.getStoreId(), JWTUtil.getStoreId())) {
            throw new AuthorizationException("资源不在你当前商铺查看范围");
        }
        if (ConstEnum.SALESTATUS_CLOSE.getCode() == saleOrder.getOrderStatus()) {
            throw new IllegalException("订单已经关闭");
        }
        if (ConstEnum.SALESTATUS_NOPAYMENT.getCode() == saleOrder.getOrderStatus()) {
            throw new IllegalException("订单未付款");
        }
        if (ConstEnum.LOGISTICSSTATUS_NOSEND.getCode() != saleOrder.getLogisticsStatus()) {
            throw new IllegalException("订单已出库");
        }

        //校验发货数量是否正确(java8 分组求和)
        List<DeliverItemDto> deliverItemDtos = deliveryDto.getDeliverItemDtos();

        deliverItemDtos
                .stream()
                .collect(Collectors
                        .groupingBy(DeliverItemDto::getProductId, Collectors.toList()))
                .forEach((pId, deliveryOrderDetailByPids) -> {

                    IntStream sum = deliveryOrderDetailByPids.stream().mapToInt(DeliverItemDto::getDeliveryCount);

                    SaleOrderDetail saleOrderDetail = new SaleOrderDetail();
                    saleOrderDetail.setSaleOrderId(deliveryDto.getSaleOrderId());
                    saleOrderDetail.setProductId(pId);
                    SaleOrderDetail dbsaleOrderDetail = saleOrderDetailMapper.selectOne(saleOrderDetail);

                    if (dbsaleOrderDetail == null) {
                        throw new IllegalException("要发货的商品信息错误，原订单无此商品，商品Id：" + pId);
                    }

                    if (ObjectUtils.notEqual(dbsaleOrderDetail.getSaleCount(), sum)) {
                        throw new IllegalException("出库数量与原定的不符合，商品Id：" + pId);
                    }

                });
    }



}
