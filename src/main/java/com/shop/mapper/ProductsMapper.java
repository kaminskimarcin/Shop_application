package com.shop.mapper;

import com.shop.domain.Products;
import com.shop.domainDto.ProductsDto;
import com.shop.domainDto.ProductsView;
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

    public ProductsView productsToProductsView(final Products products) {
        return new ProductsView(
                products.getId(),
                products.getName(),
                products.getPrice());
    }

    public List<ProductsView> mapToProductsViewList(final List<Products> productsList) {
        return productsList.stream()
                .map(t -> new ProductsView(t.getId(), t.getName(), t.getPrice()))
                .collect(Collectors.toList());
    }
}
