package com.shop.repository;

import com.shop.domain.ShoppingCart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long> {

    ShoppingCart getById(final Long id);

    ShoppingCart save(ShoppingCart shoppingCart);
}
