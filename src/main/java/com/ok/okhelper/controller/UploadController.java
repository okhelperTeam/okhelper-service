package com.ok.okhelper.controller;

import com.google.common.collect.Maps;
import com.ok.okhelper.common.ServerResponse;
import com.ok.okhelper.exception.IllegalException;
import com.ok.okhelper.service.UploadService;
import com.ok.okhelper.until.PropertiesUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Author: zc
 * Date: 2018/4/25
 * Description:
 */
@RestController
@Api(tags = "文件上传模块")
public class UploadController {

    @Autowired
    private UploadService uploadService;


    @PostMapping(value = "/upload/img")
    @ApiOperation(value = "上传图片(包括商品图片、分类图片等等，用户头像请转到头像上传接口)", notes = "注意：url为绝对路径、uri是相对路径，发请求请携带uri相对路径，数据库只存相对路径")
    @ApiImplicitParams(@ApiImplicitParam(name = "file", value = "文件", required = true, dataType = "File"))
    public ServerResponse uploadImg(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        if (!file.isEmpty()) {

            //定义临时文件夹
            String tmp_path = request.getSession().getServletContext().getRealPath("tmp");

            String targetFileName =
                    uploadService.upload(file, tmp_path, PropertiesUtil.getProperty("cos.path.img"));
            String url =
                    PropertiesUtil.getProperty("cos.server.http.prefix") + PropertiesUtil.getProperty("cos.path.img") + targetFileName;

            Map fileMap = Maps.newHashMap();
            fileMap.put("uri", targetFileName);
            fileMap.put("url", url);

            return ServerResponse.createBySuccess(fileMap);
        } else {
            throw new IllegalException("文件是空的");
        }

    }

    @PostMapping(value = "/upload/avator")
    @ApiOperation(value = "头像上传", notes = "注意：url为绝对路径、uri是相对路径，发请求请携带uri相对路径，数据库只存相对路径")
    @ApiImplicitParams(@ApiImplicitParam(name = "file", value = "文件", required = true, dataType = "File"))
    public ServerResponse uploadAvator(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        if (!file.isEmpty()) {

            //定义临时文件夹
            String tmp_path = request.getSession().getServletContext().getRealPath("tmp");

            String targetFileName =
                    uploadService.upload(file, tmp_path, PropertiesUtil.getProperty("cos.path.avator"));
            String url =
                    PropertiesUtil.getProperty("cos.server.http.prefix") + PropertiesUtil.getProperty("cos.path.avator") + targetFileName;

            Map fileMap = Maps.newHashMap();
            fileMap.put("uri", targetFileName);
            fileMap.put("url", url);

            return ServerResponse.createBySuccess(fileMap);
        } else {
            throw new IllegalException("请求不是文件");
        }

    }


    @PostMapping(value = "/upload/money_code")
    @ApiOperation(value = "收款码上传", notes = "注意：url为绝对路径、uri是相对路径，发请求请携带uri相对路径，数据库只存相对路径")
    @ApiImplicitParams(@ApiImplicitParam(name = "file", value = "文件", required = true, dataType = "File"))
    public ServerResponse uploadMoneyCode(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        if (!file.isEmpty()) {

            //定义临时文件夹
            String tmp_path = request.getSession().getServletContext().getRealPath("tmp");

            String targetFileName =
                    uploadService.upload(file, tmp_path, PropertiesUtil.getProperty("cos.path.money-code"));
            String url =
                    PropertiesUtil.getProperty("cos.server.http.prefix") + PropertiesUtil.getProperty("cos.path.money-code") + targetFileName;

            Map fileMap = Maps.newHashMap();
            fileMap.put("uri", targetFileName);
            fileMap.put("url", url);

            return ServerResponse.createBySuccess(fileMap);
        } else {
            throw new IllegalException("请求不是文件");
        }

    }

}
