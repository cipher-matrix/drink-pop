package com.birimarung.services;


import com.birimarung.data.Store;
import com.birimarung.dto.RestResponse;
import com.birimarung.redis_data.StoreRedis;
import com.birimarung.repository.StoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;

    @Cacheable(value = "storeCache", key = "'stores'")
    public RestResponse<List<StoreRedis>> getAllStores() {
        RestResponse<List<StoreRedis>> response = new RestResponse<>();
        List<Store> storeList = storeRepository.findAll();
        List<StoreRedis> storeRedis = new ArrayList<>();
        if (storeList.isEmpty()) {
            response.setMessage("Store List is Empty");
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
            response.setData(null);
        } else {
            for (Store store : storeList) {
                storeRedis.add(convertToStoreResponse(store));
            }
            response.setMessage("Store list retrieved successfully");
            response.setStatusCode(HttpStatus.OK.value());
            response.setData(storeRedis);
        }
        return response;
    }

    public StoreRedis convertToStoreResponse(Store store) {
        StoreRedis storeRedis = new StoreRedis();
        storeRedis.setId(store.getId());
        storeRedis.setStore_name(store.getStore_name());
        storeRedis.setProducts(store.getProducts());
        storeRedis.setProductsSize(store.getProducts().size());
        storeRedis.setActive(store.isActive());
        return storeRedis;
    }

}
