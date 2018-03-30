package com.shop.controller;

import com.shop.domain.ShoppingCart;
import com.shop.domainDto.ShoppingCartView;
import com.shop.mapper.ProductsMapper;
import com.shop.mapper.ShoppingCartMapper;
import com.shop.repository.ShoppingCartRepository;
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
    private ProductsMapper productsMapper;

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @RequestMapping(method = RequestMethod.PUT, value="/addToShoppingCart/{cartId}/{productId}")
    public void addProductToShoppingCart(@PathVariable Long cartId, @PathVariable Long productId) {
        shoppingCartService.saveShoppingCart(shoppingCartMapper.shoppingCartDtoToShoppingCart(shoppingCartService.saveProductsInShoppingCart(cartId, productId)));
    }

    @RequestMapping(method = RequestMethod.GET, value="/getShoppingCart/{id}")
    public ShoppingCartView getCart(@PathVariable final Long id) {
        return shoppingCartMapper.shoppingCartToShoppingCartView(shoppingCartService.getShoppingCartByUser(id));
    }
}
