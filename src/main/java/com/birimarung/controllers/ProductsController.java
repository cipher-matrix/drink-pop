package com.birimarung.controllers;

import com.birimarung.data.Product;
import com.birimarung.dto.RestResponse;
import com.birimarung.services.ProductsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductsController {
    private final ProductsService productsService;

    @GetMapping("/all")
    public RestResponse<List<Product>> getAllProductsController() {
        return productsService.getAllProducts();
    }

    @GetMapping("/all/{drink_name}")
    public RestResponse<List<Product>> getDrinksOfCertainName(@PathVariable String drink_name) {
        return productsService.getAllDrinksByName(drink_name);
    }
}
