package com.shop.domain;

import lombok.AllArgsConstructor;
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
    private String category;

    public Products(Long id, String name, double price, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
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

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "products")
    public List<ShoppingCart> getShoppingCarts() {
        return shoppingCarts;
    }

    @Column(name = "CATEGORY")
    public String getCategory() {
        return category;
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

    public void setCategory(String category) {
        this.category = category;
    }
}
