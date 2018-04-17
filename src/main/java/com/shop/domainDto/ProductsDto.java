package com.shop.domainDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Comparator;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductsDto {
    private Long id;
    private String name;
    private double price;
    private String category;

    public ProductsDto(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public static Comparator<ProductsDto> nameComparator = new Comparator<ProductsDto>() {
        @Override
        public int compare(ProductsDto o1, ProductsDto o2) {
            String nameOne = o1.getName().toUpperCase();
            String nameTwo = o2.getName().toUpperCase();

            return nameOne.compareTo(nameTwo);
        }
    };

    @Override
    public String toString() {
        return "ProductsDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                '}';
    }
}
