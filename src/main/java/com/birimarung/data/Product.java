package com.birimarung.data;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;


@Entity
@Getter
@Setter
public class Product implements Serializable {
    @Id
    private String id;
    @Column(columnDefinition = "TEXT")
    private String drinkName;
    private int storeId;
    private double price;
    @Column(columnDefinition = "TEXT")
    private String imageUrl;
    private String description;
    private String specialPrice;
    private boolean isDrinkOnSpecial;
    private String storeName;

    public Product() {
        this.id = UUID.randomUUID().toString();
    }

}
