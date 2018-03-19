package com.shop.controller;

import com.shop.domainDto.UsersDto;
import com.shop.mapper.UsersMapper;
import com.shop.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
public class UsersController {
    @Autowired
    private DbService service;

    @Autowired
    private UsersMapper usersMapper;

    @RequestMapping(method = RequestMethod.POST, name = "/createAccount", consumes = APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UsersDto usersDto) {
        service.saveUser(usersMapper.usersDtoToUsers(usersDto));
    }
}
