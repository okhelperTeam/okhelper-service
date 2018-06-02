package com.ok.okhelper.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ok.okhelper.common.PageModel;
import com.ok.okhelper.dao.CustomerMapper;
import com.ok.okhelper.dao.ProductMapper;
import com.ok.okhelper.dao.SaleOrderDetailMapper;
import com.ok.okhelper.dao.SaleOrderMapper;
import com.ok.okhelper.exception.IllegalException;
import com.ok.okhelper.exception.NotFoundException;
import com.ok.okhelper.common.constenum.ConstEnum;
import com.ok.okhelper.common.constenum.ConstStr;
import com.ok.okhelper.pojo.dto.PaymentDto;
import com.ok.okhelper.pojo.dto.PlaceOrderDto;
import com.ok.okhelper.pojo.dto.PlaceOrderItemDto;
import com.ok.okhelper.pojo.dto.SaleOrderDto;
import com.ok.okhelper.pojo.po.Customer;
import com.ok.okhelper.pojo.po.Product;
import com.ok.okhelper.pojo.po.SaleOrder;
import com.ok.okhelper.pojo.po.SaleOrderDetail;
import com.ok.okhelper.pojo.vo.PlaceOrderVo;
import com.ok.okhelper.pojo.vo.ProductCountMapVo;
import com.ok.okhelper.pojo.vo.SaleOrderVo;
import com.ok.okhelper.pojo.vo.SaleTotalVo;
import com.ok.okhelper.service.OtherService;
import com.ok.okhelper.service.ProductService;
import com.ok.okhelper.service.SaleService;
import com.ok.okhelper.shiro.JWTUtil;
import com.ok.okhelper.util.AliPayUtil;
import com.ok.okhelper.util.NumberGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.util.ThreadContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
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

    @Autowired
    private ProductService productService;

    @Autowired
    private OtherService otherService;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private AliPayUtil aliPayUtil;

    /**
     * 库存不足
     */
    public static final int LOW_STOCK = 0;


    /**
     * @Author zc
     * @Date 2018/4/29 上午11:01
     * @Param [storeId, saleOrderDto, pageNum, limit]
     * @Return com.ok.okhelper.common.PageModel<com.ok.okhelper.pojo.po.SaleOrder>
     * @Description:获取指定时间内的历史订单 (包含已关闭订单)
     */
    @Override
    public PageModel<SaleOrderVo> getSaleOrderRecords(SaleOrderDto saleOrderDto, PageModel pageModel) {
        if(saleOrderDto.getStartDate().compareTo(saleOrderDto.getEndDate())>0){
            throw new IllegalException("时间参数错误");
        }
        //启动分页
        PageHelper.startPage(pageModel.getPageNum(), pageModel.getLimit());

        if("create_time desc".equals(pageModel.getOrderBy())){
            pageModel.setOrderBy("sale_order.create_time desc");
        }

        //启动排序
        PageHelper.orderBy(pageModel.getOrderBy());

        List<SaleOrderVo> saleOrderVos = saleOrderMapper.getSaleOrderVo(JWTUtil.getStoreId(), saleOrderDto);


        if(CollectionUtils.isNotEmpty(saleOrderVos)){
            saleOrderVos.forEach(x->{
                x.setProductCount(x.getSaleOrderItemVos()!=null?x.getSaleOrderItemVos().size():0);
            });
        }

        PageInfo<SaleOrderVo> pageInfo = new PageInfo<>(saleOrderVos);
        PageModel<SaleOrderVo> dbPageModel = PageModel.convertToPageModel(pageInfo);

        //加入销售聚合汇总
        SaleTotalVo saleTotalVo =saleOrderMapper.getSaleTotal(JWTUtil.getStoreId(), saleOrderDto.getStartDate(), saleOrderDto.getEndDate());
        List<ProductCountMapVo> saleTotalProductCounts = saleOrderMapper.getSaleTotalProductCount(JWTUtil.getStoreId(), saleOrderDto.getStartDate(), saleOrderDto.getEndDate());
        if(CollectionUtils.isNotEmpty(saleTotalProductCounts)){
            saleTotalVo.setProductCountMap(saleTotalProductCounts);
            saleTotalVo.setTotalProductCount(saleTotalProductCounts.size());
            int sum = saleTotalProductCounts.stream().mapToInt(ProductCountMapVo::getSalesProductNum).sum();
            saleTotalVo.setTotalSalesProductNumber(sum);
        }
        dbPageModel.setTotalData(saleTotalVo);
        dbPageModel.setOrderBy(pageModel.getOrderBy());

        return dbPageModel;
    }

    /**
     * @Author zc  
     * @Date 2018/5/8 下午8:35  
     * @Param [id]  
     * @Return com.ok.okhelper.pojo.vo.SaleOrderVo  
     * @Description:获取指定订单
     */
    public SaleOrderVo getSaleOrderRecordOne(Long id){
        SaleOrderDto saleOrderDto=new SaleOrderDto();
        saleOrderDto.setId(id);
        List<SaleOrderVo> saleOrderVos = saleOrderMapper.getSaleOrderVo(JWTUtil.getStoreId(), saleOrderDto);

        if (CollectionUtils.isEmpty(saleOrderVos)||saleOrderVos.get(0)==null) {
            throw new NotFoundException("资源不存在");
        }

        SaleOrderVo saleOrderVo=saleOrderVos.get(0);

        if (ObjectUtils.notEqual(saleOrderVo.getStoreId(), JWTUtil.getStoreId())) {
            throw new AuthorizationException("资源不在你当前商铺查看范围");
        }

        return saleOrderVo;
    }

    /**
     * @Author zc
     * @Date 2018/4/29 上午11:00
     * @Param [storeId, seller, placeOrderDto]
     * @Return java.lang.String  返回订单vo
     * @Description:下单
     */
    @Override
    @Transactional
    public PlaceOrderVo placeOrder(Long storeId, Long seller, PlaceOrderDto placeOrderDto) {
        List<PlaceOrderItemDto> placeOrderItemDtos = placeOrderDto.getPlaceOrderItemDtos();

        //加入销售订单
        SaleOrder saleOrder = new SaleOrder();
//      saleOrder.setToBePaid(toBePaid);

        BeanUtils.copyProperties(placeOrderDto, saleOrder);
        saleOrder.setOrderNumber(NumberGenerator.generatorOrderNumber(ConstStr.ODERTR_NUM_PREFIX_SALE, seller));
        saleOrder.setOrderStatus(ConstEnum.SALESTATUS_NOPAYMENT.getCode());
        saleOrder.setStoreId(storeId);
        saleOrder.setSeller(seller);
        saleOrder.setLogisticsStatus(ConstEnum.LOGISTICSSTATUS_NOSEND.getCode());

        saleOrderMapper.insertSelective(saleOrder);


        if (CollectionUtils.isNotEmpty(placeOrderItemDtos)) {
            //插入订单子项
            assembleSaleOrderDetail(placeOrderItemDtos, saleOrder.getId());
            //减商品库存
            otherService.checkAndCutStock(placeOrderItemDtos);
        }else {
            throw new IllegalException("请添加商品");
        }


        //返回vo
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
            if(!ObjectUtils.allNotNull(placeOrderItemDto.getProductId(),placeOrderItemDto.getSaleCount(),placeOrderItemDto.getSalePrice())){
                throw new IllegalException("订单子项参数错误");
            }
            SaleOrderDetail saleOrderDetail = new SaleOrderDetail();
            Product product = productService.getProduct(placeOrderItemDto.getProductId());
            saleOrderDetail.setProductId(product.getId());
            saleOrderDetail.setMainImg(product.getMainImg());
            saleOrderDetail.setProductName(product.getProductName());
            saleOrderDetail.setProductTitle(product.getProductTitle());
            saleOrderDetail.setSaleOrderId(saleOrderId);
            saleOrderDetail.setSaleCount(placeOrderItemDto.getSaleCount());
            saleOrderDetail.setSalePrice(placeOrderItemDto.getSalePrice());
            placeOrderItemDto.setProductName(product.getProductName());
            try{
                int i = saleOrderDetailMapper.insertSelective(saleOrderDetail);
                if(i<=0){
                    throw new IllegalException("下单失败:订单子项插入失败");
                }
            }catch (Exception e){
                throw new IllegalException("下单失败:订单子项插入失败");
            }


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
     * @Date 2018/5/5 下午10:44
     * @Param [customerId, sumPrice]
     * @Return void
     * @Description:记录客户积分
     */
    @Async
    public void recordCustomerScore(Long customerId, BigDecimal sumPrice) {
        Customer dbcustomer = customerMapper.selectByPrimaryKey(customerId);

        if (dbcustomer == null) {
            throw new NotFoundException("资源不存在");
        }
        if (ObjectUtils.notEqual(dbcustomer.getStoreId(), JWTUtil.getStoreId())) {
            throw new AuthorizationException("资源不在你当前商铺查看范围");
        }
        if (ConstEnum.STATUSENUM_UNAVAILABLE.getCode() == dbcustomer.getDeleteStatus()) {
            throw new IllegalException("当前资源不可用");
        }

        Integer oldCustomerScore = dbcustomer.getCustomerScore();
        Integer newCustomer = oldCustomerScore + sumPrice.intValue();
        Customer customer = new Customer();
        customer.setCustomerScore(newCustomer);
        customer.setId(customerId);

        int i = customerMapper.updateByPrimaryKeySelective(customer);
        if (i <= 0) {
            throw new IllegalException("更新客户积分失败");
        }
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

        if (saleOrder == null) {
            throw new NotFoundException("订单不存在");
        }
        if (ObjectUtils.notEqual(saleOrder.getStoreId(), JWTUtil.getStoreId())) {
            throw new AuthorizationException("资源不在你当前商铺查看范围");
        }
        if (ConstEnum.SALESTATUS_CLOSE.getCode() == saleOrder.getOrderStatus()) {
            throw new IllegalException("订单已关闭");
        }
        if (ConstEnum.LOGISTICSSTATUS_RECEIVED.getCode() == saleOrder.getLogisticsStatus()) {
            throw new IllegalException("已经确认收货，请不要重复确认");
        }

        SaleOrder newSaleOrder = new SaleOrder();
        newSaleOrder.setId(saleOrder.getId());
        newSaleOrder.setLogisticsStatus(ConstEnum.LOGISTICSSTATUS_RECEIVED.getCode());
        newSaleOrder.setCloseTime(new Date());
        //如果已付全款则变更为交易完成
        if (ConstEnum.SALESTATUS_PAID.getCode() == newSaleOrder.getOrderStatus()) {
            newSaleOrder.setOrderStatus(ConstEnum.SALESTATUS_SUCCESS.getCode());
            newSaleOrder.setSuccessTime(new Date());
        }

        saleOrderMapper.updateByPrimaryKeySelective(newSaleOrder);
    }

    /**
     * @Author zc
     * @Date 2018/5/5 下午2:28
     * @Param [saleOrderId]
     * @Return void
     * @Description:关闭订单
     */
    @Transactional
    public void closeOrder(Long saleOrderId,boolean isTask) {
        SaleOrder saleOrder = saleOrderMapper.selectByPrimaryKey(saleOrderId);
        if (saleOrder == null) {
            throw new NotFoundException("订单不存在");
        }
        if (!isTask&&ObjectUtils.notEqual(saleOrder.getStoreId(), JWTUtil.getStoreId())) {
            throw new AuthorizationException("资源不在你当前商铺查看范围");
        }
        ThreadContext.unbindSubject();
        if (ConstEnum.SALESTATUS_CLOSE.getCode() == saleOrder.getOrderStatus()) {
            throw new IllegalException("订单已关闭");
        }

        saleOrder.setOrderStatus(ConstEnum.SALESTATUS_CLOSE.getCode());
        saleOrder.setCloseTime(new Date());
        int i = saleOrderMapper.updateByPrimaryKeySelective(saleOrder);

        if (i <= 0) {
            throw new IllegalException("订单关闭失败");
        }

        //把库存加回来
        SaleOrderDetail saleOrderDetail = new SaleOrderDetail();
        saleOrderDetail.setSaleOrderId(saleOrderId);
        List<SaleOrderDetail> saleOrderDetails = saleOrderDetailMapper.select(saleOrderDetail);
        if (CollectionUtils.isNotEmpty(saleOrderDetails)) {
            saleOrderDetails.forEach(dbsaleOrderDetail ->
                    productMapper.addSalesStock(dbsaleOrderDetail.getSaleCount(), dbsaleOrderDetail.getProductId()));
        }

        //积分扣回
        if (saleOrder.getCustomerId() != null) {
            Customer dbcustomer = customerMapper.selectByPrimaryKey(saleOrder.getCustomerId());
            if (dbcustomer != null) {
                Customer customer = new Customer();
                customer.setId(dbcustomer.getId());
                customer.setCustomerScore(dbcustomer.getCustomerScore() - saleOrder.getSumPrice().intValue());
                customerMapper.updateByPrimaryKeySelective(customer);
            }
        }

    }


    /**
     * @Author zc
     * @Date 2018/5/5 下午5:24
     * @Param [saleOrderId, paymentDto]
     * @Return void
     * @Description:支付
     */
    public void payment(Long saleOrderId, PaymentDto paymentDto) {
        SaleOrder saleOrder = saleOrderMapper.selectByPrimaryKey(saleOrderId);
        if (saleOrder == null) {
            throw new NotFoundException("订单不存在");
        }
        if (ObjectUtils.notEqual(saleOrder.getStoreId(), JWTUtil.getStoreId())) {
            throw new AuthorizationException("资源不在你当前商铺查看范围");
        }
        if (ConstEnum.SALESTATUS_CLOSE.getCode() == saleOrder.getOrderStatus()) {
            throw new IllegalException("订单已关闭");
        }
        if (ConstEnum.SALESTATUS_SUCCESS.getCode() == saleOrder.getOrderStatus()) {
            throw new IllegalException("交易已完成");
        }
        if (ConstEnum.SALESTATUS_PAID.getCode() == saleOrder.getOrderStatus()) {
            throw new IllegalException("订单已支付全款，不要再支付了");
        }
        if (saleOrder.getSumPrice().subtract(saleOrder.getRealPay()).subtract(paymentDto.getRealPay()).doubleValue()<0.0) {
            throw new IllegalException("实付金额不能大于应付金额");
        }

        //生成支付流水号(我们自己的)
        String payNumber
                = NumberGenerator.generatorPayMentOrderNumber(saleOrderId, paymentDto.getTradeType(), paymentDto.getPayType());

        String aliPayTradeNumber=null;

        //支付宝扣款
        if(String.valueOf(ConstEnum.PAYTYPE_ALIPAY.getCode()).equals(paymentDto.getPayType())){
            if(StringUtils.isBlank(paymentDto.getAliPayAuthCode())){
                throw new IllegalException("支付宝付款码不能为空");
            }
            if(ConstEnum.TRADETYPE_FIRST.getCode()==paymentDto.getTradeType()){
                aliPayTradeNumber=aliPayUtil.alipay(payNumber,paymentDto.getAliPayAuthCode(),paymentDto.getRealPay().toString(),"0.00","OK帮下单支付-"+saleOrder.getOrderNumber());
            }else if(ConstEnum.TRADETYPE_REPAYMENT.getCode()==paymentDto.getTradeType()){
                aliPayTradeNumber=aliPayUtil.alipay(payNumber,paymentDto.getAliPayAuthCode(),paymentDto.getRealPay().toString(),"0.00","OK帮订单还款-"+saleOrder.getOrderNumber());
            }
        }


        //更新订单信息

        //(历史已经付款金额+这次实付金额)
        BigDecimal realPay = saleOrder.getRealPay().add(paymentDto.getRealPay());
        //欠款金额=应付金额-(历史已经付款金额+这次实付金额)
        BigDecimal toBePaid
                = saleOrder.getSumPrice().subtract(realPay);

        saleOrder.setRealPay(realPay);
        saleOrder.setToBePaid(toBePaid);
        saleOrder.setPayTime(new Date());

        //判断物流状态决定是否完成订单
        if (toBePaid.doubleValue() > 0.0) {
            saleOrder.setOrderStatus(ConstEnum.SALESTATUS_DEBT.getCode());
        } else {
            saleOrder.setOrderStatus(ConstEnum.SALESTATUS_PAID.getCode());
            if (ConstEnum.LOGISTICSSTATUS_RECEIVED.getCode() == saleOrder.getLogisticsStatus()) {
                saleOrder.setOrderStatus(ConstEnum.SALESTATUS_SUCCESS.getCode());
            }
        }

        //统计支付方式,金额
        try {
            ObjectMapper objectMapper=new ObjectMapper();
            Map<String,String> map = objectMapper.readValue(saleOrder.getPayType(), Map.class);
            String dbpayMentTypePrice = map.get(paymentDto.getPayType());
            if(dbpayMentTypePrice==null){
                throw new IllegalException("payType参数错误");
            }
            //数据库中该支付方式金额
            BigDecimal dbpayMentTypePriceDecimal=new BigDecimal(dbpayMentTypePrice);
            BigDecimal newpayMentTypePriceDecimal = dbpayMentTypePriceDecimal.add(paymentDto.getRealPay());
            map.put(paymentDto.getPayType(),newpayMentTypePriceDecimal.toString());
            String newPayType = objectMapper.writeValueAsString(map);
            saleOrder.setPayType(newPayType);
        } catch (IOException e) {
           log.error("异常：{}",e.getMessage());
           //支付宝退款
           aliPayUtil.refund(saleOrderId.toString(),aliPayTradeNumber,realPay.toString());
           throw new IllegalException("系统异常，支付失败");
        }


        //更新数据库
        int i = saleOrderMapper.updateByPrimaryKeySelective(saleOrder);
        if (i <= 0) {
            //支付宝退款
            aliPayUtil.refund(saleOrderId.toString(),aliPayTradeNumber,realPay.toString());
            throw new IllegalException("支付失败");
        }



    }

}
