package com.shop.service;

import com.shop.domain.Products;
import com.shop.repository.ProductsRepository;
import com.shop.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductsRepository productsRepository;

    private final ShoppingCartRepository shoppingCartRepository;

    @Autowired
    public ProductService(ProductsRepository productsRepository, ShoppingCartRepository shoppingCartRepository) {
        this.productsRepository = productsRepository;
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public Products updateProducts(final Long cartId, final Long productId) {
        Products products = productsRepository.getById(productId);
        products.getShoppingCarts().add(shoppingCartRepository.getById(cartId));
        saveProduct(products);
        return products;
    }

    public Products getById(final Long id) {
        return productsRepository.getById(id);
    }

    public Products saveProduct(final Products products) {
        return productsRepository.save(products);
    }

    public List<Products> getAllProducts() {
        return productsRepository.findAll();
    }

    public void deleteProduct(final Long id) {
        productsRepository.deleteById(id);
    }

    public Page getAllProducts(final Pageable pageable) {
        return productsRepository.findAll(pageable);
    }
}
