package com.shop.service;

import com.shop.domain.Products;
import com.shop.domain.ShoppingCart;
import com.shop.domain.Users;
import com.shop.domainDto.ShoppingCartDto;
import com.shop.mapper.ShoppingCartMapper;
import com.shop.mapper.UsersMapper;
import com.shop.repository.ProductsRepository;
import com.shop.repository.ShoppingCartRepository;
import com.shop.repository.UsersRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DbService {
    @Autowired
    ProductsRepository productsRepository;

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Autowired
    private UsersMapper usersMapper;

    public List<Products> getAllProducts() {
        return productsRepository.findAll();
    }

    public Products saveProduct(final Products products) {
        return productsRepository.save(products);
    }

    public void deleteProduct(final Long id) {
        productsRepository.deleteById(id);
    }

    public ShoppingCart saveProductsInShoppingCart(final ShoppingCart shoppingCart, Products product) {
        shoppingCart.getProducts().add(product);
        return shoppingCartRepository.save(shoppingCart);
    }

    public ShoppingCart createShoppingCart(final ShoppingCart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }

    public Users saveUser(final Users users) {
        createShoppingCart(shoppingCartMapper.shoppingCartDtoToShoppingCart(new ShoppingCartDto(users, new ArrayList<>())));
        return usersRepository.save(users);
    }
}
