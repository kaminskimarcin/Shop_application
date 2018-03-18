package com.shop.mapper;

import com.shop.domain.Users;
import com.shop.domainDto.UsersDto;
import org.springframework.stereotype.Component;

@Component
public class UsersMapper {

    public Users usersDtoToUsers(final UsersDto usersDto) {
        return new Users(
                usersDto.getId(),
                usersDto.getName(),
                usersDto.getPassword()
        );
    }

    public UsersDto usersToUsersDto(final Users users) {
        return new UsersDto(
                users.getId(),
                users.getName(),
                users.getPassword()
        );
    }

}
