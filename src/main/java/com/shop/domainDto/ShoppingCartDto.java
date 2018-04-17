package com.shop.domainDto;

import com.shop.domain.Products;
import com.shop.domain.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartDto {
    private Long id;
    private Users users;
    private List<ProductsDto> productsDto;
    private String cartStatus = "active";
    private double cartValue;

    public ShoppingCartDto(Users users, List<ProductsDto> products) {
        this.users = users;
        this.productsDto = products;
    }
}
