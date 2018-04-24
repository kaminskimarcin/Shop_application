package com.shop.mapper;

import com.shop.domain.Role;
import com.shop.domain.Users;
import com.shop.domainDto.RoleDto;
import com.shop.domainDto.UsersDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UsersMapper {

    private RoleMapper roleMapper;

    @Autowired
    public UsersMapper(final RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    public Users usersDtoToUsers(final UsersDto usersDto) {
        return new Users(
                usersDto.getId(),
                usersDto.getName(),
                usersDto.getPassword(),
                usersDto.getEmail(),
                usersDto.getPassword(),
                roleMapper.mapToRoleSet(usersDto.getRolesDto())
        );
    }

    public UsersDto usersToUsersDto(final Users users) {
        return new UsersDto(
                users.getId(),
                users.getName(),
                users.getPassword(),
                users.getEmail(),
                roleMapper.mapToRoleDtoSet(users.getRoles())
        );
    }
}
