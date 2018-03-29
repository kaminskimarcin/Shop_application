package com.shop.mapper;

import com.shop.domain.Products;
import com.shop.domainDto.ProductsDto;
import com.shop.domainDto.ProductsView;
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
public class ProductsMapperTestSuite {
    @Autowired
    private ProductsMapper productsMapper;

    @Test
    public void testProductsToProductsDto() {
        //Given
        Products products = new Products(1L, "TestName", 20.00, new ArrayList<>());
        //When
        ProductsDto productsDto = productsMapper.productsToProductsDto(products);
        Long id = productsDto.getId();
        String name = productsDto.getName();
        double price = productsDto.getPrice();
        int size = productsDto.getShoppingCart().size();
        //Then
        assertEquals(new Long(1), id);
        assertEquals("TestName", name);
        assertEquals(20.00, price, 0);
        assertEquals(0, size);
    }

    @Test
    public void testProductsDtoToProducts() {
        //Given
        ProductsDto productsDto = new ProductsDto(1L, "TestName", 20.00, new ArrayList<>());
        //When
        Products products = productsMapper.productsDtoToProducts(productsDto);
        Long id = products.getId();
        String name = products.getName();
        double price = products.getPrice();
        int size = products.getShoppingCarts().size();
        //Then
        assertEquals(new Long(1), id);
        assertEquals("TestName", name);
        assertEquals(20.00, price, 0);
        assertEquals(0, size);
    }

    @Test
    public void testProductsListToProductsViewList() {
        //Given
        List<Products> productsList = new ArrayList<>();
        Products productOne = new Products(1L, "TestName", 20.00, new ArrayList<>());
        Products productTwo = new Products(2L, "TestNameTwo", 25.00, new ArrayList<>());
        productsList.add(productOne);
        productsList.add(productTwo);
        //When
        List<ProductsView> productsViewList = productsMapper.mapToProductsViewList(productsList);
        int size = productsViewList.size();
        String productOneName = productsViewList.get(0).getName();
        double productOnePrice = productsViewList.get(0).getPrice();
        String productTwoName = productsViewList.get(1).getName();
        double productTwoPrice = productsViewList.get(1).getPrice();
        //Then
        assertEquals(2, size);
        assertEquals("TestName", productOneName);
        assertEquals("TestNameTwo", productTwoName);
        assertEquals(20.00, productOnePrice, 0);
        assertEquals(25.00, productTwoPrice, 0);
    }
}
