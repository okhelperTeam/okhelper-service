package com.ok.okhelper.util;

import com.ok.okhelper.common.constenum.ConstStr;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

/**
 * Author: zc
 * Date: 2018/4/24
 * Description:单号，条码生成器
 */
@Slf4j
public class NumberGenerator {

    public static String generatorBarCode() {
        return RandomStringUtils.randomNumeric(18);
    }

    /**
     * @Author zc
     * @Date 2018/4/28 下午10:53
     * @Param [orderType, userId]  orderType ST 销售单  IT 入库单  OT 出库单
     * @Return java.lang.String
     * @Description: orderType两位 +yyMMddHHmmss + userId后四位 + random 4位
     */
    public static String generatorOrderNumber(String orderType, Long userId) {
        String str_m = String.valueOf(userId);
        String str = "00000000000000000000";
        str_m = str.substring(0, 20 - str_m.length()) + str_m;
        String userIdString = str_m.substring(16, 20);
        String date = DateFormatUtils.format(new Date(), "yyMMddHHmmss");
        String random = RandomStringUtils.randomNumeric(4);
        return orderType + date + userIdString + random;
    }

    /**
     * @Author zc
     * @Date 2018/5/13 上午12:11
     * @Param [orderId, paymentType]  tradeType 1 首次付款  2 再次付款(还款)
     * @Return java.lang.String    PY 支付流水号
     * @Description: PY + yyMMddHHmmss + orderId后四位 +tradeType 1位 + payType 1位 +random 4位
     */
    public static String generatorPayMentOrderNumber(Long orderId, Integer tradeType,String payType) {
        String str_m = String.valueOf(orderId);
        String str = "00000000000000000000";
        str_m = str.substring(0, 20 - str_m.length()) + str_m;
        String orderIdString = str_m.substring(16, 20);
        String date = DateFormatUtils.format(new Date(), "yyMMddHHmmss");
        String random = RandomStringUtils.randomNumeric(4);
        return ConstStr.ODERTR_NUM_PREFIX_PAYMENT + date + orderIdString + tradeType + payType +random;
    }

}
