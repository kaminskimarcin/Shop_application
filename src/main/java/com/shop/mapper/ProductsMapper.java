package com.shop.mapper;

import com.shop.domain.Products;
import com.shop.domainDto.ProductsDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductsMapper {

    public Products productsDtoToProducts(final ProductsDto productsDto) {
        return new Products(
                productsDto.getId(),
                productsDto.getName(),
                productsDto.getPrice(),
                productsDto.getCategory()
        );
    }

    public ProductsDto productsToProductsDto(final Products products) {
        return new ProductsDto(
                products.getId(),
                products.getName(),
                products.getPrice(),
                products.getCategory()
        );
    }

    public List<ProductsDto> mapToProductsDto(final List<Products> productsList) {
        return productsList.stream()
                .map(t -> new ProductsDto(t.getId(), t.getName(), t.getPrice(), t.getCategory()))
                .collect(Collectors.toList());
    }

    public Page<ProductsDto> mapToPageProductsDto(final Page<Products> productsPage) {
        return productsPage.map(this::productsToProductsDto);
    }

    public List<Products> mapToProducts(final List<ProductsDto> productsDtoList) {
        return productsDtoList.stream()
                .map(t -> new Products(t.getId(), t.getName(), t.getPrice(), t.getCategory()))
                .collect(Collectors.toList());
    }
}
