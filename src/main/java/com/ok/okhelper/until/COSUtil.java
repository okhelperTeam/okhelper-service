package com.ok.okhelper.until;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by zc on 2017/6/16.
 */

@Component
public class COSUtil {
    private static final Logger logger = LoggerFactory.getLogger(COSUtil.class);

    private COSClient cosClient;

    @Value("${cos.secretId}")
    private String secretId;

    @Value("${cos.secretKey}")
    private String secretKey;

    @Value("${cos.bucketName}")
    private String bucketName;

    @Value("${cos.region}")
    private String region;

    private COSUtil() {
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig(new Region(region));
        // 3 生成cos客户端
        cosClient = new COSClient(cred, clientConfig);
    }


    public static Boolean uploadFile(String cosPath, String localPath) {
        COSUtil cosUtil = new COSUtil();

        PutObjectRequest putObjectRequest = new PutObjectRequest(cosUtil.bucketName, cosPath, localPath);
        PutObjectResult putObjectResult = cosUtil.cosClient.putObject(putObjectRequest);
        String etag = putObjectResult.getETag();  // 获取文件的etag
        return true;
    }

}
