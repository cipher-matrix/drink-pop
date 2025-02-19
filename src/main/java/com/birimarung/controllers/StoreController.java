package com.birimarung.controllers;


import com.birimarung.dto.RestResponse;
import com.birimarung.redis_data.StoreRedis;
import com.birimarung.services.StoreService;
import lombok.AllArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@AllArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @GetMapping("/stores")
    public ResponseEntity<RestResponse<List<StoreRedis>>> getAllStoresController() {
        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl(CacheControl.maxAge(1, TimeUnit.HOURS).cachePublic());
        RestResponse<List<StoreRedis>> response = storeService.getAllStores();
        return ResponseEntity.ok()
                .headers(headers)
                .body(response);
    }
}
