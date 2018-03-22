package com.shop.mapper;

import com.shop.domain.ShoppingCart;
import com.shop.domainDto.ShoppingCartDto;
import org.springframework.stereotype.Component;


@Component
public class ShoppingCartMapper {

    public ShoppingCart shoppingCartDtoToShoppingCart(ShoppingCartDto shoppingCartDto) {
        return new ShoppingCart(
                shoppingCartDto.getId(),
                shoppingCartDto.getUsers(),
                shoppingCartDto.getProducts(),
                shoppingCartDto.getTest()
        );
    }

    public ShoppingCartDto shoppingCartToShoppingCartDto(ShoppingCart shoppingCart) {
        return new ShoppingCartDto(
                shoppingCart.getId(),
                shoppingCart.getUsers(),
                shoppingCart.getProducts(),
                shoppingCart.getTest()
        );
    }
}
