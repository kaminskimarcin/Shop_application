package com.shop.service;

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
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UsersMapper usersMapper;

    public ShoppingCart saveProductsInShoppingCart(final Long userId, final Long productId) {
        ShoppingCart shoppingCart = shoppingCartRepository.getByUsers(userService.getUser(userId));
        shoppingCart.getProducts().add(productService.getById(productId));
        productService.updateProducts(shoppingCart.getId(), productId);
        return shoppingCart;
    }

    public ShoppingCart saveShoppingCart(final ShoppingCart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }

    public ShoppingCart getShoppingCartByUser(final Long id) {
        UsersDto usersDto = usersMapper.usersToUsersDto(userService.getUser(id));
        return shoppingCartRepository.getByUsers(usersMapper.usersDtoToUsers(usersDto));
    }
}
