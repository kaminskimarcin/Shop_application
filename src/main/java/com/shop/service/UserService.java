package com.shop.service;

import com.shop.domain.Users;
import com.shop.domainDto.ShoppingCartDto;
import com.shop.mapper.ShoppingCartMapper;
import com.shop.repository.UsersRepository;
import org.hibernate.SessionFactory;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;

@Service
public class UserService {
    private final ShoppingCartService shoppingCartService;

    private final ShoppingCartMapper shoppingCartMapper;

    private final UsersRepository usersRepository;

    @Autowired
    private SessionFactory sessionFactory;

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

    @SuppressWarnings("unchecked")
    public Users findByUsername(final String name) {
        List<Users> users = new ArrayList<>();

        users = sessionFactory.getCurrentSession()
                .createQuery("FROM USERS WHERE name =?")
                .setParameter(0, name)
                .list();

        if(users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
    }
}
