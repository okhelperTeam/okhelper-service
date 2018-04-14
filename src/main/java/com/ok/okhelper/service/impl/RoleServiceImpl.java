package com.ok.okhelper.service.impl;

import com.ok.okhelper.dao.RoleMapper;
import com.ok.okhelper.pojo.dto.RoleDto;
import com.ok.okhelper.pojo.po.Role;
import com.ok.okhelper.service.RoleService;
import com.ok.okhelper.shiro.JWTUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Boolean postRole(Long operator, RoleDto roleDto) {
        Role role = new Role();
        role.setOperator(operator);
        BeanUtils.copyProperties(roleDto, role);
        return roleMapper.insertSelective(role) > 0;
    }
}
