package com.birimarung.data;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;



@Entity
@Table(name = "product")
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(columnDefinition = "TEXT", name = "drink_name")
    private String drinkName;
    @Column(name = "store_id")
    private int storeId;
    private double price;
    @Column(columnDefinition = "TEXT")
    private String imageUrl;
    private String description;
    private String specialPrice;
    private boolean isDrinkOnSpecial;
    private String storeName;

}
