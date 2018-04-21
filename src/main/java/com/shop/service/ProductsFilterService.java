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

    public List<ProductsDto> filterByPrice(List<ProductsDto> productsDtoList, Double startPrice, Double endPrice) {
        if (startPrice == null) {
            startPrice = 0.00;
        }
        if (endPrice == null) {
            endPrice = 160000.00;
        }

        if (startPrice == null || endPrice == null) {
            return productsDtoList;
        }

        Double finalStartPrice = startPrice;
        Double finalEndPrice = endPrice;

        return productsDtoList.stream()
                .filter(k -> k.getPrice() > finalStartPrice - 0.01)
                .filter(k -> k.getPrice() < finalEndPrice + 0.01)
                .collect(Collectors.toList());
    }

    public List<ProductsDto> filterByCategory(List<ProductsDto> productsDtoList, String category) {
        if(category == null) {
            return productsDtoList;
        } else {
            return productsDtoList = productsDtoList.stream()
                    .filter(k -> k.getCategory().contains(category))
                    .collect(Collectors.toList());
        }
    }

    public List<ProductsDto> filterByName(List<ProductsDto> productsDtoList, String name) {
        if(name == null) {
            return productsDtoList;
        } else {
            return productsDtoList = productsDtoList.stream()
                    .filter(k -> k.getName().contains(name))
                    .collect(Collectors.toList());
        }
    }

    public List<Products> getAllProductsByCategory(final String category) {
        return productsRepository.findAllByCategory(category);
    }

    public List<Products> getAllProductsByName(final String name) {
        return productsRepository.findAllByName(name);
    }

    public List<Products> getAllProductsByPrice(final double startPrice, final double endPrice) {
        List<ProductsDto> listToSort = productsMapper.mapToProductsDto(productsRepository.findAll());
        List<ProductsDto> sortedList = listToSort.stream()
                .filter(k -> k.getPrice() > startPrice - 0.01)
                .filter(k -> k.getPrice() < endPrice + 0.01)
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

    public List<ProductsDto> getFilteredProducts(final String name, final String category, final Double startPrice, final Double endPrice) {
        List<ProductsDto> productslist = productsMapper.mapToProductsDto(productsRepository.findAll());
        productslist = filterByCategory(productslist, category);
        productslist = filterByName(productslist, name);
        productslist = filterByPrice(productslist, startPrice, endPrice);
        return productslist;
    }
}
