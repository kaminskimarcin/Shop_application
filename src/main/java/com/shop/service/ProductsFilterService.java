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
    private final ProductsRepository productsRepository;

    private final ProductsMapper productsMapper;

    private final ProductsSortService productsSortService;

    @Autowired
    public ProductsFilterService(ProductsRepository productsRepository, ProductsMapper productsMapper, ProductsSortService productsSortService) {
        this.productsRepository = productsRepository;
        this.productsMapper = productsMapper;
        this.productsSortService = productsSortService;
    }

    private List<ProductsDto> filterByPrice(List<ProductsDto> productsDtoList, Double startPrice, Double endPrice) {
        if (startPrice == null) {
            startPrice = 0.00;
        }
        if (endPrice == null) {
            endPrice = 160000.00;
        }

        Double finalStartPrice = startPrice;
        Double finalEndPrice = endPrice;

        return productsDtoList.stream()
                .filter(k -> k.getPrice() > finalStartPrice - 0.01)
                .filter(k -> k.getPrice() < finalEndPrice + 0.01)
                .collect(Collectors.toList());
    }

    private List<ProductsDto> filterByCategory(List<ProductsDto> productsDtoList, String category) {
        return productsDtoList.stream()
                .filter(k -> k.getCategory().contains(category))
                .collect(Collectors.toList());

    }

    private List<ProductsDto> filterByName(List<ProductsDto> productsDtoList, String name) {
        if (name == null) {
            return productsDtoList;
        } else {
            return productsDtoList.stream()
                    .filter(k -> k.getName().contains(name))
                    .collect(Collectors.toList());
        }
    }

    public List<ProductsDto> getFilteredProducts(final String name, final String category, final Double startPrice, final Double endPrice) {
        List<ProductsDto> productslist = productsMapper.mapToProductsDto(productsRepository.findAll());
        if (category != null) {
            productslist = filterByCategory(productslist, category);
        }
        if (name != null) {
            productslist = filterByName(productslist, name);
        }
        if (startPrice == null || endPrice == null) {
            //do nothing
        } else {
            productslist = filterByPrice(productslist, startPrice, endPrice);
        }
        return productslist;
    }

    public List<ProductsDto> getFilteredAndSortedProducts(final String name, final String category, final Double startPrice, final Double endPrice, final String sortType) {
        List<ProductsDto> productsDtos = getFilteredProducts(name, category, startPrice, endPrice);
        return productsSortService.sortProducts(productsDtos, sortType);
    }
}
