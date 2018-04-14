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
	
	List<Long> findStoreIdByUserId(Long userId);
	
//	int register(UserDto userDto);
}