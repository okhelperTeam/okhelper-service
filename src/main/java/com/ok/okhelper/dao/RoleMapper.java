package com.ok.okhelper.dao;

import com.ok.okhelper.pojo.po.Role;
import com.ok.okhelper.pojo.vo.RolePermissionVo;
import com.ok.okhelper.util.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper extends MyMapper<Role> {
	
	List<Role> findRoleByUserId(Long userId);

    int insertUserRole(@Param("userId") Long userId, @Param("roleId") Long roleId);

    List<RolePermissionVo> findRolePermissionByRoleId(Long storeId);

    int insertRolePermission(@Param("rid") Long roleId, @Param("pid") Long permissionId);

    int findUserCountByRoleId(@Param("storeId") Long storeId, @Param("roleId") Long roleId);
}