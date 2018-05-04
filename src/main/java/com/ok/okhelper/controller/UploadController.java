package com.ok.okhelper.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.ok.okhelper.common.ServerResponse;
import com.ok.okhelper.dao.StockMapper;
import com.ok.okhelper.dao.StoreMapper;
import com.ok.okhelper.exception.IllegalException;
import com.ok.okhelper.pojo.po.Store;
import com.ok.okhelper.pojo.vo.UploadVo;
import com.ok.okhelper.service.UploadService;
import com.ok.okhelper.shiro.JWTUtil;
import com.ok.okhelper.until.PropertiesUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * Author: zc
 * Date: 2018/4/25
 * Description:
 */
@RestController
@Api(tags = "文件上传模块")
@Slf4j
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @Autowired
    private StoreMapper storeMapper;


    @PostMapping(value = "/upload/img")
    @ApiOperation(value = "上传图片(包括商品图片、分类图片等等，用户头像请转到头像上传接口)", notes = "注意：url为绝对路径、uri是相对路径，发请求请携带uri相对路径，数据库只存相对路径")
    @ApiImplicitParams(@ApiImplicitParam(name = "file", value = "文件", required = true, dataType = "File"))
    public ServerResponse<UploadVo> uploadImg(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        if (!file.isEmpty()&&file.getContentType().startsWith("image")) {
            //定义临时文件夹
            String tmp_path = request.getSession().getServletContext().getRealPath("tmp");

            String targetFileName =
                    uploadService.upload(file, tmp_path, PropertiesUtil.getProperty("cos.path.img"));
            String url =
                    PropertiesUtil.getProperty("cos.server.http.prefix") + PropertiesUtil.getProperty("cos.path.img") + targetFileName;

            UploadVo uploadVo = new UploadVo(targetFileName, url);

            return ServerResponse.createBySuccess(uploadVo);
        } else {
            throw new IllegalException("请求文件文件格式不对");
        }

    }

    @PostMapping(value = "/upload/avator")
    @ApiOperation(value = "头像上传", notes = "注意：url为绝对路径、uri是相对路径，发请求请携带uri相对路径，数据库只存相对路径")
    @ApiImplicitParams(@ApiImplicitParam(name = "file", value = "文件", required = true, dataType = "File"))
    public ServerResponse<UploadVo> uploadAvator(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        if (!file.isEmpty()&&file.getContentType().startsWith("image")) {

            //定义临时文件夹
            String tmp_path = request.getSession().getServletContext().getRealPath("tmp");

            String targetFileName =
                    uploadService.upload(file, tmp_path, PropertiesUtil.getProperty("cos.path.avator"));
            String url =
                    PropertiesUtil.getProperty("cos.server.http.prefix") + PropertiesUtil.getProperty("cos.path.avator") + targetFileName;

            UploadVo uploadVo = new UploadVo(targetFileName, url);

            return ServerResponse.createBySuccess(uploadVo);
        } else {
            throw new IllegalException("请求文件文件格式不对");
        }

    }


    @PostMapping(value = "/upload/money_code")
    @ApiOperation(value = "上传并修改收款码(支付宝/微信)", notes = "注意：url为绝对路径、uri是相对路径，发请求请携带uri相对路径，数据库只存相对路径")
    @ApiImplicitParams(@ApiImplicitParam(name = "file", value = "文件", required = true, dataType = "File"))
    public ServerResponse<UploadVo> uploadMoneyCode(@RequestParam("file") MultipartFile file,
                                                    @ApiParam(value = "收款码类型(alipay/weichat)", required = true)
                                                    @RequestParam(required = true) String codeType, HttpServletRequest request) throws IOException {
        if (!file.isEmpty()&&file.getContentType().startsWith("image")) {

            //定义临时文件夹
            String tmp_path = request.getSession().getServletContext().getRealPath("tmp");

            String targetFileName =
                    uploadService.upload(file, tmp_path, PropertiesUtil.getProperty("cos.path.money-code"));
            String url =
                    PropertiesUtil.getProperty("cos.server.http.prefix") + PropertiesUtil.getProperty("cos.path.money-code") + targetFileName;

            //修改收款码
            Store dbstore = storeMapper.selectByPrimaryKey(JWTUtil.getStoreId());
            if (dbstore == null) {
                throw new IllegalException("商铺信息错误");
            }
            String moneyCode = dbstore.getMoneyCode();

            Store store = new Store();
            store.setId(JWTUtil.getStoreId());
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, String> moneyCodeMap = objectMapper.readValue(moneyCode != null ? moneyCode : "{}", Map.class);
            moneyCodeMap.put(codeType, url);
            store.setMoneyCode(objectMapper.writeValueAsString(moneyCodeMap));

            storeMapper.updateByPrimaryKeySelective(store);

            UploadVo uploadVo = new UploadVo(targetFileName, url);

            return ServerResponse.createBySuccess(uploadVo);
        } else {
            throw new IllegalException("请求文件文件格式不对");
        }

    }


}
