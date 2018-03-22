package com.shop.mapper;

import com.shop.domain.Products;
import com.shop.domainDto.ProductsDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductsMapper {

    public Products productsDtoToProducts(final ProductsDto productsDto) {
        return new Products(
                productsDto.getId(),
                productsDto.getName(),
                productsDto.getPrice());
    }

    public ProductsDto productsToProductsDto(final Products products) {
        return new ProductsDto(
                products.getId(),
                products.getName(),
                products.getPrice(),
                products.getShoppingCarts());
    }

    public List<ProductsDto> mapToProductsDtoList(final List<Products> productsList) {
        return productsList.stream()
                .map(t -> new ProductsDto(t.getId(), t.getName(), t.getPrice(), t.getShoppingCarts()))
                .collect(Collectors.toList());
    }
}
