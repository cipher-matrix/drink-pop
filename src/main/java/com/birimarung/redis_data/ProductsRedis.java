package com.birimarung.redis_data;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serial;
import java.io.Serializable;

@RedisHash(value = "products_redis")
@Getter
@Setter
public class ProductsRedis implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L; // It's good practice to add this
    private int id;
    private String drinkName;
    private int storeId;
    private double price;
    private String imageUrl;
    private String description;
    private String specialPrice;
    private boolean isDrinkOnSpecial;
    private String storeName;
}
