package com.shop.service;

import com.shop.controller.ProductNotFoundException;
import com.shop.domain.ShoppingCart;
import com.shop.domainDto.ShoppingCartDto;
import com.shop.domainDto.UsersDto;
import com.shop.mapper.ShoppingCartMapper;
import com.shop.mapper.UsersMapper;
import com.shop.repository.ShoppingCartRepository;
import com.shop.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Autowired
    private ProductService productService;

    @Autowired
    private UsersMapper usersMapper;

    public ShoppingCartDto saveProductsInShoppingCart(final Long cartId, final Long productId) throws ProductNotFoundException {
        ShoppingCart shoppingCart = shoppingCartRepository.getById(cartId);
        ShoppingCartDto shoppingCartDto = shoppingCartMapper.shoppingCartToShoppingCartDto(shoppingCart);
        shoppingCartDto.getProducts().add(productService.getById(productId).orElseThrow(ProductNotFoundException::new));
        productService.updateProducts(cartId, productId);
        return shoppingCartDto;
    }

    public ShoppingCart saveShoppingCart(final ShoppingCart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }

    public ShoppingCart getShoppingCartByUser(final Long id) {
        UsersDto usersDto = usersMapper.usersToUsersDto(userService.getUser(id));
        return shoppingCartRepository.getByUsers(usersMapper.usersDtoToUsers(usersDto));
    }
}
