package com.birimarung.redis_data;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "drinks_redis")
@Getter
@Setter
public class DrinksRedis {
    private int id;
    private String drinkName;
    private boolean isDrinkPubliclyAvailable;
}
