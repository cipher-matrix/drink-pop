package com.birimarung.data;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Entity
@Getter
@Setter
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
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

}
