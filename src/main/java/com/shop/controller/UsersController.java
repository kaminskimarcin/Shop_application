package com.shop.controller;

import com.shop.domain.Users;
import com.shop.domainDto.UsersDto;
import com.shop.mapper.UsersMapper;
import com.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RequestMapping("/user")
@RestController
public class UsersController {
    @Autowired
    private UserService userService;

    @Autowired
    private UsersMapper usersMapper;

    @RequestMapping(method = RequestMethod.POST, value = "/createAccount", consumes = APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UsersDto usersDto) {
        userService.saveUser(usersMapper.usersDtoToUsers(usersDto));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getUser/{id}")
    public Users getUser(@PathVariable final Long id) {
        return userService.getUser(id);
    }
}
