package com.shop.controller;

import com.shop.domainDto.ProductsDto;
import com.shop.mapper.ProductsMapper;
import com.shop.service.ProductService;
import com.shop.service.ProductsFilterService;
import com.shop.service.ProductsSortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequestMapping("/")
public class ShopController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductsMapper productsMapper;

    @Autowired
    private ProductsSortService productsSortService;

    @Autowired
    private ProductsFilterService productsFilterService;

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public List<ProductsDto> getAllProducts() {
        return productsMapper.mapToProductsDto(productService.getAllProducts());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/sortedByName")
    public List<ProductsDto> getAllSortedProductsByName() {
        return productsSortService.getAllProductsSortedByName();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/sortedByCategory")
    public List<ProductsDto> getAllSortedProductsByCategory() {
        return productsSortService.getAllProductsSortedByCategory();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/sortedByPrice")
    public List<ProductsDto> getAllProductsSortedByPrice() {
        return productsSortService.getAllProductsSortedByPrice();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/sorted")
    public List<ProductsDto> getAllProductsWithCategorySortedByName(@RequestParam final String category) {
        return productsFilterService.getAllProductsWithCategorySortedByName(category);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/byCategory")
    public List<ProductsDto> getAllProductsByCategory(@RequestParam final String category) {
        return productsMapper.mapToProductsDto(productsFilterService.getAllProductsByCategory(category));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/filterByName")
    public List<ProductsDto> getAllProductsWithName(@RequestParam final String name) {
        return productsMapper.mapToProductsDto(productsFilterService.getAllProductsByName(name));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/filterByPrice")
    public List<ProductsDto> getAllProductsWithPrice(@RequestParam final Double startPrice, @RequestParam final Double endPrice) {
        return productsMapper.mapToProductsDto(productsFilterService.getAllProductsByPrice(startPrice, endPrice));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ProductsDto getProductById(@PathVariable Long id) {
        return productsMapper.productsToProductsDto(productService.getById(id));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/", consumes = APPLICATION_JSON_VALUE)
    public void createTask(@RequestBody ProductsDto productsDto) {
        productService.saveProduct(productsMapper.productsDtoToProducts(productsDto));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deleteTask(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
