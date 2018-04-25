package com.shop.controller;

import com.shop.domain.UserRole;
import com.shop.domainDto.UserRoleDto;
import com.shop.mapper.RoleMapper;
import com.shop.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin/role/")
public class RoleController {
    private RoleService roleService;

    private RoleMapper roleMapper;

    @Autowired
    public RoleController(final RoleService roleService) {
        this.roleService = roleService;
        this.roleMapper = roleMapper;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/")
    public UserRole createRole(@RequestBody UserRoleDto userRoleDto) {
        return roleService.createNewRole(roleMapper.roleDtoToRole(userRoleDto));
    }
}
