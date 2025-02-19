package com.birimarung.services;

import com.birimarung.data.Product;
import com.birimarung.dto.RestResponse;
import com.birimarung.redis_data.ProductsRedis;
import com.birimarung.redis_repository.ProductRedisRepository;
import com.birimarung.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductsService {
    private final ProductRepository productRepository;
    private final ProductRedisRepository productRedisRepository;

    @Cacheable(value = "productsCache", key = "'products'")
    public RestResponse<List<ProductsRedis>> getAllProducts() {
        RestResponse<List<ProductsRedis>> response = new RestResponse<>();
        List<ProductsRedis> productsRedis = new ArrayList<>();
        List<Product> productList = productRepository.findAll();


        if (productList.isEmpty()) {
            response.setMessage("List is Empty");
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
            response.setData(null);
        } else {

            for (Product product : productList) {
                productsRedis.add(convertProductsToDto(product));
            }
            response.setMessage("Product list retrieved successfully");
            response.setStatusCode(HttpStatus.OK.value());
            response.setData(productsRedis);
        }
        return response;
    }

    public RestResponse<List<ProductsRedis>> getAllDrinksByName(String name) {
        RestResponse<List<ProductsRedis>> response = new RestResponse<>();
        List<ProductsRedis> productsRedis = new ArrayList<>();
        List<Product> productListContainingCertainName = productRepository.findProductByDrinkNameContainingIgnoreCase(name.toLowerCase());
        if (productListContainingCertainName.isEmpty()) {
            response.setMessage("List is Empty");
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
            response.setData(null);
        } else {
            for (Product product : productListContainingCertainName) {
                productsRedis.add(convertProductsToDto(product));
            }
            response.setMessage("Product list for " + name + " retrieved successfully");
            response.setStatusCode(HttpStatus.OK.value());
            response.setData(productsRedis);
        }
        return response;
    }

    private ProductsRedis convertProductsToDto(Product product) {
        ProductsRedis productsRedis = new ProductsRedis();
        productsRedis.setSpecialPrice(product.getSpecialPrice());
        productsRedis.setDrinkName(product.getDrinkName());
        productsRedis.setImageUrl(product.getImageUrl());
        productsRedis.setPrice(product.getPrice());
        productsRedis.setStoreName(product.getStoreName());
        return productsRedis;
    }
}
