package com.shop.service;

import com.shop.domain.UserRole;
import com.shop.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public RoleService(final RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public UserRole createNewRole(final UserRole userRole) {
        return roleRepository.save(userRole);
    }
}
