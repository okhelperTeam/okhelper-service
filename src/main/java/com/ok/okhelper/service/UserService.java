package com.ok.okhelper.service;

import com.ok.okhelper.bo.UserBo;
import com.ok.okhelper.po.User;

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

    public User findUserByUserNme(Integer username);


	public UserBo findUserRolePersmission(Integer userId);
}
