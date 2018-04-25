package com.shop.mapper;

import com.shop.domain.UserRole;
import com.shop.domainDto.UserRoleDto;
import com.shop.domainDto.UsersDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RoleMapper {

    @Autowired
    private UsersMapper usersMapper;

    public UserRole roleDtoToRole(final UserRoleDto userRoleDto) {
        return new UserRole(
                userRoleDto.getId(),
                usersMapper.usersDtoToUsers(userRoleDto.getUsersDto()),
                userRoleDto.getRole()
        );
    }

    public UserRoleDto roleToRoleDto(final UserRole userRole) {
        return new UserRoleDto(
                userRole.getId(),
                usersMapper.usersToUsersDto(userRole.getUsers()),
                userRole.getRole()
        );
    }

    public Set<UserRole> mapToRoleSet(final Set<UserRoleDto> userRoleDtosSet) {
        return userRoleDtosSet.stream()
                .map(k -> new UserRole(k.getId(),usersMapper.usersDtoToUsers(k.getUsersDto()), k.getRole()))
                .collect(Collectors.toSet());
    }

    public Set<UserRoleDto> mapToRoleDtoSet(final Set<UserRole> userRoleSet) {
        return userRoleSet.stream()
                .map(k -> new UserRoleDto(k.getId(), usersMapper.usersToUsersDto(k.getUsers()), k.getRole()))
                .collect(Collectors.toSet());
    }
}
