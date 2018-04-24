package com.shop.service;

import com.shop.domain.Users;
import com.shop.domainDto.ShoppingCartDto;
import com.shop.mapper.ShoppingCartMapper;
import com.shop.repository.UsersRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import static java.util.Collections.emptyList;

@Service
public class UserService {
    private final ShoppingCartService shoppingCartService;

    private final ShoppingCartMapper shoppingCartMapper;

    private final UsersRepository usersRepository;

    @Autowired
    public UserService(ShoppingCartMapper shoppingCartMapper, ShoppingCartService shoppingCartService, UsersRepository usersRepository) {
        this.shoppingCartMapper = shoppingCartMapper;
        this.shoppingCartService = shoppingCartService;
        this.usersRepository = usersRepository;
    }

    private String hashPassword(String userPassword) {
        return BCrypt.hashpw(userPassword, BCrypt.gensalt());
    }

    public Users saveUser(final Users users) {
        try {
            shoppingCartService.saveShoppingCart(shoppingCartMapper.shoppingCartDtoToShoppingCart(new ShoppingCartDto(users, new ArrayList<>())));
            users.setPassword(hashPassword(users.getPassword()));
            return usersRepository.save(users);
        } catch (DataIntegrityViolationException e) {
            return null;
        }
    }

    public Users getUser(final Long id) {
        return usersRepository.getById(id);
    }
}
