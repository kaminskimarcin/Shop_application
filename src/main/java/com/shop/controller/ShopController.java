package com.shop.controller;

import com.shop.domainDto.ProductsDto;
import com.shop.mapper.ProductsMapper;
import com.shop.service.ProductService;
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

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public List<ProductsDto> getAllProducts() {
        return productsMapper.mapToProductsDto(productService.getAllProducts());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/sortedByName")
    public List<ProductsDto> getAllSortedProductsByName() {
        return productService.getAllProductsSortedByName();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/byCategory")
    public List<ProductsDto> getAllProductsByCategory(@RequestParam final String category) {
        return productsMapper.mapToProductsDto(productService.getAllProductsByCategory(category));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/", consumes = APPLICATION_JSON_VALUE)
    public void createTask(@RequestBody ProductsDto productsDto) {
        productService.saveProduct(productsMapper.productsDtoToProducts(productsDto));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deleteTask(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ProductsDto getProductById(@PathVariable Long id) {
        return productsMapper.productsToProductsDto(productService.getById(id));

    }
}
