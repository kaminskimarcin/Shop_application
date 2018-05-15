package com.shop.service;

import com.shop.domain.UserRole;
import com.shop.domain.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public CustomDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

        Users users = userService.getUserByUsername(username);
        List<GrantedAuthority> authorities = buildUserAuthority(users.getUserRole());
        System.out.println("user role " + users.getUserRole());
        return new User(users.getName(), users.getPassword(), true, true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {

        Set<GrantedAuthority> setAuths = new HashSet<>();

        return userRoles.stream()
                .map(k -> new SimpleGrantedAuthority(k.getRole()))
                .collect(Collectors.toList());
    }
}
