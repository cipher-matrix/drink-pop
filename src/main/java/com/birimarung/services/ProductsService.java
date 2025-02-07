package com.birimarung.services;

import com.birimarung.data.Product;
import com.birimarung.dto.RestResponse;
import com.birimarung.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductsService {
    private final ProductRepository productRepository;

    public RestResponse<List<Product>> getAllProducts() {
        RestResponse<List<Product>> response = new RestResponse<>();
        List<Product> productList = productRepository.findAll();
        if (productList.isEmpty()) {
            response.setMessage("List is Empty");
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
            response.setData(null);
        } else {
            response.setMessage("Product list retrieved successfully");
            response.setStatusCode(HttpStatus.OK.value());
            response.setData(productList);
        }
        return response;
    }

    public List<Product> getAllDrinksByName(String name) {
        List<Product> containingName = productRepository.findProductByDrinkNameContainingIgnoreCase(name.toLowerCase());
        if (containingName.isEmpty()) {
            return null;
        }
        return containingName;
    }
}
