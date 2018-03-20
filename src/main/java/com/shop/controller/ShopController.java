package com.shop.controller;

import com.shop.domainDto.ProductsDto;
import com.shop.domainDto.UsersDto;
import com.shop.mapper.ProductsMapper;
import com.shop.mapper.UsersMapper;
import com.shop.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequestMapping("/")
public class ShopController {
    @Autowired
    private DbService service;

    @Autowired
    private ProductsMapper productsMapper;

    @Autowired
    private UsersMapper usersMapper;

    @RequestMapping(method = RequestMethod.GET, value="/products")
    public List<ProductsDto> getAllProducts() {
        return productsMapper.mapToProductsDtoList(service.getAllProducts());
    }

    //Just for preparing data
    @RequestMapping(method = RequestMethod.POST, value="/addProduct", consumes = APPLICATION_JSON_VALUE)
    public void createTask(@RequestBody ProductsDto productsDto) {
        service.saveProduct(productsMapper.productsDtoToProducts(productsDto));
    }
    //Cleaning
    @RequestMapping(method = RequestMethod.DELETE, value="/{id}")
    public void deleteTask(@PathVariable Long id) {
        service.deleteProduct(id);
    }
}
