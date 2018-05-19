package com.ok.okhelper.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ok.okhelper.common.ServerResponse;
import com.ok.okhelper.dao.StoreMapper;
import com.ok.okhelper.dao.UserMapper;
import com.ok.okhelper.exception.IllegalException;
import com.ok.okhelper.pojo.po.Store;
import com.ok.okhelper.pojo.po.User;
import com.ok.okhelper.pojo.vo.UploadVo;
import com.ok.okhelper.service.UploadService;
import com.ok.okhelper.shiro.JWTUtil;
import com.ok.okhelper.util.COSUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    private UserMapper userMapper;

    @Value("${cos.server.http.prefix}")
    private String cosHttpPrefix;

    @Value("${cos.path.img}")
    private String cosPathImg;

    @Value("${cos.path.avator}")
    private String cosPathAvator;

    @Value("${cos.path.money-code}")
    private String cosPathMoneyCode;

    @Value("${cos.path.store-logo}")
    private String cosPathStoreLogo;


    @PostMapping(value = "/upload/img")
    @ApiOperation(value = "上传图片(包括商品图片、分类图片等等，用户头像请转到头像上传接口)", notes = "此接口只上传图片，无业务逻辑")
    @ApiImplicitParams(@ApiImplicitParam(name = "file", value = "文件", required = true, dataType = "File"))
    public ServerResponse<UploadVo> uploadImg(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        if (!file.isEmpty() && file.getContentType().startsWith("image")) {
            //定义临时文件夹
            String tmp_path = request.getSession().getServletContext().getRealPath("tmp");

            String targetFileName = uploadService.upload(file, tmp_path, cosPathImg);

            String url = cosHttpPrefix + cosPathImg + targetFileName;

            UploadVo uploadVo = new UploadVo(targetFileName, url);

            return ServerResponse.createBySuccess(uploadVo);
        } else {
            throw new IllegalException("请求文件文件格式不对");
        }

    }

    @PostMapping(value = "/upload/avator/me")
    @ApiOperation(value = "上传并修改我的头像", notes = "")
    @ApiImplicitParams(@ApiImplicitParam(name = "file", value = "文件", required = true, dataType = "File"))
    public ServerResponse<UploadVo> uploadAvator(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        if (!file.isEmpty() && file.getContentType().startsWith("image")) {

            //定义临时文件夹
            String tmp_path = request.getSession().getServletContext().getRealPath("tmp");

            String targetFileName = uploadService.upload(file, tmp_path, cosPathAvator);
            String url = cosHttpPrefix + cosPathAvator + targetFileName;
            User dbuser = userMapper.selectByPrimaryKey(JWTUtil.getUserId());

            //删除旧头像
            if (StringUtils.isNotBlank(dbuser.getUserAvatar())) {
                COSUtil.deleteFile(dbuser.getUserAvatar());
            }

            //更新用户头像
            User user = new User();
            user.setId(JWTUtil.getUserId());
            user.setUserAvatar(url);
            userMapper.updateByPrimaryKeySelective(user);

            UploadVo uploadVo = new UploadVo(targetFileName, url);

            return ServerResponse.createBySuccess(uploadVo);
        } else {
            throw new IllegalException("请求文件文件格式不对");
        }

    }


    @RequiresPermissions("store:edit")
    @PostMapping(value = "/upload/money_code")
    @ApiOperation(value = "上传并修改收款码(支付宝/微信)", notes = "")
    @ApiImplicitParams(@ApiImplicitParam(name = "file", value = "文件", required = true, dataType = "File"))
    public ServerResponse<UploadVo> uploadMoneyCode(@RequestParam("file") MultipartFile file,
                                                    @ApiParam(value = "收款码类型(alipay/weichat)", required = true)
                                                    @RequestParam(required = true) String codeType, HttpServletRequest request) throws IOException {
        if (!file.isEmpty() && file.getContentType().startsWith("image")) {

            //定义临时文件夹
            String tmp_path = request.getSession().getServletContext().getRealPath("tmp");

            String targetFileName = uploadService.upload(file, tmp_path, cosPathMoneyCode);

            String url = cosHttpPrefix + cosPathMoneyCode + targetFileName;

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

    @RequiresPermissions("store:edit")
    @PostMapping(value = "/upload/store_logo")
    @ApiOperation(value = "上传并修改商铺logo", notes = "")
    @ApiImplicitParams(@ApiImplicitParam(name = "file", value = "文件", required = true, dataType = "File"))
    public ServerResponse<UploadVo> uploadStoreLogo(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        if (!file.isEmpty() && file.getContentType().startsWith("image")) {

            //定义临时文件夹
            String tmp_path = request.getSession().getServletContext().getRealPath("tmp");

            String targetFileName = uploadService.upload(file, tmp_path, cosPathStoreLogo);

            String url = cosHttpPrefix + cosPathStoreLogo + targetFileName;

            //修改收款码
            Store dbstore = storeMapper.selectByPrimaryKey(JWTUtil.getStoreId());
            if (dbstore == null) {
                throw new IllegalException("商铺信息错误");
            }

            //删除旧图片
            if (StringUtils.isNotBlank(dbstore.getStoreLogo())) {
                COSUtil.deleteFile(dbstore.getStoreLogo());
            }

            Store store = new Store();
            store.setId(JWTUtil.getStoreId());
            store.setStoreLogo(url);

            storeMapper.updateByPrimaryKeySelective(store);

            UploadVo uploadVo = new UploadVo(targetFileName, url);

            return ServerResponse.createBySuccess(uploadVo);
        } else {
            throw new IllegalException("请求文件文件格式不对");
        }

    }


}
