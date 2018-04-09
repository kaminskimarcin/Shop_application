package com.shop.mapper;

import com.shop.domain.ShoppingCart;
import com.shop.domainDto.ShoppingCartDto;
import com.shop.domainDto.ShoppingCartView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ShoppingCartMapper {
    @Autowired
    private ProductsMapper productsMapper;

    public ShoppingCart shoppingCartDtoToShoppingCart(ShoppingCartDto shoppingCartDto) {
        return new ShoppingCart(
                shoppingCartDto.getId(),
                shoppingCartDto.getUsers(),
                shoppingCartDto.getProducts(),
                shoppingCartDto.getCartStatus()
        );
    }

    public ShoppingCartDto shoppingCartToShoppingCartDto(ShoppingCart shoppingCart) {
        return new ShoppingCartDto(
                shoppingCart.getId(),
                shoppingCart.getUsers(),
                shoppingCart.getProducts(),
                shoppingCart.getCartStatus()
        );
    }

    public ShoppingCartView shoppingCartToShoppingCartView(ShoppingCart shoppingCart) {
        return new ShoppingCartView(
                shoppingCart.getId(),
                shoppingCart.getUsers().getId(),
                productsMapper.mapToProductsViewList(shoppingCart.getProducts()),
                shoppingCart.getProducts().stream()
                        .mapToDouble(k -> k.getPrice()).sum());
    }
}
