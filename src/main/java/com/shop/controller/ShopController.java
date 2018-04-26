package com.shop.controller;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
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

    @RequestMapping(method = RequestMethod.GET, value = "/shop")
    public List<ProductsDto> getAllProducts() {
        return productsMapper.mapToProductsDto(productService.getAllProducts());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/shop/sortProducts")
    public List<ProductsDto> getSortedProducts(@RequestParam final String sortType) {
        return productsSortService.getSortedProducts(sortType);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/shop/sortAndFilter")
    public List<ProductsDto> getSortedProducts(@RequestParam(required = false) final String name, @RequestParam(required = false) final String category, @RequestParam(required = false) final Double startPrice, @RequestParam(required = false) final Double endPrice, @RequestParam(required = false) final String sortType) {
        return productsFilterService.getFilteredAndSortedProducts(name, category, startPrice, endPrice, sortType);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/shop/filter")
    public List<ProductsDto> getSortedProducts(@RequestParam(required = false) final String name, @RequestParam(required = false) final String category, @RequestParam(required = false) final Double startPrice, @RequestParam(required = false) final Double endPrice) {
        return productsFilterService.getFilteredProducts(name, category, startPrice, endPrice);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/shop/{id}")
    public ProductsDto getProductById(@PathVariable Long id) {
        return productsMapper.productsToProductsDto(productService.getById(id));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/admin", consumes = APPLICATION_JSON_VALUE)
    public void createProduct(@RequestBody ProductsDto productsDto) {
        productService.saveProduct(productsMapper.productsDtoToProducts(productsDto));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/admin/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
