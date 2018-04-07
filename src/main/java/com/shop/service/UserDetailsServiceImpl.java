package com.shop.service;

import com.shop.domain.Users;
import com.shop.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Users user = userService.getUser(23L);

        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }

        return new User(name, user.getPassword(), true, true, true,
                true, AuthorityUtils.createAuthorityList(user.getRole()));

    }

}