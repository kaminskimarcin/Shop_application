package com.shop.domainDto;

import com.shop.domain.Products;
import com.shop.domain.Users;
import com.shop.mapper.UsersMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartDto {
    private Long id;
    private Users users;
    private List<Products> products;

    public ShoppingCartDto(Users users, List<Products> products) {
        this.users = users;
        this.products = products;
    }
}
