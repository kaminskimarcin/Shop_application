package com.shop.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "SHOPPING_CARTS")
public class ShoppingCart {
    private Long id;
    private Users users;
    private List<Products> products = new ArrayList<>();
    private String cartStatus = "active";
    private double cartValue = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SHOPPING_CART_ID")
    public Long getId() {
        return id;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "USERS_ID")
    public Users getUsers() {
        return users;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "JOIN_PRODUCTS_CARTS",
            joinColumns = {@JoinColumn(name = "SHOPPING_CART_ID", referencedColumnName = "SHOPPING_CART_ID")},
            inverseJoinColumns = {@JoinColumn(name = "PRODUCTS_ID", referencedColumnName = "PRODUCTS_ID")}
    )
    public List<Products> getProducts() {
        return products;
    }

    @Column(name = "STATUS")
    public String getCartStatus() {
        return cartStatus;
    }

    @Column(name = "CART_VALUE")
    public double getCartValue() {
        return cartValue;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }

    public void setCartStatus(String cartStatus) {
        this.cartStatus = cartStatus;
    }

    public void setCartValue(double cartValue) {
        this.cartValue = cartValue;
    }
}
