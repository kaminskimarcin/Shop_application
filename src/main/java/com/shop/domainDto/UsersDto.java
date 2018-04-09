package com.shop.domainDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class UsersDto {
    private Long id;
    private String name;
    private String password;
    private String email;
}
