package com.shop.security;

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
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepository.getByUsername(username);

        if(user == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }

        return new User(username, user.getPassword(), true, true, true, true, AuthorityUtils.createAuthorityList(user.getRole()));
    }

}
