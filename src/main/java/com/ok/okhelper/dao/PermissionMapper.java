package com.ok.okhelper.dao;

import com.ok.okhelper.po.Permission;
import com.ok.okhelper.until.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionMapper extends MyMapper<Permission> {

	List<String> findAddPermissionCode(Long userId);
}