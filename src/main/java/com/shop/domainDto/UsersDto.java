package com.shop.domainDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UsersDto {
    private Long id;
    private String name;
    private String password;
    private String email;
}
