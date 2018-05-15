package com.shop.service;

import com.shop.domain.ShoppingCart;
import com.shop.domainDto.UsersDto;
import com.shop.mapper.UsersMapper;
import com.shop.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;

    private final UserService userService;

    private final ProductService productService;

    private final UsersMapper usersMapper;

    @Autowired
    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, UserService userService, ProductService productService, UsersMapper usersMapper) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userService = userService;
        this.productService = productService;
        this.usersMapper = usersMapper;
    }

    public ShoppingCart saveProductsInShoppingCart(final Long cartId, final Long productId) {
        ShoppingCart shoppingCart = shoppingCartRepository.getById(cartId);
        shoppingCart.getProducts().add(productService.getById(productId));
        double newValue = shoppingCart.getProducts().stream().mapToDouble(k -> k.getPrice()).sum();
        shoppingCart.setCartValue(newValue);
        productService.updateProducts(cartId, productId);
        return shoppingCart;
    }

    public ShoppingCart saveShoppingCart(final ShoppingCart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }

    public ShoppingCart getShoppingCartByUser(final Long id) {
        return shoppingCartRepository.getByUsers_Id(id);
    }
}
