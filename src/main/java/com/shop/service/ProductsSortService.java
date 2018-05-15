package com.shop.service;

import com.shop.domainDto.ProductsDto;
import com.shop.mapper.ProductsMapper;
import com.shop.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProductsSortService {
    private final ProductsMapper productsMapper;

    private final ProductsRepository productsRepository;

    @Autowired
    public ProductsSortService(ProductsMapper productsMapper, ProductsRepository productsRepository) {
        this.productsMapper = productsMapper;
        this.productsRepository = productsRepository;
    }

    List<ProductsDto> sortProducts(List<ProductsDto> productsDtoList, String sortedBy) {
        switch (sortedBy) {
            case "category":
                productsDtoList.sort(ProductsDto.categoryComparator);
                return productsDtoList;
            case "name":
                productsDtoList.sort(ProductsDto.nameComparator);
                return productsDtoList;
            case "price":
                productsDtoList.sort(ProductsDto.priceComparator);
                return productsDtoList;
            default:
                return productsDtoList;
        }
    }

    public List<ProductsDto> getSortedProducts(final String sortType) {
        List<ProductsDto> productsDtos = productsMapper.mapToProductsDto(productsRepository.findAll());
        sortProducts(productsDtos, sortType);
        return productsDtos;
    }
}
