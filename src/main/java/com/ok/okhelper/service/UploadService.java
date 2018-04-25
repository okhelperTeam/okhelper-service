package com.ok.okhelper.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Author: zc
 * Date: 2018/4/25
 * Description:
 */
public interface UploadService {
    String upload(MultipartFile file, String tmp_path, String cosPathPrefix);
}
