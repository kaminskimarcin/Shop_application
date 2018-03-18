package com.shop.controller;

import com.shop.domain.Products;
import com.shop.domain.ShoppingCart;
import com.shop.domain.Users;
import com.shop.domainDto.ProductsDto;
import com.shop.mapper.ProductsMapper;
import com.shop.repository.ShoppingCartRepository;
import com.shop.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/ShoppingCart")
public class ShoppingCartController {
    @Autowired
    private DbService service;

    @Autowired
    private ProductsMapper productsMapper;

    private ShoppingCart shoppingCart;

    private Users users;

    @RequestMapping(method = RequestMethod.POST, value="/addToShoppingCart")
    public void addProductToShoppingCart(@PathVariable Long id, ProductsDto productsDto) {
        if(shoppingCart.getId().equals(users.getId())) {
            shoppingCart.getProducts().add(productsMapper.productsDtoToProducts(productsDto));
        } else {
            new ShoppingCart();
        }
    }
}
