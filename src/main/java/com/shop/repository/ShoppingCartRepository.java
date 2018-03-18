package com.shop.repository;

import com.shop.domain.Products;
import com.shop.domain.ShoppingCart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long> {

    Products addToCart(Products products);
}
