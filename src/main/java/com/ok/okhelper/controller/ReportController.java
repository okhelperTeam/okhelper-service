package com.ok.okhelper.controller;

import com.ok.okhelper.common.ServerResponse;
import com.ok.okhelper.exception.IllegalException;
import com.ok.okhelper.pojo.constenum.ConstStr;
import com.ok.okhelper.pojo.vo.HotSaleVo;
import com.ok.okhelper.pojo.po.Product;
import com.ok.okhelper.service.ProductService;
import com.ok.okhelper.shiro.JWTUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Author: zc
 * Date: 2018/4/30
 * Description:
 */
@RestController
@Api(tags = "统计报表模块")
@Slf4j
public class ReportController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ProductService productService;


    @ApiOperation(value = "热销商品", notes = "获取销量前十的商品")
    @GetMapping("/report/hot_sale")
    public ServerResponse<List<HotSaleVo>> getHotProduct(
            @ApiParam(value = "查询范围(今天->today 三天内->threeDays 一周内->week 一个月->month)") @RequestParam(required = true) String range) {
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

        List<HotSaleVo> hotSaleVos = projectIdTop10.stream().map(p -> {
            Product product;
            try {
                product = productService.getProduct(Long.valueOf(p.getValue()));
                HotSaleVo hotSaleVo = new HotSaleVo();
                BeanUtils.copyProperties(product, hotSaleVo);
                hotSaleVo.setSalesVolume(p.getScore().intValue());
                return hotSaleVo;
            } catch (IllegalException e) {
                log.debug(e.getMessage());
                return null;
            }
        }).collect(Collectors.toList());

        hotSaleVos.removeIf(Objects::isNull);

        return ServerResponse.createBySuccess(hotSaleVos);
    }


}
