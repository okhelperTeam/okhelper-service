package com.ok.okhelper.bo;

import lombok.Data;

import java.util.List;

/*
*Author:zhangxin_an
*Description:
*Data:Created in 21:32 2018/4/9
*/
@Data
public class UserBo {
    private String userName;
    private String password;
    private List<RoleBo> roles;
}
