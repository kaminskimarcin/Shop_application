package com.shop.domainDto;

import com.shop.domain.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleDto {
    private Long id;
    private UsersDto usersDto;
    private String role;
}
