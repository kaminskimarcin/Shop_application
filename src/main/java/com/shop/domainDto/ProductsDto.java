package com.shop.domainDto;

import com.shop.domain.ShoppingCart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductsDto {
    private Long id;
    private String name;
    private double price;
    private List<ShoppingCart> shoppingCart = new ArrayList<>();

    public ProductsDto(String name, double price) {
        this.name = name;
        this.price = price;
    }
}
