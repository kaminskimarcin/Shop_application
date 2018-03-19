package com.shop.domainDto;

import com.shop.domain.Products;
import com.shop.domain.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartDto {
    private Long id;
    private Users users;
    private List<Products> products = new ArrayList<>();


}
