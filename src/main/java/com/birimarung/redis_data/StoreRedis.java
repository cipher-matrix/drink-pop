package com.birimarung.redis_data;

import com.birimarung.data.Product;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@RedisHash(value = "store_redis")
@Getter
@Setter
public class StoreRedis implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private int id;
    private String store_name;
    private boolean isActive;
    private List<Product> products = new ArrayList<>();
    private long productsSize;
}
