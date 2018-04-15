package com.ok.okhelper.dao;

import com.ok.okhelper.pojo.dto.UserDto;
import com.ok.okhelper.pojo.po.User;
import com.ok.okhelper.until.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper extends MyMapper<User> {

    Long findUserIdByName(String userName);

    User findUserByUserName(String userName);

    Long findStoreIdByUserId(Long userId);

    List<String> checkUserName(String userName);

    List<Long> getUserListByStoreId(Long storeId);

    String getPassWordByUserId(Long user_id);

//	int register(UserDto userDto);
}