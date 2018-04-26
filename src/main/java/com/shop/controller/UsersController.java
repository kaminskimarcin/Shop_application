package com.shop.controller;

import com.shop.domainDto.UsersDto;
import com.shop.mapper.UsersMapper;
import com.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RequestMapping("/")
@RestController
public class UsersController {
    @Autowired
    private UserService userService;

    @Autowired
    private UsersMapper usersMapper;

    @RequestMapping(method = RequestMethod.POST, value = "/shop/createAccount", consumes = APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UsersDto usersDto) {
        userService.saveUser(usersMapper.usersDtoToUsers(usersDto));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/getUser")
    public UsersDto getUser() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return usersMapper.usersToUsersDto(userService.getUserByUsername(name));
    }
}
