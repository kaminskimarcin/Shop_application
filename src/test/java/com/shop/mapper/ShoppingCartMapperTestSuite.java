package com.shop.mapper;

import com.shop.domain.Products;
import com.shop.domain.ShoppingCart;
import com.shop.domain.Users;
import com.shop.domainDto.ShoppingCartDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShoppingCartMapperTestSuite {
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Test
    public void testCartToCartDto() {
        //Given
        Users users = new Users(1L, "TestUserName", "TestUserPassword", "USER");
        List<Products> productsList = new ArrayList<>();
        Products products = new Products(1L, "TestProductName", 20.00, new ArrayList<>());
        productsList.add(products);
        ShoppingCart shoppingCart = new ShoppingCart(1L, users, productsList);
        //When
        ShoppingCartDto shoppingCartDto = shoppingCartMapper.shoppingCartToShoppingCartDto(shoppingCart);
        Long id = shoppingCartDto.getId();
        String productName = shoppingCartDto.getProducts().get(0).getName();
        String userName = shoppingCartDto.getUsers().getName();
        String userPassword = shoppingCartDto.getUsers().getPassword();
        double productPrice = shoppingCartDto.getProducts().get(0).getPrice();
        //Then
        assertEquals(new Long(1), id);
        assertEquals("TestProductName", productName);
        assertEquals("TestUserName", userName);
        assertEquals("TestUserPassword", userPassword);
        assertEquals(20.00, productPrice, 0);
    }

    @Test
    public void testCartDtoToCart() {
        //Given
        Users users = new Users(1L, "TestUserName", "TestUserPassword", "USER");
        List<Products> productsList = new ArrayList<>();
        Products products = new Products(1L, "TestProductName", 20.00, new ArrayList<>());
        productsList.add(products);
        ShoppingCartDto shoppingCartDto = new ShoppingCartDto(1L, users, productsList);
        //When
        ShoppingCart shoppingCart = shoppingCartMapper.shoppingCartDtoToShoppingCart(shoppingCartDto);
        Long id = shoppingCart.getId();
        String productName = shoppingCart.getProducts().get(0).getName();
        String userName = shoppingCart.getUsers().getName();
        String userPassword = shoppingCart.getUsers().getPassword();
        double productPrice = shoppingCart.getProducts().get(0).getPrice();
        //Then
        assertEquals(new Long(1), id);
        assertEquals("TestProductName", productName);
        assertEquals("TestUserName", userName);
        assertEquals("TestUserPassword", userPassword);
        assertEquals(20.00, productPrice, 0);
    }
}
