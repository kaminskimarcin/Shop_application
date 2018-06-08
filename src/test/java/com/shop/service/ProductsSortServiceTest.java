package com.shop.service;

import com.shop.domainDto.ProductsDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductsSortServiceTest {

    @Autowired
    private ProductsSortService productsSortService;

    @Test
    public void testSortProducts() {
        //Given
        List<ProductsDto> productsDtoList = new ArrayList<>();
        ProductsDto productOne = new ProductsDto(1L, "ATest", 100.00, "ACategory");
        ProductsDto productTwo = new ProductsDto(2L, "BTest", 500.00, "BCategory");
        ProductsDto productThree = new ProductsDto(3L, "CTest", 10000.00, "CCategory");

        productsDtoList.add(productOne);
        productsDtoList.add(productTwo);
        productsDtoList.add(productThree);
        //When
        List<ProductsDto> resultSortedByCategory = productsSortService.sortProducts(productsDtoList, "category");
        List<ProductsDto> resultSortedByPrice = productsSortService.sortProducts(productsDtoList, "price");
        List<ProductsDto> resultSortedByName = productsSortService.sortProducts(productsDtoList, "name");
        //Then
        assertEquals("ACategory", resultSortedByCategory.get(0).getCategory());
        assertEquals(100.00, resultSortedByPrice.get(0).getPrice(), 0.0001);
        assertEquals("ATest", resultSortedByName.get(0).getName());
    }
}