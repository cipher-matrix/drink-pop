package com.birimarung.services;

import com.birimarung.data.Store;
import com.birimarung.repository.StoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;

    public List<Store> getAllStores(){
        return storeRepository.findAll();
    }

}
