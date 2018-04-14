package com.ok.okhelper.service;


import com.ok.okhelper.pojo.dto.RoleDto;

public interface RoleService {
    Boolean postRole(Long operator, RoleDto roleDto);
}
