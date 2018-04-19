package com.shop.service;

import com.shop.domainDto.ProductsDto;
import com.shop.mapper.ProductsMapper;
import com.shop.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProductsSortService {
    @Autowired
    private ProductsMapper productsMapper;

    @Autowired
    private ProductsRepository productsRepository;

    public List<ProductsDto> getAllProductsSortedByName() {
        List<ProductsDto> productsDtoList = productsMapper.mapToProductsDto(productsRepository.findAll());
        Collections.sort(productsDtoList, ProductsDto.nameComparator);
        return productsDtoList;
    }

    public List<ProductsDto> getAllProductsSortedByCategory() {
        List<ProductsDto> productsDtoList = productsMapper.mapToProductsDto(productsRepository.findAll());
        Collections.sort(productsDtoList, ProductsDto.categoryComparator);
        return productsDtoList;
    }

    public List<ProductsDto> getAllProductsSortedByPrice() {
        List<ProductsDto> productsDtoList = productsMapper.mapToProductsDto(productsRepository.findAll());
        Collections.sort(productsDtoList, ProductsDto.priceComparator);
        return productsDtoList;
    }
}
