package com.birimarung.controllers;


import com.birimarung.data.Store;
import com.birimarung.dto.RestResponse;
import com.birimarung.services.StoreService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @GetMapping("/stores")
    public RestResponse<List<Store>> getAllStoresController() {
        return storeService.getAllStores();
    }
}
