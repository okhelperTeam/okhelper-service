package com.ok.okhelper.service.impl;

import com.ok.okhelper.dao.PermissionMapper;
import com.ok.okhelper.dao.RoleMapper;
import com.ok.okhelper.pojo.dto.RoleDto;
import com.ok.okhelper.pojo.po.Permission;
import com.ok.okhelper.pojo.po.Role;
import com.ok.okhelper.service.RoleService;
import com.ok.okhelper.shiro.JWTUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author: zc
 * @description:角色
 * @date: 2018/4/14
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
//    @CachePut(value = "role_list", key = "#roleDto.getStoreId()")
    public Boolean postRole(RoleDto roleDto) {
        Role role = new Role();
        BeanUtils.copyProperties(roleDto, role);
        return roleMapper.insertSelective(role) > 0;
    }

    @Override
//    @Cacheable(value = "role_list", key = "#storeId")
    public List<Role> getRoleListByStore(Long storeId) {
        Example example = new Example(Role.class);
        example.createCriteria().andEqualTo("storeId", storeId)
                .orEqualTo("storeId", 0)
                .andNotBetween("id", 1, 2);
        List<Role> roles = roleMapper.selectByExample(example);
        return roles;
    }

    @Override
//    @CacheEvict(value = "role_list", key = "#storeId")
    public void clearRoleListCache(Long store_id) {
    }


}
