package com.ok.okhelper.util;

import com.alipay.api.AlipayApiException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

/**
 * Created by zc on 2018/4/10.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = COSUtil.class)
public class COSUtilTest {



    @Test
    public void tsetUpload() {
        COSUtil.uploadFile("/2.png", new File("/Users/zc/Desktop/rbac.png"));
    }

    @Test
    public void testDelete() {
        COSUtil.deleteFile("https://okhelper-1252411697.cosbj.myqcloud.com/store_logo/b9c98cc2-61b1-444a-bec8-a7ae63859c31.png");
    }

    @Test
    public void testFulshB(){
//        COSUtil.flushBucket();
    }

}
