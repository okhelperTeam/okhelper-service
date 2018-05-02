package com.ok.okhelper.service.impl;

import com.ok.okhelper.dao.ProductMapper;
import com.ok.okhelper.dao.SalesOrderDetailMapper;
import com.ok.okhelper.dao.SalesOrderMapper;
import com.ok.okhelper.dao.StockMapper;
import com.ok.okhelper.exception.IllegalException;
import com.ok.okhelper.exception.NotFoundException;
import com.ok.okhelper.pojo.constenum.ConstEnum;
import com.ok.okhelper.pojo.dto.DeliverItemDto;
import com.ok.okhelper.pojo.dto.DeliveryDto;
import com.ok.okhelper.pojo.dto.PlaceOrderItemDto;
import com.ok.okhelper.pojo.po.DeliveryOrderDetail;
import com.ok.okhelper.pojo.po.SalesOrder;
import com.ok.okhelper.pojo.po.SalesOrderDetail;
import com.ok.okhelper.pojo.po.Stock;
import com.ok.okhelper.service.OtherService;
import com.ok.okhelper.shiro.JWTUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.internet.MimeMessage;
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
    private SalesOrderMapper salesOrderMapper;

    @Autowired
    private SalesOrderDetailMapper salesOrderDetailMapper;

    @Autowired
    private StockMapper stockMapper;

    /**
     * @Author zc
     * @Date 2018/4/28 下午11:57
     * @Param [salesOrderDetails]
     * @Return void
     * @Description:检测并减库存
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void checkAndCutStock(List<PlaceOrderItemDto> placeOrderItemDtos) {
        placeOrderItemDtos.forEach(placeOrderItemDto -> {
            int i = productMapper.cutSalesStock(placeOrderItemDto.getSalesCount(), placeOrderItemDto.getProductId());
            if (i <= 0) {
                throw new IllegalException("商品id：" + placeOrderItemDto.getProductId() + "库存不足下单失败");
            }
        });
    }


    /**
     * @Author zc
     * @Date 2018/5/2 下午4:49
     * @Param [deliveryDto]
     * @Return void
     * @Description:校验发货单 //FIXME 放到这里是为了关闭事务提高运行销量
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public void checkDelivery(DeliveryDto deliveryDto) {

        SalesOrder salesOrder = salesOrderMapper.selectByPrimaryKey(deliveryDto.getSalesOrderId());
        if (salesOrder == null) {
            throw new NotFoundException("无此订单");
        }
        if (ConstEnum.SALESTATUS_CLOSE.getCode().equals(salesOrder.getOrderStatus())) {
            throw new IllegalException("订单已经关闭");
        }
        if (ConstEnum.SALESTATUS_NOPAYMENT.getCode().equals(salesOrder.getOrderStatus())) {
            throw new IllegalException("订单未付款");
        }
        if (ObjectUtils.notEqual(ConstEnum.LOGISTICSSTATUS_NOSEND.getCode(), salesOrder.getLogisticsStatus())) {
            throw new IllegalException("订单已出库");
        }
        if (ObjectUtils.notEqual(salesOrder.getStoreId(), JWTUtil.getStoreId())) {
            throw new IllegalException("订单不在你的店铺操作范围");
        }

        //校验发货数量是否正确(java8 分组求和)
        List<DeliverItemDto> deliverItemDtos = deliveryDto.getDeliverItemDtos();

        deliverItemDtos
                .stream()
                .collect(Collectors
                        .groupingBy(DeliverItemDto::getProductId, Collectors.toList()))
                .forEach((pId, deliveryOrderDetailByPids) -> {

                    IntStream sum = deliveryOrderDetailByPids.stream().mapToInt(DeliverItemDto::getDeliveryCount);

                    SalesOrderDetail salesOrderDetail = new SalesOrderDetail();
                    salesOrderDetail.setSalesOrderId(deliveryDto.getSalesOrderId());
                    salesOrderDetail.setProductId(pId);
                    SalesOrderDetail salesOrderDetail1 = salesOrderDetailMapper.selectOne(salesOrderDetail);

                    if (salesOrderDetail1 == null) {
                        throw new IllegalException("要发货的商品信息错误，原订单无此商品，商品Id：" + pId);
                    }

                    if (ObjectUtils.notEqual(salesOrderDetail1.getSalesCount(), sum)) {
                        throw new IllegalException("出库数量与原定的不符合，商品Id：" + pId);
                    }

                });
    }



}
