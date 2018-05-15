package com.ok.okhelper.dao;

import com.ok.okhelper.pojo.bo.IdAndNameBo;
import com.ok.okhelper.pojo.bo.UserBo;
import com.ok.okhelper.pojo.po.User;
import com.ok.okhelper.util.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface UserMapper extends MyMapper<User> {

    Long findUserIdByName(String userName);

    User findUserByUserName(String userName);

    Long findStoreIdByUserId(Long userId);

    List<String> checkUserName(String userName);

    List<Long> getUserListByStoreId(Long storeId);

    String getPassWordByUserId(Long user_id);

    int deleteAllRoleFromUser(Long userId);

    int insertRoleToUser(@Param("user_id") Long user_id, @Param("role_id") Long role_id, @Param("operator") Long operator);


    IdAndNameBo getIdAndName(Long id);

    List<UserBo> getEmployeeList(@Param("storeId") Long storeId, @Param("deleteStatus") Integer deleteStatus);

//	int checkPassword(String password);

//	int register(UserDto userDto);
}