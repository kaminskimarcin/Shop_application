package com.shop.service;

import com.shop.domain.Products;
import com.shop.domain.ShoppingCart;
import com.shop.repository.ProductsRepository;
import com.shop.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DbService {
    @Autowired
    ProductsRepository productsRepository;

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

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
}
