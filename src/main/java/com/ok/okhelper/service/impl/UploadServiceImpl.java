package com.ok.okhelper.service.impl;

import com.ok.okhelper.exception.IllegalException;
import com.ok.okhelper.service.UploadService;
import com.ok.okhelper.util.COSUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Author: zc
 * Date: 2018/4/25
 * Description:
 */
@Service
@Slf4j
public class UploadServiceImpl implements UploadService {

    /**
     * @Author zc
     * @Date 2018/4/25 下午11:56
     * @Param [file, tmp_path, cosPathPrefix]
     * @Return java.lang.String
     * @Description:上传文件并且返回文件名
     */
    public String upload(MultipartFile file, String tmp_path, String cosPathPrefix) {
        String fileName = file.getOriginalFilename();
        //扩展名
        //abc.jpg
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
        String uploadFileName = UUID.randomUUID().toString() + "." + fileExtensionName;
        log.info("开始上传文件,上传文件的文件名:{},上传的路径:{},新文件名:{}", fileName, tmp_path, uploadFileName);

        File fileDir = new File(tmp_path);
        if (!fileDir.exists()) {
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile = new File(tmp_path, uploadFileName);


        try {
            file.transferTo(targetFile);
            //文件已经上传成功了

            Boolean aBoolean = COSUtil.uploadFile(cosPathPrefix + uploadFileName, targetFile);
            if (!aBoolean) {
                throw new IllegalException("COS上传失败");
            }
            //已经上传到Cos

            targetFile.delete();
        } catch (IOException e) {
            log.error("上传文件异常", e);
            return null;
        } catch (IllegalException e) {
            e.printStackTrace();
        }

        return targetFile.getName();
    }
}
