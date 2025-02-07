package com.birimarung.data;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;


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
    private long productsSize;
}
