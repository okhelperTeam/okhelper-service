package com.ok.okhelper.util;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.ok.okhelper.exception.IllegalException;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;

import javax.xml.ws.http.HTTPException;
import java.io.IOException;
import java.util.Objects;
import java.util.regex.Pattern;

/*
* @Author zhangxin_an 
* @Date 2018/5/12 16:55
* @Description:
*/  
@Slf4j
public class SMSUtil {

    private static int APPID= Integer.parseInt(Objects.requireNonNull(PropertiesUtil.getProperty("sms.appid")));

    private static String APPKEY=PropertiesUtil.getProperty("sms.appkey");

    public static final String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

    public static void sendSMSCode(String phoneNumber,String captcha) {
        try {

            if(!Pattern.matches(REGEX_MOBILE, phoneNumber)){
                throw new IllegalException("手机号格式不正确");
            }

            SmsSingleSender ssender = new SmsSingleSender(APPID, APPKEY);
            SmsSingleSenderResult result = ssender.send(0, "86", phoneNumber,
                    "尊敬的OK帮用户，你本次的验证码为："+captcha+"，10分钟内有效请尽快完成验证。", "", "");
            System.out.print(result);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
    
    /*
    * @Author zhangxin_an 
    * @Date 2018/5/12 15:17
    * @Params []  
    * @Return java.lang.String  
    * @Description:随机验证码
    */  
    public static String createRandomVcode(){
        //验证码
        String vcode = "";
        for (int i = 0; i < 6; i++) {
            vcode = vcode + (int)(Math.random() * 9);
        }
        return vcode;
    }

}

