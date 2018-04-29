package com.ok.okhelper.until;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;
import java.util.UUID;

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
     * @Param [userId, payType]  
     * @Return java.lang.String  
     * @Description:yyMMddHHmmss + userId后四位 + random 4位
     */  
    public static String generatorPlaceOrderNumber(Long userId){
        String str_m = String.valueOf(userId);
        String str ="00000000000000000000";
        str_m=str.substring(0, 20-str_m.length())+str_m;
        String userIdString = str_m.substring(16, 20);
        String date = DateFormatUtils.format(new Date(), "yyMMddHHmmss");
        String random = RandomStringUtils.randomNumeric(4);
        return date+userIdString+random;
    }
}
