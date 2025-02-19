package com.birimarung.controllers;

import com.birimarung.dto.RestResponse;
import com.birimarung.redis_data.ProductsRedis;
import com.birimarung.services.ProductsService;
import lombok.AllArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductsController {
    private final ProductsService productsService;

    @GetMapping("/all")
    public ResponseEntity<RestResponse<List<ProductsRedis>>> getAllProductsController() {
        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl(CacheControl.maxAge(1, TimeUnit.HOURS).cachePublic());
        RestResponse<List<ProductsRedis>> response = productsService.getAllProducts();
        return ResponseEntity.ok()
                .headers(headers)
                .body(response);
    }


    @GetMapping("/all/{drink_name}")
    public RestResponse<List<ProductsRedis>> getDrinksOfCertainName(@PathVariable String drink_name) {
        return productsService.getAllDrinksByName(drink_name);
    }
}
