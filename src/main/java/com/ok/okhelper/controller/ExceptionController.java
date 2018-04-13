package com.ok.okhelper.controller;

import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zc
 * @description:
 * @date: 2018/4/13
 */
@RestController
public class ExceptionController {

    @RequestMapping("/401")
    public String handle401() {
        throw new UnauthenticatedException("we");
    }

}
