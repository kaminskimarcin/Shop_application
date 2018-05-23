package com.shop.mapper;

import com.shop.domain.Products;
import com.shop.domain.ShoppingCart;
import com.shop.domainDto.ShoppingCartDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ShoppingCartMapper {
    @Autowired
    private ProductsMapper productsMapper;

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Autowired
    private UsersMapper usersMapper;

    public ShoppingCart shoppingCartDtoToShoppingCart(ShoppingCartDto shoppingCartDto) {
        return new ShoppingCart(
                shoppingCartDto.getId(),
                usersMapper.usersDtoToUsers(shoppingCartDto.getUsersDto()),
                productsMapper.mapToProducts(shoppingCartDto.getProductsDto()),
                shoppingCartDto.getCartStatus(),
                shoppingCartDto.getCartValue()
        );
    }

    public ShoppingCartDto shoppingCartToShoppingCartDto(ShoppingCart shoppingCart) {
        return new ShoppingCartDto(
                shoppingCart.getId(),
                usersMapper.usersToUsersDto(shoppingCart.getUsers()),
                productsMapper.mapToProductsDto(shoppingCart.getProducts()),
                shoppingCart.getCartStatus(),
                shoppingCart.getProducts().stream().mapToDouble(Products::getPrice).sum()
        );
    }
}
