package com.ok.okhelper.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ok.okhelper.common.PageModel;
import com.ok.okhelper.dao.*;
import com.ok.okhelper.exception.IllegalException;
import com.ok.okhelper.pojo.constenum.ConstEnum;
import com.ok.okhelper.pojo.constenum.ConstStr;
import com.ok.okhelper.pojo.dto.PlaceOrderDto;
import com.ok.okhelper.pojo.dto.PlaceOrderItemDto;
import com.ok.okhelper.pojo.dto.SaleOrderDto;
import com.ok.okhelper.pojo.vo.SaleTotalVo;
import com.ok.okhelper.pojo.po.Product;
import com.ok.okhelper.pojo.po.SaleOrder;
import com.ok.okhelper.pojo.po.SaleOrderDetail;
import com.ok.okhelper.pojo.vo.PlaceOrderVo;
import com.ok.okhelper.service.OtherService;
import com.ok.okhelper.service.ProductService;
import com.ok.okhelper.service.SaleService;
import com.ok.okhelper.shiro.JWTUtil;
import com.ok.okhelper.until.NumberGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Author: zc
 * Date: 2018/4/23
 * Description:
 */
@Service
@Slf4j
public class SaleServiceImpl implements SaleService {
    @Autowired
    private SaleOrderMapper saleOrderMapper;

    @Autowired
    private SaleOrderDetailMapper saleOrderDetailMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Lazy
    @Autowired
    private ProductService productService;

    @Autowired
    private OtherService otherService;

    /**
     * 库存不足
     */
    public static final int LOW_STOCK = 0;


    /**
     * @Author zc
     * @Date 2018/4/29 上午11:01
     * @Param [storeId, saleOrderDto, pageNum, limit]
     * @Return com.ok.okhelper.common.PageModel<com.ok.okhelper.pojo.po.SaleOrder>
     * @Description:获取指定时间内的历史订单(包含已关闭订单)
     */
    @Override
    public PageModel<SaleOrder> getSaleOrderRecords(Long storeId, SaleOrderDto saleOrderDto, Integer pageNum, Integer limit) {
        //启动分页
        PageHelper.startPage(pageNum, limit);

        //启动排序
        PageHelper.orderBy(saleOrderDto.getOrderBy());

        Example example = new Example(SaleOrder.class);
        example.createCriteria()
                .andBetween("createdTime", saleOrderDto.getStartDate(), saleOrderDto.getEndDate())
                .andEqualTo("storeId", storeId);
        List<SaleOrder> saleOrders = saleOrderMapper.selectByExample(example);

        PageInfo<SaleOrder> pageInfo = new PageInfo<>(saleOrders);

        return PageModel.convertToPageModel(pageInfo);
    }

    /**
     * @Author zc
     * @Date 2018/4/29 上午11:00
     * @Param [storeId, startDate, endDate]
     * @Return com.ok.okhelper.pojo.vo.SaleTotalVo
     * @Description:获取指定时间范围的销售聚合 (去除 已关闭订单)
     */
    @Override
    public SaleTotalVo getSaleTotalVo(Long storeId, Date startDate, Date endDate) {
        Example example = new Example(SaleOrder.class);
        example.createCriteria()
                .andBetween("createdTime", startDate, endDate)
                .andEqualTo("storeId", storeId)
                .andNotEqualTo("orderStatus", ConstEnum.SALESTATUS_CLOSE.getCode());
        List<SaleOrder> saleOrders = saleOrderMapper.selectByExample(example);


        SaleTotalVo saleTotalVo = new SaleTotalVo();
        saleTotalVo.setSaleCount(saleOrders.size());

        //java 8流式操作集合 计算总和
        BigDecimal totalMoney = saleOrders
                .stream()
                .filter(saleOrder -> saleOrder.getSumPrice() != null)
                .map(SaleOrder::getSumPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        saleTotalVo.setTotalSales(totalMoney);

        return saleTotalVo;
    }

    /**
     * @Author zc
     * @Date 2018/4/29 上午11:00
     * @Param [storeId, seller, placeOrderDto]
     * @Return java.lang.String  返回订单vo
     * @Description:下单并付款
     */
    @Override
    @Transactional
    public PlaceOrderVo placeOrder(Long storeId, Long seller, PlaceOrderDto placeOrderDto) {
        List<PlaceOrderItemDto> placeOrderItemDtos = placeOrderDto.getPlaceOrderItemDtos();

        //TODO 试一试 去掉try catch
        otherService.checkAndCutStock(placeOrderItemDtos);

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

        SaleOrder saleOrder = new SaleOrder();
        BeanUtils.copyProperties(placeOrderDto, saleOrder);

        saleOrderMapper.insertSelective(saleOrder);

        if (CollectionUtils.isNotEmpty(placeOrderItemDtos)) {
            assembleSaleOrderDetail(placeOrderItemDtos, saleOrder.getId());
        }

        PlaceOrderVo placeOrderVo = new PlaceOrderVo();
        BeanUtils.copyProperties(saleOrder, placeOrderVo);

        return placeOrderVo;
    }


    /**
     * @Author zc
     * @Date 2018/4/30 下午1:56
     * @Param [placeOrderItemDtos]
     * @Return void
     * @Description:组装订单子项并持久化到数据库
     */
    public void assembleSaleOrderDetail(List<PlaceOrderItemDto> placeOrderItemDtos, Long saleOrderId) {
        placeOrderItemDtos.forEach(placeOrderItemDto -> {
            SaleOrderDetail saleOrderDetail = new SaleOrderDetail();
            Product product = productService.getProduct(saleOrderDetail.getProductId());
            saleOrderDetail.setMainImg(product.getMainImg());
            saleOrderDetail.setProductName(product.getProductName());
            saleOrderDetail.setProductTitle(product.getProductTitle());
            saleOrderDetail.setSaleOrderId(saleOrderId);
            saleOrderDetail.setSaleCount(placeOrderItemDto.getSaleCount());
            saleOrderDetail.setSalePrice(placeOrderItemDto.getSalePrice());
            saleOrderDetailMapper.insertSelective(saleOrderDetail);
        });
    }

    /**
     * @Author zc
     * @Date 2018/4/30 下午1:53
     * @Param [placeOrderItemDtos]
     * @Return void
     * @Description:记录热销缓存
     */
    @Async
    public void recordHotSale(List<PlaceOrderItemDto> placeOrderItemDtos) {
        String zkey = ConstStr.HOT_SALE + ":" + JWTUtil.getStoreId() + ":" + DateFormatUtils.format(new Date(), "yyyyMMdd");
        placeOrderItemDtos.forEach(placeOrderItemDto -> {
            Long productId = placeOrderItemDto.getProductId();
            Integer salesCount = placeOrderItemDto.getSaleCount();
            redisTemplate.opsForZSet().incrementScore(zkey, String.valueOf(productId), salesCount);

            //如果不存在zkey说明当前是今天第一单，设置失效时间30天
            if (!redisTemplate.hasKey(zkey)) {
                redisTemplate.expire(zkey, 30, TimeUnit.DAYS);
            }

        });
    }

    /**
     * @Author zc
     * @Date 2018/5/3 上午10:25
     * @Param [saleOrderId]
     * @Return void
     * @Description:确认收货
     */
    public void confirmReceipt(Long saleOrderId) {
        SaleOrder saleOrder = saleOrderMapper.selectByPrimaryKey(saleOrderId);

        if (ConstEnum.LOGISTICSSTATUS_RECEIVED.getCode().equals(saleOrder.getLogisticsStatus())) {
            throw new IllegalException("已经确认收货，请不要重复确认");
        }

        SaleOrder newSaleOrder = new SaleOrder();
        newSaleOrder.setId(saleOrder.getId());
        newSaleOrder.setLogisticsStatus(ConstEnum.LOGISTICSSTATUS_RECEIVED.getCode());
        newSaleOrder.setCloseTime(new Date());
        //如果已付全款则变更为交易完成
        if (ConstEnum.SALESTATUS_PAID.getCode().equals(newSaleOrder.getOrderStatus())) {
            newSaleOrder.setOrderStatus(ConstEnum.SALESTATUS_SUCCESS.getCode());
            newSaleOrder.setSuccessTime(new Date());
        }

        saleOrderMapper.updateByPrimaryKeySelective(newSaleOrder);
    }


}
