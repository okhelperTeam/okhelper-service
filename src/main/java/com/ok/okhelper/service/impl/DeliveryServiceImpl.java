package com.ok.okhelper.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ok.okhelper.common.PageModel;
import com.ok.okhelper.dao.*;
import com.ok.okhelper.exception.IllegalException;
import com.ok.okhelper.exception.NotFoundException;
import com.ok.okhelper.common.constenum.ConstEnum;
import com.ok.okhelper.common.constenum.ConstStr;
import com.ok.okhelper.pojo.dto.DeliveryDto;
import com.ok.okhelper.pojo.po.*;
import com.ok.okhelper.service.DeliveryService;
import com.ok.okhelper.service.OtherService;
import com.ok.okhelper.shiro.JWTUtil;
import com.ok.okhelper.util.NumberGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: zc
 * Date: 2018/5/2
 * Description:
 */
@Service
@Slf4j
public class DeliveryServiceImpl implements DeliveryService {
    @Autowired
    private OtherService otherService;

    @Autowired
    private DeliveryOrderMapper deliveryOrderMapper;

    @Autowired
    private DeliveryOrderDetailMapper deliveryOrderDetailMapper;

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private SaleOrderMapper saleOrderMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String mailUsername;

    /**
     * @Author zc
     * @Date 2018/5/2 下午5:13
     * @Param [deliveryDto]
     * @Return 发货单
     * @Description:发货/出库
     */
    @Transactional
    public Long deliverGoods(DeliveryDto deliveryDto) {
        //校验发货单与子项
        otherService.checkDelivery(deliveryDto);

        //插入发货单
        DeliveryOrder deliveryOrder = new DeliveryOrder();
        deliveryOrder.setSaleOrderId(deliveryDto.getSaleOrderId());
        deliveryOrder.setStockouter(JWTUtil.getUserId());
        deliveryOrder.setStoreId(JWTUtil.getStoreId());
        deliveryOrder.setOrderNumber(NumberGenerator.generatorOrderNumber(ConstStr.ODERTR_NUM_PREFIX_OUTSTOCK, JWTUtil.getUserId()));

        deliveryOrderMapper.insertSelective(deliveryOrder);
        Long deliveryOrderId = deliveryOrder.getId();

        //向子项中注入发货单Id
        List<DeliveryOrderDetail> deliveryOrderDetails =
                deliveryDto.getDeliverItemDtos()
                        .stream()
                        .map(x -> {
                            DeliveryOrderDetail deliveryOrderDetail = new DeliveryOrderDetail();
                            BeanUtils.copyProperties(x, deliveryOrderDetail);
                            deliveryOrderDetail.setDeliveryOrderId(deliveryOrderId);
                            return deliveryOrderDetail;
                        }).collect(Collectors.toList());
        //插入子项
        deliveryOrderDetailMapper.insertList(deliveryOrderDetails);

        //修改真实库存
        deliveryOrderDetails.forEach(x -> {
            Stock stock = new Stock();
            stock.setOperator(JWTUtil.getUserId());
            stock.setProductId(x.getProductId());
            stock.setWarehouseId(x.getWarehouseId());
            stock.setProductDate(x.getProductDate());

            Stock dbstock = stockMapper.selectOne(stock);
            if (dbstock == null) {
                throw new NotFoundException("库存不存在，商品Id：" + x.getProductId() + "仓库Id：" + x.getWarehouseId() + "生产日期：" + x.getProductDate());
            }
            if (dbstock.getStockCount() < x.getDeliveryCount()) {
                throw new IllegalException("库存不足请重新出库，商品Id：" + x.getProductId() + "仓库Id：" + x.getWarehouseId() + "生产日期：" + x.getProductDate());
            }

            dbstock.setStockCount(dbstock.getStockCount() - x.getDeliveryCount());

            stockMapper.updateByPrimaryKeySelective(dbstock);
        });

        //修改订单状态
        SaleOrder saleOrder = new SaleOrder();
        saleOrder.setId(deliveryDto.getSaleOrderId());
        saleOrder.setLogisticsStatus(ConstEnum.LOGISTICSSTATUS_SEND.getCode());
        saleOrder.setStockouter(JWTUtil.getUserId());
        saleOrder.setSendTime(new Date());
        saleOrderMapper.updateByPrimaryKeySelective(saleOrder);

        return deliveryOrderId;
    }


    /**
     * @Author zc
     * @Date 2018/5/2 下午6:04
     * @Param [customId, deliveryOrderId]
     * @Return void
     * @Description:给客户发邮件
     */
    @Async
    public void sendEmail(Long saleOrderId) {
        SaleOrder saleOrder = saleOrderMapper.selectByPrimaryKey(saleOrderId);

        Long customerId = saleOrder.getCustomerId();

        Customer customer = customerMapper.selectByPrimaryKey(customerId);

        if (customer != null && StringUtils.isNotBlank(customer.getCustomerEmail())) {

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(mailUsername);
            message.setTo(customer.getCustomerEmail());
            message.setSubject("标题：发货通知");
            message.setText(customer.getCustomerName() + "你好，你的订单：" + saleOrder.getOrderNumber() + "已经发货了");
            try {
                mailSender.send(message);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
    }

    @Override
    public PageModel<SaleOrder> getUnSendOrder(PageModel pageModel) {
        //启动分页
        PageHelper.startPage(pageModel.getPageNum(), pageModel.getLimit());

        //启动排序
        PageHelper.orderBy(pageModel.getOrderBy());

        List<SaleOrder> unSendOrder = saleOrderMapper.getUnSendOrder(JWTUtil.getStoreId());

        if(CollectionUtils.isEmpty(unSendOrder)){
            throw new NotFoundException("没有未发货订单");
        }

        PageInfo pageInfo=new PageInfo();

        return null;
    }
}
