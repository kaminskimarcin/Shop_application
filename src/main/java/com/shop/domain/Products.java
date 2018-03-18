package com.shop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "PRODUCTS")
public class Products {
    private Long id;
    private String name;
    private double price;
    private List<ShoppingCart> shoppingCarts = new ArrayList<>();

    public Products(Long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setShoppingCarts(List<ShoppingCart> shoppingCarts) {
        this.shoppingCarts = shoppingCarts;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PRODUCTS_ID")
    public Long getId() {
        return id;

    }

    @Column(name = "NAMES")
    public String getName() {
        return name;
    }

    @Column(name = "PRICES")
    public double getPrice() {
        return price;
    }

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "products")
    public List<ShoppingCart> getShoppingCarts() {
        return shoppingCarts;
    }
}
