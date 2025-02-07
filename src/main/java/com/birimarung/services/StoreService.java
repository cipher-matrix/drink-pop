package com.birimarung.services;


import com.birimarung.data.Store;
import com.birimarung.dto.RestResponse;
import com.birimarung.repository.StoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;

    public RestResponse<List<Store>> getAllStores() {
        RestResponse<List<Store>> response = new RestResponse<>();
        List<Store> storeList = storeRepository.findAll();
        if (storeList.isEmpty()) {
            response.setMessage("Store List is Empty");
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
            response.setData(null);
        } else {
            response.setMessage("Store list retrieved successfully");
            response.setStatusCode(HttpStatus.OK.value());
            response.setData(storeList);
        }
        return response;
    }

}
