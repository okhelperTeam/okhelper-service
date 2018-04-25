package com.ok.okhelper.until;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

/**
 * Author: zc
 * Date: 2018/4/24
 * Description:
 */
@Slf4j
public class NumberGenerator {

    public static String generatorBarCode() {
        String s = RandomStringUtils.randomNumeric(18);
        return s;
    }

    public static void main(String[] args) {
        String s = generatorBarCode();
        log.debug(s);
    }
}
