package com.shop.mapper;

import com.shop.domain.Products;
import com.shop.domainDto.ProductsDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductsMapperTestSuite {
    @Autowired
    private ProductsMapper productsMapper;

    @Test
    public void testProductsToProductsDto() {
        //Given
        Products products = new Products(1L, "TestName", 20.00, new ArrayList<>(), "Test");
        //When
        ProductsDto productsDto = productsMapper.productsToProductsDto(products);
        Long id = productsDto.getId();
        String name = productsDto.getName();
        double price = productsDto.getPrice();
        //Then
        assertEquals(new Long(1), id);
        assertEquals("TestName", name);
        assertEquals(20.00, price, 0);
    }

    @Test
    public void testProductsDtoToProducts() {
        //Given
        ProductsDto productsDto = new ProductsDto(1L, "TestName", 20.00, "Test");
        //When
        Products products = productsMapper.productsDtoToProducts(productsDto);
        Long id = products.getId();
        String name = products.getName();
        double price = products.getPrice();
        //Then
        assertEquals(new Long(1), id);
        assertEquals("TestName", name);
        assertEquals(20.00, price, 0);
    }
}
