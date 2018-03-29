package com.shop.controller;

import com.shop.domainDto.ProductsDto;
import com.shop.domainDto.ProductsView;
import com.shop.mapper.ProductsMapper;
import com.shop.mapper.UsersMapper;
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

    @RequestMapping(method = RequestMethod.GET, value = "/products")
    public List<ProductsView> getAllProducts() {
        return productsMapper.mapToProductsViewList(productService.getAllProducts());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addProduct", consumes = APPLICATION_JSON_VALUE)
    public void createTask(@RequestBody ProductsDto productsDto) {
        productService.saveProduct(productsMapper.productsDtoToProducts(productsDto));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deleteTask(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
