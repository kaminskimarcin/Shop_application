package com.shop.domainDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductsDto {
    private Long id;
    private String name;
    private double price;

    public ProductsDto(String name, double price) {
        this.name = name;
        this.price = price;
    }
}
