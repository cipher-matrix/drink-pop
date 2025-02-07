package com.birimarung.data;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

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
    @ColumnDefault(value = "0")

    @OneToMany(mappedBy = "storeId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> products;

    private long productsSize;


    @PrePersist
    @PreUpdate
    public void updateProductSize() {
        this.productsSize = (products != null) ? products.size() : 0;
    }
}
