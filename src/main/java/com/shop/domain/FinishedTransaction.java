package com.shop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "FINISHED_TRANSACTIONS")
public class FinishedTransaction {
    private Long id;
    private Long shoppingCart_Id;

    @Id
    @GeneratedValue
    @Column(name = "Finished_Transaction_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setShoppingCart_Id(Long shoppingCart_Id) {
        this.shoppingCart_Id = shoppingCart_Id;
    }

    @Column(name = "Shopping_cart_id")
    public Long getShoppingCart_Id() {
        return shoppingCart_Id;
    }
}
