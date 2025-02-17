package com.birimarung.services;


import com.birimarung.data.Store;
import com.birimarung.dto.RestResponse;
import com.birimarung.dto.StoreDto;
import com.birimarung.repository.StoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;

    public RestResponse<List<StoreDto>> getAllStores() {
        RestResponse<List<StoreDto>> response = new RestResponse<>();
        List<Store> storeList = storeRepository.findAll();
        List<StoreDto> storeDto = new ArrayList<>();
        if (storeList.isEmpty()) {
            response.setMessage("Store List is Empty");
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
            response.setData(null);
        } else {
            for (Store store : storeList) {
                storeDto.add(convertToStoreResponse(store));
            }
            response.setMessage("Store list retrieved successfully");
            response.setStatusCode(HttpStatus.OK.value());
            response.setData(storeDto);
        }
        return response;
    }

    public StoreDto convertToStoreResponse(Store store) {
        StoreDto response = new StoreDto();
        response.setId(store.getId());
        response.setStore_name(store.getStore_name());
        response.setProducts(store.getProducts());
        response.setProductsSize(store.getProducts().size());
        response.setActive(store.isActive());
        return response;
    }

}
