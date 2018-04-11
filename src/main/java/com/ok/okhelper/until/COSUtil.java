package com.ok.okhelper.until;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

/**
 * Created by zc on 2018/4/10.
 */

@Slf4j
public class COSUtil {

    private COSClient cosClient;

    private String secretId = PropertiesUtil.getProperty("cos.secretId");

    private String secretKey = PropertiesUtil.getProperty("cos.secretKey");

    private String bucketName = PropertiesUtil.getProperty("cos.bucketName");

    private String region = PropertiesUtil.getProperty("cos.region");

    private COSUtil() {
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig(new Region(region));
        // 3 生成cos客户端
        cosClient = new COSClient(cred, clientConfig);
    }


    public static Boolean uploadFile(String cosPath, File localfile) {
        COSUtil cosUtil = new COSUtil();

        try {
            PutObjectRequest putObjectRequest = new PutObjectRequest(cosUtil.bucketName, cosPath, localfile);
            PutObjectResult putObjectResult = cosUtil.cosClient.putObject(putObjectRequest);

            String etag = putObjectResult.getETag();  // 获取文件的etag
            log.info(etag);
        } catch (RuntimeException exception) {
            log.error(exception.getMessage());
            return false;
        }


        return true;
    }

}
