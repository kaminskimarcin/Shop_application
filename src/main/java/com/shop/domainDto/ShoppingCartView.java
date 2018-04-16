package com.shop.domainDto;

import com.shop.domain.Products;
import com.shop.domain.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartView {
    private Long id;
    private Long userId;
    private List<ProductsDto> productsDtos;
    private double price;
}
