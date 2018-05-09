package com.ok.okhelper.controller;

import com.ok.okhelper.common.ServerResponse;
import com.ok.okhelper.util.NumberGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: zc
 * Date: 2018/4/24
 * Description:
 */
@RestController
@Api(tags = "工具模块")
public class UtilController {

    @ApiOperation(value = "生成随机条码",notes = "当商品没有条码时后端随机生成一个")
    @GetMapping("/until/bar_code")
    public ServerResponse generatorBarCode(){
        String barCode = NumberGenerator.generatorBarCode();
        Map<String, String> map=new HashMap<>();
        map.put("barCode",barCode);
        return ServerResponse.createBySuccess(map);
    }
}
