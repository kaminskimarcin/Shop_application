package com.shop.controller;

import com.shop.domainDto.ShoppingCartDto;
import com.shop.mapper.ShoppingCartMapper;
import com.shop.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/Cart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @RequestMapping(method = RequestMethod.PUT, value="/addToShoppingCart/{cartId}/{productId}")
    public void addProductToShoppingCart(@PathVariable Long cartId, @PathVariable Long productId) {
        shoppingCartService.saveShoppingCart(shoppingCartService.saveProductsInShoppingCart(cartId, productId));
    }

    @RequestMapping(method = RequestMethod.GET, value="/getShoppingCart/{id}")
    public ShoppingCartDto getCart(@PathVariable final Long id) {
        return shoppingCartMapper.shoppingCartToShoppingCartDto(shoppingCartService.getShoppingCartByUser(id));
    }
}
