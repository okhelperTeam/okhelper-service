package com.ok.okhelper.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ok.okhelper.common.PageModel;
import com.ok.okhelper.dao.SaleOrderMapper;
import com.ok.okhelper.exception.IllegalException;
import com.ok.okhelper.pojo.bo.CustomerDebtBo;
import com.ok.okhelper.pojo.bo.CustomerDebtTotalBo;
import com.ok.okhelper.common.constenum.ConstStr;
import com.ok.okhelper.pojo.po.Product;
import com.ok.okhelper.pojo.vo.SaleTotalVo;
import com.ok.okhelper.pojo.vo.SalesVolumeVo;
import com.ok.okhelper.service.ProductService;
import com.ok.okhelper.service.ReportService;
import com.ok.okhelper.shiro.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Author: zc
 * Date: 2018/5/6
 * Description:
 */
@Service
@Slf4j
public class ReportServiceImpl implements ReportService {

    @Autowired
    private SaleOrderMapper saleOrderMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ProductService productService;

    /**
     * @Author zc
     * @Date 2018/5/6 下午2:53
     * @Param [condition, pageModel]
     * @Return com.ok.okhelper.common.PageModel<com.ok.okhelper.pojo.bo.CustomerDebtBo>
     * @Description:客户对账查询
     */
    @Override
    public PageModel<CustomerDebtBo> getCustomerDebt(String condition, PageModel pageModel) {
        //启动分页
        PageHelper.startPage(pageModel.getPageNum(), pageModel.getLimit());

        if("create_time desc".equals(pageModel.getOrderBy())){
            pageModel.setOrderBy("sale_order.create_time desc");
        }

        //启动排序
        PageHelper.orderBy(pageModel.getOrderBy());

        List<CustomerDebtBo> customerDebtBos = saleOrderMapper.getCustomerDebtBo(JWTUtil.getStoreId(), condition);

        PageInfo<CustomerDebtBo> pageInfo = new PageInfo<>(customerDebtBos);

        PageModel<CustomerDebtBo> dbPageModel = PageModel.convertToPageModel(pageInfo);

//        List<CustomerDebtGroupBo> customerDebtGroupBos = saleOrderMapper.getCustomerDebtGroupBo(JWTUtil.getStoreId(), condition);

        List<CustomerDebtBo> allCustomerDebtBo = saleOrderMapper.getCustomerDebtBo(JWTUtil.getStoreId(), condition);


        if (CollectionUtils.isNotEmpty(allCustomerDebtBo)) {
            CustomerDebtTotalBo customerDebtTotalBo = new CustomerDebtTotalBo();
            Map<Long, List<CustomerDebtBo>> customerDebtMap = allCustomerDebtBo.stream()
                    .filter(x -> x.getToBePaid() != null && x.getToBePaid().doubleValue() > 0.0)
                    .collect(Collectors.groupingBy(CustomerDebtBo::getCustomerId));

            BigDecimal totalMoney = allCustomerDebtBo.stream()
                    .filter(x -> x.getToBePaid() != null && x.getToBePaid().doubleValue() > 0.0)
                    .map(CustomerDebtBo::getToBePaid)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            customerDebtTotalBo.setTotalToBePaid(totalMoney);

            customerDebtTotalBo.setCustomerCount(customerDebtMap.size());

            customerDebtTotalBo.setTotalToBePaid(totalMoney);

            dbPageModel.setTotalData(customerDebtTotalBo);
            dbPageModel.setOrderBy(pageModel.getOrderBy());
        }

        return dbPageModel;
    }

    /**
     * @Author zc
     * @Date 2018/5/6 下午1:50
     * @Param [range]
     * @Return java.util.List<com.ok.okhelper.pojo.vo.SalesVolumeVo>
     * @Description:获取热销商品排行
     */
    @Override
    public List<SalesVolumeVo> getHotSaleByRedis(String range) {
        Set<ZSetOperations.TypedTuple<String>> projectIdTop10 = null;
        String zkey_today = ConstStr.HOT_SALE + ":" + JWTUtil.getStoreId() + ":" + DateFormatUtils.format(new Date(), "yyyyMMdd");

        Set<String> keySet = new HashSet<>();
        switch (range) {
            case ConstStr.QUERY_RANGE_TODAY:
                projectIdTop10 = redisTemplate.opsForZSet().reverseRangeWithScores(zkey_today, 0, 9);
                break;
            case ConstStr.QUERY_RANGE_THREEDAYS:
                String zkey_three_days = ConstStr.HOT_SALE + ":" + JWTUtil.getStoreId() + ":" + "last_threeDays";
                keySet.clear();
                // 生成前两天的keys
                for (int i = 1; i <= 2; i++) {
                    String oneKey =
                            ConstStr.HOT_SALE + ":" + JWTUtil.getStoreId() + ":" + DateFormatUtils.format(DateUtils.addDays(new Date(), -i), "yyyyMMdd");
                    keySet.add(oneKey);
                }
                redisTemplate.opsForZSet().unionAndStore(zkey_today, keySet, zkey_three_days);
                projectIdTop10 = redisTemplate.opsForZSet().reverseRangeWithScores(zkey_three_days, 0, 9);
                break;
            case ConstStr.QUERY_RANGE_WEEK:
                String zkey_week = ConstStr.HOT_SALE + ":" + JWTUtil.getStoreId() + ":" + "last_week";
                keySet.clear();
                // 生成前6天
                for (int i = 1; i <= 6; i++) {
                    String oneKey =
                            ConstStr.HOT_SALE + ":" + JWTUtil.getStoreId() + ":" + DateFormatUtils.format(DateUtils.addDays(new Date(), -i), "yyyyMMdd");
                    keySet.add(oneKey);
                }
                redisTemplate.opsForZSet().unionAndStore(zkey_today, keySet, zkey_week);
                projectIdTop10 = redisTemplate.opsForZSet().reverseRangeWithScores(zkey_week, 0, 9);
                break;
            case ConstStr.QUERY_RANGE_MONTH:
                String zkey_month = ConstStr.HOT_SALE + ":" + JWTUtil.getStoreId() + ":" + "last_month";
                keySet.clear();

                for (int i = 1; i <= 29; i++) {
                    String oneKey =
                            ConstStr.HOT_SALE + ":" + JWTUtil.getStoreId() + ":" + DateFormatUtils.format(DateUtils.addDays(new Date(), -i), "yyyyMMdd");
                    keySet.add(oneKey);
                }
                redisTemplate.opsForZSet().unionAndStore(zkey_today, keySet, zkey_month);
                projectIdTop10 = redisTemplate.opsForZSet().reverseRangeWithScores(zkey_month, 0, 9);
                break;
            default:
                throw new IllegalException("range参数错误");
        }

        List<SalesVolumeVo> salesVolumeVos = projectIdTop10.stream().map(p -> {
            Product product;
            try {
                product = productService.getProduct(Long.valueOf(p.getValue()));
                SalesVolumeVo salesVolumeVo = new SalesVolumeVo();
                BeanUtils.copyProperties(product, salesVolumeVo);
                salesVolumeVo.setSalesVolume(p.getScore().intValue());
                return salesVolumeVo;
            } catch (IllegalException e) {
                log.debug(e.getMessage());
                return null;
            }
        }).collect(Collectors.toList());

        salesVolumeVos.removeIf(Objects::isNull);

        return salesVolumeVos;
    }

    /**
     * @Author zc
     * @Date 2018/5/6 下午1:50
     * @Param [range]
     * @Return java.util.List<com.ok.okhelper.pojo.vo.SalesVolumeVo>
     * @Description:获取滞销商品排行
     */
    @Override
    public List<SalesVolumeVo> getUnsalableByRedis(String range) {
        Set<ZSetOperations.TypedTuple<String>> projectIdTop10 = null;
        String zkey_today = ConstStr.HOT_SALE + ":" + JWTUtil.getStoreId() + ":" + DateFormatUtils.format(new Date(), "yyyyMMdd");

        Set<String> keySet = new HashSet<>();
        switch (range) {
            case ConstStr.QUERY_RANGE_TODAY:
                projectIdTop10 = redisTemplate.opsForZSet().rangeWithScores(zkey_today, 0, 9);
                break;
            case ConstStr.QUERY_RANGE_THREEDAYS:
                String zkey_three_days = ConstStr.HOT_SALE + ":" + JWTUtil.getStoreId() + ":" + "last_threeDays";
                keySet.clear();
                // 生成前两天的keys
                for (int i = 1; i <= 2; i++) {
                    String oneKey =
                            ConstStr.HOT_SALE + ":" + JWTUtil.getStoreId() + ":" + DateFormatUtils.format(DateUtils.addDays(new Date(), -i), "yyyyMMdd");
                    keySet.add(oneKey);
                }
                redisTemplate.opsForZSet().unionAndStore(zkey_today, keySet, zkey_three_days);
                projectIdTop10 = redisTemplate.opsForZSet().rangeWithScores(zkey_three_days, 0, 9);
                break;
            case ConstStr.QUERY_RANGE_WEEK:
                String zkey_week = ConstStr.HOT_SALE + ":" + JWTUtil.getStoreId() + ":" + "last_week";
                keySet.clear();
                // 生成前6天
                for (int i = 1; i <= 6; i++) {
                    String oneKey =
                            ConstStr.HOT_SALE + ":" + JWTUtil.getStoreId() + ":" + DateFormatUtils.format(DateUtils.addDays(new Date(), -i), "yyyyMMdd");
                    keySet.add(oneKey);
                }
                redisTemplate.opsForZSet().unionAndStore(zkey_today, keySet, zkey_week);
                projectIdTop10 = redisTemplate.opsForZSet().rangeWithScores(zkey_week, 0, 9);
                break;
            case ConstStr.QUERY_RANGE_MONTH:
                String zkey_month = ConstStr.HOT_SALE + ":" + JWTUtil.getStoreId() + ":" + "last_month";
                keySet.clear();

                for (int i = 1; i <= 29; i++) {
                    String oneKey =
                            ConstStr.HOT_SALE + ":" + JWTUtil.getStoreId() + ":" + DateFormatUtils.format(DateUtils.addDays(new Date(), -i), "yyyyMMdd");
                    keySet.add(oneKey);
                }
                redisTemplate.opsForZSet().unionAndStore(zkey_today, keySet, zkey_month);
                projectIdTop10 = redisTemplate.opsForZSet().rangeWithScores(zkey_month, 0, 9);
                break;
            default:
                throw new IllegalException("range参数错误");
        }

        List<SalesVolumeVo> salesVolumeVos = projectIdTop10.stream().map(p -> {
            Product product;
            try {
                product = productService.getProduct(Long.valueOf(p.getValue()));
                SalesVolumeVo salesVolumeVo = new SalesVolumeVo();
                BeanUtils.copyProperties(product, salesVolumeVo);
                salesVolumeVo.setSalesVolume(p.getScore().intValue());
                return salesVolumeVo;
            } catch (IllegalException e) {
                log.debug(e.getMessage());
                return null;
            }
        }).collect(Collectors.toList());

        salesVolumeVos.removeIf(Objects::isNull);
        return salesVolumeVos;
    }

    /**
     * @Author zc
     * @Date 2018/4/29 上午11:00
     * @Param [storeId, startDate, endDate]
     * @Return com.ok.okhelper.pojo.vo.SaleTotalVo
     * @Description:获取指定时间范围的销售聚合 (去除 已关闭订单)
     */
    @Override
    public SaleTotalVo getSaleTotalVo(Date startDate, Date endDate) {
        if (startDate.compareTo(endDate) > 0) {
            throw new IllegalException("时间参数错误");
        }
        return saleOrderMapper.getSaleTotal(JWTUtil.getStoreId(), startDate, endDate);
    }
}
