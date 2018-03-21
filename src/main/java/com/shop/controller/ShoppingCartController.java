package com.shop.controller;

import com.shop.domain.Products;
import com.shop.domain.ShoppingCart;
import com.shop.domain.Users;
import com.shop.domainDto.ProductsDto;
import com.shop.domainDto.ShoppingCartDto;
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

    @RequestMapping(method = RequestMethod.PUT, value="/addToShoppingCart/{productId}/{userId}")
    public ShoppingCartDto addProductToShoppingCart(@PathVariable Long userId, Long productId) {
        return service.saveProductsInShoppingCart(userId, productId);
    }
}
