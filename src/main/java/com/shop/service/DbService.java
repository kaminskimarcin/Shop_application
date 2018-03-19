package com.shop.service;

import com.shop.domain.Products;
import com.shop.domain.ShoppingCart;
import com.shop.domain.Users;
import com.shop.repository.ProductsRepository;
import com.shop.repository.ShoppingCartRepository;
import com.shop.repository.UsersRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DbService {
    @Autowired
    ProductsRepository productsRepository;

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private UsersRepository usersRepository;

    public List<Products> getAllProducts() {
        return productsRepository.findAll();
    }

    public Products saveProduct(final Products products) {
        return productsRepository.save(products);
    }

    public void deleteProduct(final Long id) {
        productsRepository.deleteById(id);
    }

    public Products saveProductsInShoppingCXart(final Long id) {
        return shoppingCartRepository.saveById(id);
    }

    public Users saveUser(final Users users) {
        return usersRepository.save(users);
    }
}
