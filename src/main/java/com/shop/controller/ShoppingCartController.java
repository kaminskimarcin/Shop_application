package com.shop.controller;

import com.shop.domain.Users;
import com.shop.domainDto.ShoppingCartDto;
import com.shop.mapper.ShoppingCartMapper;
import com.shop.service.ShoppingCartService;
import com.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.PUT, value="/user/addToShoppingCart/{cartId}/{productId}")
    public void addProductToShoppingCart(@PathVariable Long cartId, @PathVariable Long productId) {
        shoppingCartService.saveShoppingCart(shoppingCartService.saveProductsInShoppingCart(cartId, productId));
    }

    @RequestMapping(method = RequestMethod.GET, value="/user/getShoppingCart")
    public ShoppingCartDto getCart() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Users user = userService.getUserByUsername(name);
        return shoppingCartMapper.shoppingCartToShoppingCartDto(shoppingCartService.getShoppingCartByUser(user.getId()));
    }
}
