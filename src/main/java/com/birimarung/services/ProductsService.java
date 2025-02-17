package com.birimarung.services;

import com.birimarung.data.Product;
import com.birimarung.dto.ProductDTO;
import com.birimarung.dto.RestResponse;
import com.birimarung.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductsService {
    private final ProductRepository productRepository;

    public RestResponse<List<ProductDTO>> getAllProducts() {
        RestResponse<List<ProductDTO>> response = new RestResponse<>();
        List<ProductDTO> productDTOS = new ArrayList<>();
        List<Product> productList = productRepository.findAll();
        if (productList.isEmpty()) {
            response.setMessage("List is Empty");
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
            response.setData(null);
        } else {
            for (Product product : productList) {
                productDTOS.add(convertProductsToDto(product));
            }
            response.setMessage("Product list retrieved successfully");
            response.setStatusCode(HttpStatus.OK.value());
            response.setData(productDTOS);
        }
        return response;
    }

    public RestResponse<List<ProductDTO>> getAllDrinksByName(String name) {
        RestResponse<List<ProductDTO>> response = new RestResponse<>();
        List<ProductDTO> productDTOS = new ArrayList<>();
        List<Product> productListContainingCertainName = productRepository.findProductByDrinkNameContainingIgnoreCase(name.toLowerCase());
        if (productListContainingCertainName.isEmpty()) {
            response.setMessage("List is Empty");
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
            response.setData(null);
        } else {
            for (Product product : productListContainingCertainName) {
                productDTOS.add(convertProductsToDto(product));
            }
            response.setMessage("Product list for " + name + " retrieved successfully");
            response.setStatusCode(HttpStatus.OK.value());
            response.setData(productDTOS);
        }
        return response;
    }

    private ProductDTO convertProductsToDto(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setSpecialDrinkPrice(product.getSpecialPrice());
        productDTO.setDrinkName(product.getDrinkName());
        productDTO.setDrinkImageUrl(product.getImageUrl());
        productDTO.setDrinkPrice(product.getPrice());
        productDTO.setStoreName(product.getStoreName());
        return productDTO;
    }
}
