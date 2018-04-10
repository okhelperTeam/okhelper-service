package com.ok.okhelper.until;

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
        COSUtil.uploadFile("/1.png", new File("/Users/zc/Desktop/rbac.png"));
    }
}
