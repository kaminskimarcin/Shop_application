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
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class DbService {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(DbService.class);

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

    public Products getById(final Long id) {
        return productsRepository.getById(id);
    }

    public Products saveProduct(final Products products) {
        return productsRepository.save(products);
    }

    public void deleteProduct(final Long id) {
        productsRepository.deleteById(id);
    }

    public ShoppingCartDto saveProductsInShoppingCart(final Long cartId, final Long productId) {
        LOGGER.info("Starting...");
        ShoppingCart shoppingCart = shoppingCartRepository.getById(cartId);
        LOGGER.info("Mapping...");
        ShoppingCartDto shoppingCartDto = shoppingCartMapper.shoppingCartToShoppingCartDto(shoppingCart);
        LOGGER.info("Cart id: " + shoppingCartDto.getId());
        LOGGER.info("User id: " + shoppingCartDto.getUsers());
        shoppingCartDto.getProducts().add(getById(productId));
        LOGGER.info("Products: " + shoppingCartDto.getProducts().size());
        return shoppingCartDto;
    }

    public ShoppingCart saveShoppingCart(final ShoppingCart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }

    public Users saveUser(final Users users) {
        saveShoppingCart(shoppingCartMapper.shoppingCartDtoToShoppingCart(new ShoppingCartDto(users, new ArrayList<>())));
        return usersRepository.save(users);
    }

}
