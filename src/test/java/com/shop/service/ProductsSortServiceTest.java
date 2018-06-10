package com.shop.service;

import com.shop.domain.Products;
import com.shop.domainDto.ProductsDto;
import com.shop.repository.ProductsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductsSortServiceTest {

    @Autowired
    private ProductsSortService productsSortService;

    @SpyBean
    private ProductService productService;

    @Test
    public void testSortProducts() {
        //Given
        List<ProductsDto> productsDtoList = new ArrayList<>();
        ProductsDto productOne = new ProductsDto(1L, "ATest", 100.00, "ACategory");
        ProductsDto productTwo = new ProductsDto(2L, "CTest", 10000.00, "CCategory");
        ProductsDto productThree = new ProductsDto(3L, "BTest", 500.00, "BCategory");

        productsDtoList.add(productOne);
        productsDtoList.add(productTwo);
        productsDtoList.add(productThree);
        //When
        List<ProductsDto> resultSortedByCategory = productsSortService.sortProducts(productsDtoList, "category");
        List<ProductsDto> resultSortedByPrice = productsSortService.sortProducts(productsDtoList, "price");
        List<ProductsDto> resultSortedByName = productsSortService.sortProducts(productsDtoList, "name");
        //Then
        assertEquals("BCategory", resultSortedByCategory.get(1).getCategory());
        assertEquals(500.00, resultSortedByPrice.get(1).getPrice(), 0.0001);
        assertEquals("BTest", resultSortedByName.get(1).getName());
    }

    @Test
    public void testGetSortedProducts() {
        //Given
        List<Products> productsList = new ArrayList<>();
        Products productOne = new Products(1L, "ATest", 100.00, "ACategory");
        Products productTwo = new Products(2L, "CTest", 10000.00, "CCategory");
        Products productThree = new Products(3L, "BTest", 500.00, "BCategory");

        productsList.add(productOne);
        productsList.add(productTwo);
        productsList.add(productThree);

        when(productService.getAllProducts()).thenReturn(productsList);

        //When
        List<ProductsDto> resultSortedByCategory = productsSortService.getSortedProducts("category");
        List<ProductsDto> resultSortedByPrice = productsSortService.getSortedProducts("price");
        List<ProductsDto> resultSortedByName = productsSortService.getSortedProducts("name");
        //Then
        assertEquals(3, resultSortedByCategory.size());
        assertEquals(3, resultSortedByName.size());
        assertEquals(3, resultSortedByPrice.size());
        assertEquals("BTest", resultSortedByName.get(1).getName());
        assertEquals("BCategory", resultSortedByCategory.get(1).getCategory());
        assertEquals(500.00, resultSortedByPrice.get(1).getPrice(), 0.0001);
    }
}