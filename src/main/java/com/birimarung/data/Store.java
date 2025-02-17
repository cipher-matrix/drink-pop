package com.birimarung.data;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String store_name;
    private boolean isActive;


    @OneToMany(mappedBy = "storeId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> products = new ArrayList<>();

    private long productsSize;


    @PrePersist
    @PreUpdate
    public void updateProductSize() {
        this.productsSize = (products != null) ? products.size() : 0;
    }
}
