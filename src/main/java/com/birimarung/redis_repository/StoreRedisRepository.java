package com.birimarung.redis_repository;

import com.birimarung.redis_data.StoreRedis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRedisRepository extends CrudRepository<StoreRedis, Integer> {
}
