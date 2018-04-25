package com.shop.mapper;

import com.shop.domain.Users;
import com.shop.domainDto.UsersDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
                usersDto.isEnabled(),
                roleMapper.mapToRoleSet(usersDto.getRolesDto())
        );
    }

    public UsersDto usersToUsersDto(final Users users) {
        return new UsersDto(
                users.getId(),
                users.getName(),
                users.getPassword(),
                users.getEmail(),
                users.isEnabled(),
                roleMapper.mapToRoleDtoSet(users.getUserRole())
        );
    }
}
