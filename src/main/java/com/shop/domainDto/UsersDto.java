package com.shop.domainDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public class UsersDto {
    private Long id;
    private String name;
    private String password;
    private String email;
    private boolean enabled;
    private Set<UserRoleDto> rolesDto;
}
