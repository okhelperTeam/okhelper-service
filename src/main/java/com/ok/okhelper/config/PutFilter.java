package com.ok.okhelper.config;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.HttpPutFormContentFilter;

/*
*Author:zhangxin_an
*Description:解决PUt请求接受不到参数
*Data:Created in 11:31 2018/4/24
*/
@Component
public class PutFilter extends HttpPutFormContentFilter {
}