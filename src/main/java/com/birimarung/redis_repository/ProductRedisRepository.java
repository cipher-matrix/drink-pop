package com.birimarung.redis_repository;

import com.birimarung.redis_data.ProductsRedis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRedisRepository extends CrudRepository<ProductsRedis, String> {
    Optional<ProductsRedis> findProductByDrinkNameAndStoreId(String drinkName, int storeId);

    List<ProductsRedis> findProductByDrinkNameContainingIgnoreCase(String drinkName);
}
