package com.shop.service;

import com.shop.domain.Products;
import com.shop.domain.ShoppingCart;
import com.shop.domain.Users;
import com.shop.domainDto.ProductsDto;
import com.shop.domainDto.ShoppingCartDto;
import com.shop.mapper.ProductsMapper;
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

    @Autowired
    private ProductsMapper productsMapper;

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
        LOGGER.info("CartId: " + cartId + " + productId: " + productId);
        ShoppingCart shoppingCart = shoppingCartRepository.getById(cartId);
        LOGGER.info("Mapping...");
        ShoppingCartDto shoppingCartDto = shoppingCartMapper.shoppingCartToShoppingCartDto(shoppingCart);
        LOGGER.info("Cart id: " + shoppingCartDto.getId());
        LOGGER.info("User id: " + shoppingCartDto.getUsers());
        shoppingCartDto.getProducts().add(getById(productId));
        LOGGER.info("Products: " + shoppingCartDto.getProducts().size());
        LOGGER.info(shoppingCartDto.getTest());
        updateProducts(cartId, productId);
        return shoppingCartDto;
    }

    public ProductsDto updateProducts(final Long cartId, final Long productId) {
        Products products = productsRepository.getById(productId);
        LOGGER.info("Information: " + products.getId() + " " + products.getName());
        ProductsDto productsDto = productsMapper.productsToProductsDto(products);
        productsDto.getShoppingCart().add(shoppingCartRepository.getById(cartId));
        saveProduct(productsMapper.productsDtoToProducts(productsDto));
        return productsDto;
    }

    public ShoppingCart saveShoppingCart(final ShoppingCart shoppingCart) {
        LOGGER.info("Products size: " + shoppingCart.getProducts().size());
        return shoppingCartRepository.save(shoppingCart);
    }

    public Users saveUser(final Users users) {
        saveShoppingCart(shoppingCartMapper.shoppingCartDtoToShoppingCart(new ShoppingCartDto(users, new ArrayList<>(), "Test test")));
        return usersRepository.save(users);
    }

}
