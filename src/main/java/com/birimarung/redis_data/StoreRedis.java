package com.birimarung.redis_data;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import java.util.ArrayList;
import java.util.List;

@RedisHash(value = "store_redis")
@Getter
@Setter
public class StoreRedis {
    private int id;
    private String store_name;
    private boolean isActive;

    @OneToMany(mappedBy = "storeId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductsRedis> products = new ArrayList<>();
}
