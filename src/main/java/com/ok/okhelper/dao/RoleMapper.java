package com.ok.okhelper.dao;

import com.ok.okhelper.pojo.po.Role;
import com.ok.okhelper.until.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper extends MyMapper<Role> {
	
	List<Role> findRoleByUserId(Long userId);

    int insertUserRole(@Param("user_id") Long user_id, @Param("role_id") Long role_id, @Param("operator") Long operator);
}