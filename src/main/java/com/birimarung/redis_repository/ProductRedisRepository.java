package com.birimarung.redis_repository;

import com.birimarung.redis_data.ProductsRedis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRedisRepository extends CrudRepository<ProductsRedis, String> {
}
