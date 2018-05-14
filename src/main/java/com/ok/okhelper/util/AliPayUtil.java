package com.ok.okhelper.util;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradePayResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.ok.okhelper.exception.IllegalException;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Author: zc
 * Date: 2018/5/12
 * Description:
 */
@Component
@Slf4j
public class AliPayUtil {

    @Value("${sdk.alipay.app-id}")
    private String APP_ID;

    @Value("${sdk.alipay.private-key}")
    private String APP_PRIVATE_KEY;

    private String CHARSET = "UTF-8";

    @Value("${sdk.alipay.alipay-public-key}")
    private String ALIPAY_PUBLIC_KEY;

    /**
     * 参数     名称	    参数说明
     * out_trade_no	    商户订单号，需要保证不重复
     * scene	        条码支付固定传入bar_code
     * auth_code	    用户付款码，25~30开头的长度为16~24位的数字，实际字符串长度以开发者获取的付款码长度为准
     * subject	        订单标题
     * store_id	        商户门店编号
     * total_amount	    订单金额
     * timeout_express	交易超时时间
     */
    public String alipay(String pyOrderNum, String auth_code, String total_amount, String discountable_amount, String subject) {
        // 支付宝当面付2.0服务
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do", APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, "RSA2"); //获得初始化的AlipayClient
        AlipayTradePayRequest request = new AlipayTradePayRequest(); //创建API对应的request类

        JSONObject data = new JSONObject();
        data.put("out_trade_no", pyOrderNum); //商户订单号
        data.put("scene", "bar_code");
        data.put("auth_code", auth_code);
        data.put("subject", subject);
        data.put("store_id", "OK帮");
        data.put("total_amount", total_amount);
//        data.put("discount_amount",discountable_amount);

        request.setBizContent(data.toString()); //设置业务参数
        AlipayTradePayResponse response = null; //通过alipayClient调用API，获得对应的response类
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            log.error("支付宝错误码：{}，错误信息：{}", e.getErrCode(), e.getErrMsg());
            throw new IllegalException("支付宝支付失败,请重新支付");
        }

        log.info("支付宝支付响应信息：{}", response.getCode());

        if (!"10000".equals(response.getCode())) {
            throw new IllegalException("支付宝支付失败,请重新支付");
        } else {
            return response.getTradeNo();
        }

    }


    //退款
    public void refund(String pyOrderNum,String aliPayTradeNumber,String refundAmount) {
        AlipayClient alipayClient = new DefaultAlipayClient
                ("https://openapi.alipaydev.com/gateway.do", APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, "RSA2"); //获得初始化的AlipayClient
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest(); //创建退款API对应的request类

        JSONObject data = new JSONObject();
        data.put("out_trade_no", pyOrderNum);
        data.put("trade_no", aliPayTradeNumber);
        data.put("refund_amount", refundAmount);

        request.setBizContent(data.toString()); //设置业务参数

        AlipayTradeRefundResponse response = null;//通过alipayClient调用API，获得对应的response类
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
            log.error("支付宝退款失败"+e.getErrCode());
        }
    }

}
