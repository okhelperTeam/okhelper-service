package com.ok.okhelper.dao;

import com.ok.okhelper.po.Role;
import com.ok.okhelper.until.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper extends MyMapper<Role> {
	
//	public List<Role> findRoleByUserId(Integer userId);
	
}