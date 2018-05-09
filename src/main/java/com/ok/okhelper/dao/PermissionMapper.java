package com.ok.okhelper.dao;

import com.ok.okhelper.pojo.po.Permission;
import com.ok.okhelper.pojo.vo.PermissionMenuVo;
import com.ok.okhelper.util.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionMapper extends MyMapper<Permission> {

    List<Permission> findAddPermission(Long userId);

    List<PermissionMenuVo> findPermissionMenu(Long userId);
}