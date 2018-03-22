package com.shop.controller;

import com.shop.mapper.ProductsMapper;
import com.shop.mapper.ShoppingCartMapper;
import com.shop.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/Cart")
public class ShoppingCartController {
    @Autowired
    private DbService service;

    @Autowired
    private ProductsMapper productsMapper;

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @RequestMapping(method = RequestMethod.PUT, value="/addToShoppingCart/{cartId}/{productId}")
    public void addProductToShoppingCart(@PathVariable Long cartId, @PathVariable Long productId) {
        service.saveShoppingCart(shoppingCartMapper.shoppingCartDtoToShoppingCart(service.saveProductsInShoppingCart(cartId, productId)));
    }
}
