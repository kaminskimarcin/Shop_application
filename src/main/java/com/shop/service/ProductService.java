package com.shop.service;

import com.shop.domain.Products;
import com.shop.mapper.ProductsMapper;
import com.shop.repository.ProductsRepository;
import com.shop.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ProductsMapper productsMapper;

    public Products updateProducts(final Long cartId, final Long productId) {
        Products products = productsRepository.getById(productId);
        products.getShoppingCarts().add(shoppingCartRepository.getById(cartId));
        saveProduct(products);
        return products;
    }

    public Optional<Products> getById(final Long id) {
        return productsRepository.findById(id);
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

}
