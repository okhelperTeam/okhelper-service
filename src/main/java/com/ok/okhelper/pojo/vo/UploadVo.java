package com.ok.okhelper.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: zc
 * Date: 2018/4/26
 * Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadVo {
    //相对路径(文件名)
    private String fileName;

    //绝对路径
    private String url;
}
