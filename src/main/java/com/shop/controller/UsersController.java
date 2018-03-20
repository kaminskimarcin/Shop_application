package com.shop.controller;

import com.shop.domainDto.UsersDto;
import com.shop.mapper.UsersMapper;
import com.shop.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RequestMapping("/user")
@RestController
public class UsersController {
    @Autowired
    private DbService service;

    @Autowired
    private UsersMapper usersMapper;

    @RequestMapping(method = RequestMethod.POST, value = "/createAccount", consumes = APPLICATION_JSON_VALUE)
        public void createUser(@RequestBody UsersDto usersDto) {
            service.saveUser(usersMapper.usersDtoToUsers(usersDto));
    }
}
