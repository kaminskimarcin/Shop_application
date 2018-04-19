package com.shop.service;

import com.shop.domain.Products;
import com.shop.domainDto.ProductsDto;
import com.shop.mapper.ProductsMapper;
import com.shop.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductsFilterService {
    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private ProductsMapper productsMapper;

    @Autowired
    private ProductsSortService productsSortService;

    public List<Products> getAllProductsByCategory(final String category) {
        return productsRepository.findAllByCategory(category);
    }

    public List<Products> getAllProductsByName(final String name) {
        return productsRepository.findAllByName(name);
    }

    public List<Products> getAllProductsByPrice(final double startPrice, final double endPrice) {
        List<ProductsDto> listToSort = productsMapper.mapToProductsDto(productsRepository.findAll());
        List<ProductsDto> sortedList = listToSort.stream()
                .filter(k -> k.getPrice() > startPrice-0.01)
                .filter(k -> k.getPrice() < endPrice+0.01)
                .collect(Collectors.toList());
        return productsMapper.mapToProducts(sortedList);
    }

    public List<ProductsDto> getAllProductsWithCategorySortedByName(final String category) {
        List<ProductsDto> productsList = productsSortService.getAllProductsSortedByName();
        productsList = productsList.stream()
                .filter(k -> k.getCategory().contains(category))
                .collect(Collectors.toList());
        return productsList;
    }

    public List<ProductsDto> getAllProductsWithCategorySortedByPrice(final String category) {
        List<ProductsDto> productsList = productsSortService.getAllProductsSortedByPrice();
        productsList = productsList.stream()
                .filter(k -> k.getCategory().contains(category))
                .collect(Collectors.toList());
        return productsList;
    }

    public List<ProductsDto> getAllProductsWithCategorySortedByCategory(final String category) {
        List<ProductsDto> productsList = productsSortService.getAllProductsSortedByCategory();
        productsList = productsList.stream()
                .filter(k -> k.getCategory().contains(category))
                .collect(Collectors.toList());
        return productsList;
    }

    public List<ProductsDto> getAllProductsWithNameSortedByPrice(final String name) {
        List<ProductsDto> productsList = productsSortService.getAllProductsSortedByPrice();
        productsList = productsList.stream()
                .filter(k -> k.getCategory().contains(name))
                .collect(Collectors.toList());
        return productsList;
    }

    public List<ProductsDto> getAllProductsWithNameSortedByCategory(final String name) {
        List<ProductsDto> productsList = productsSortService.getAllProductsSortedByCategory();
        productsList = productsList.stream()
                .filter(k -> k.getCategory().contains(name))
                .collect(Collectors.toList());
        return productsList;
    }

    public List<ProductsDto> getAllProductsWithNameSortedByName(final String name) {
        List<ProductsDto> productsList = productsSortService.getAllProductsSortedByName();
        productsList = productsList.stream()
                .filter(k -> k.getCategory().contains(name))
                .collect(Collectors.toList());
        return productsList;
    }

}
