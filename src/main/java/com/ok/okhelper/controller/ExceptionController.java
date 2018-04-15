package com.ok.okhelper.controller;

import org.apache.shiro.authc.AuthenticationException;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;

/**
 * @author: zc
 * @description:
 * @date: 2018/4/13
 */
@RestController
public class ExceptionController {

    @RequestMapping("/401")
    public void handle401(ServletRequest request) {
        Object error = request.getAttribute("error");
        if (error != null) {
            throw (RuntimeException) error;
        } else {
            throw new AuthenticationException("未登录/验证失败");
        }

    }

}
