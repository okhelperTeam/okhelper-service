package com.ok.okhelper.service;

import com.ok.okhelper.common.ServerResponse;
import com.ok.okhelper.pojo.dto.UserDto;
import com.ok.okhelper.pojo.po.User;

/*
 *Author:zhangxin_an
 *Description:
 *Data:Created in 21:26 2018/4/10
 */
public interface UserService {

    /*
     *Author:zhangxin_an
     *Description:查找用户
     *Data:Created in  .21:30 2018/4/10
     */

    public User findUserByUserNme(String username);

    ServerResponse loginUser(String userName, String password, String ip);

    /*
     * @Author zhangxin_an
     * @Date 2018/4/15 8:27
     * @Params [userDto]
     * @Return com.ok.okhelper.common.ServerResponse
     * @Description:用户注册
     */
    ServerResponse userRegister(UserDto userDto);

    ServerResponse checkUserName(String userName);
}
