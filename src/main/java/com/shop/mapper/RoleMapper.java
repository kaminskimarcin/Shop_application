package com.shop.mapper;

import com.shop.domain.Role;
import com.shop.domainDto.RoleDto;
import com.shop.domainDto.UsersDto;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RoleMapper {
    private Long id;
    private String name;
    private Set<UsersDto> usersDto;

    public Role roleDtoToRole(final RoleDto roleDto) {
        return new Role(
                roleDto.getId(),
                roleDto.getName()
        );
    }

    public RoleDto roleToRoleDto(final Role role) {
        return new RoleDto(
                role.getId(),
                role.getName()
        );
    }

    public Set<Role> mapToRoleSet(final Set<RoleDto> roleDtosSet) {
        return roleDtosSet.stream()
                .map(k -> new Role(k.getId(), k.getName()))
                .collect(Collectors.toSet());
    }

    public Set<RoleDto> mapToRoleDtoSet(final Set<Role> roleSet) {
        return roleSet.stream()
                .map(k -> new RoleDto(k.getId(), k.getName()))
                .collect(Collectors.toSet());
    }
}
