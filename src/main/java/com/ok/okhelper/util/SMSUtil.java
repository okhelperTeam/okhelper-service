package com.ok.okhelper.util;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;

import javax.xml.ws.http.HTTPException;
import java.io.IOException;

/**
 * Author: zc
 * Date: 2018/5/11
 * Description:
 */
@Slf4j
public class SMSUtil {

    public static void sendSMSCaptcha() {
        try {
            int appid = 1400088948;
            String appkey = "84d1bfc6b5afa6538031f08931f913ae";
            String phoneNumber = "13296390792";
            String captcha = "123";
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult result = ssender.send(0, "86", phoneNumber,
                    "【OK帮】尊敬的用户，你本次的验证码为："+captcha+"，10分钟内有效请尽快完成验证。", "", "");
            System.out.print(result);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }


    public static void main(String[] args) {
        sendSMSCaptcha();
    }
}

