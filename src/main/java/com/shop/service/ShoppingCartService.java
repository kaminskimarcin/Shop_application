package com.shop.service;

import com.shop.domain.ShoppingCart;
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

    public ShoppingCart saveProductsInShoppingCart(final Long cartId, final Long productId) throws NullPointerException {
        ShoppingCart shoppingCart = shoppingCartRepository.getById(cartId);
        shoppingCart.getProducts().add(productService.getById(productId).orElseThrow(NullPointerException::new));
        double newValue = shoppingCart.getProducts().stream().mapToDouble(k -> k.getPrice()).sum();
        shoppingCart.setCartValue(newValue);
        productService.updateProducts(cartId, productId);
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
