package com.shop.repository;

import com.shop.domain.ShoppingCart;
import com.shop.domain.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long> {

    ShoppingCart getById(final Long id);

    ShoppingCart getByUsers(final Users users);

    ShoppingCart save(ShoppingCart shoppingCart);
}
