package com.ok.okhelper.service;


import com.ok.okhelper.pojo.dto.RoleDto;
import com.ok.okhelper.pojo.po.Permission;
import com.ok.okhelper.pojo.po.Role;
import org.springframework.cache.annotation.CacheEvict;

import java.util.List;

/**
 * @author: zc
 * @description:
 * @date: 2018/4/14
 */
public interface RoleService {
    Boolean postRole(RoleDto roleDto);

    List<Role> getRoleListByStore(Long storeId);

    void clearRoleListCache(Long store_id);
}
