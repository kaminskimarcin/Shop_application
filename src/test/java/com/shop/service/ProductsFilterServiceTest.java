package com.shop.service;

import com.shop.domainDto.ProductsDto;
import com.shop.mapper.ProductsMapper;
import com.shop.repository.ProductsRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductsFilterServiceTest {
    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private ProductsMapper productsMapper;

    @Autowired
    private ProductsSortService productsSortService;

    @Autowired
    private ProductsFilterService productsFilterService;

    @Test
    public void testFilterByPrice() {
        //Given
        List<ProductsDto> productsDtoList = new ArrayList<>();
        ProductsDto productOne = new ProductsDto(1L, "Test1", 100.00, "TestCategory");
        ProductsDto productTwo = new ProductsDto(2L, "Test2", 500.00, "TestCategory");
        ProductsDto productThree = new ProductsDto(3L, "Test3", 10000.00, "TestCategory");

        productsDtoList.add(productOne);
        productsDtoList.add(productTwo);
        productsDtoList.add(productThree);
        //When
        List<ProductsDto> returnedList = productsFilterService.filterByPrice(productsDtoList, 200.00, 1000.00);
        //Then
        assertEquals(1, returnedList.size());
        assertEquals(productTwo, returnedList.get(0));
    }

    @Test
    public void testFilterByCategory() {
        //Given
        List<ProductsDto> productsDtoList = new ArrayList<>();
        ProductsDto productOne = new ProductsDto(1L, "Test1", 100.00, "TestCategory1");
        ProductsDto productTwo = new ProductsDto(2L, "Test2", 100.00, "TestCategory2");
        ProductsDto productThree = new ProductsDto(3L, "Test3", 100.00, "TestCategory2");
        ProductsDto productFour = new ProductsDto(4L, "Test4", 100.00, "TestCategory3");

        productsDtoList.add(productOne);
        productsDtoList.add(productTwo);
        productsDtoList.add(productThree);
        productsDtoList.add(productFour);
        //When
        List<ProductsDto> returnedList = productsFilterService.filterByCategory(productsDtoList, "TestCategory2");
        //Then
        assertEquals(2, returnedList.size());
        assertEquals(productTwo, returnedList.get(0));
        assertEquals(productThree, returnedList.get(1));
    }

    @Test
    public void testFilterByName() {
        //Given
        List<ProductsDto> productsDtoList = new ArrayList<>();
        ProductsDto productOne = new ProductsDto(1L, "Orange Juice", 100.00, "TestCategory");
        ProductsDto productTwo = new ProductsDto(2L, "Orange", 100.00, "TestCategory");
        ProductsDto productThree = new ProductsDto(3L, "Apple", 100.00, "TestCategory");
        ProductsDto productFour = new ProductsDto(4L, "Apple Juice", 100.00, "TestCategory");

        productsDtoList.add(productOne);
        productsDtoList.add(productTwo);
        productsDtoList.add(productThree);
        productsDtoList.add(productFour);
        //When
        List<ProductsDto> returnedList = productsFilterService.filterByName(productsDtoList, "Orange");
        //Then
        assertEquals(2, returnedList.size());
        assertEquals("Orange Juice", returnedList.get(0).getName());
        assertEquals("Orange", returnedList.get(1).getName());
    }

    @Test
    public void testGetFilteredProducts() {
        //Given
        List<ProductsDto> productsDtoList = new ArrayList<>();
        ProductsDto productOne = new ProductsDto(1L, "Orange Juice", 100.00, "TestCategory1");
        ProductsDto productTwo = new ProductsDto(2L, "Orange", 200.00, "TestCategory2");
        ProductsDto productThree = new ProductsDto(3L, "Apple", 300.00, "TestCategory3");
        ProductsDto productFive = new ProductsDto(5L, "Apple 1", 500.00, "TestCategory3");
        ProductsDto productSix = new ProductsDto(5L, "Apple 2", 500.00, "TestCategory3");
        ProductsDto productFour = new ProductsDto(4L, "Apple Juice", 400.00, "TestCategory4");

        productsDtoList.add(productOne);
        productsDtoList.add(productTwo);
        productsDtoList.add(productThree);
        productsDtoList.add(productFour);
        productsDtoList.add(productFive);
        productsDtoList.add(productSix);


        //When
        List<ProductsDto> returnedList = productsFilterService.getFilteredProducts("Apple", "TestCategory3", 400.00, 600.00);
        //Then
        assertEquals(2, returnedList.size());
        assertEquals(productFive, returnedList.get(0));
        assertEquals(productSix, returnedList.get(1));
    }
}