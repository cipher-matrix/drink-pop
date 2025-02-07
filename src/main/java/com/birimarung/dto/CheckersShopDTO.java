package com.birimarung.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckersShopDTO {
    @JsonProperty("price")
    private String price;
    @JsonProperty("unit_sale_price")
    private String unitSalePrice;
    @JsonProperty("name")
    private String name;
    @JsonProperty("variant")
    private String variant;
    @JsonProperty("id")
    private String id;
    @JsonProperty("position")
    private String position;
    @JsonProperty("product_image_url")
    private String productImageURL;
    @JsonProperty("category")
    private String category;
    @JsonProperty("list")
    private String list;
    @JsonProperty("stock")
    private String stock;
    @JsonProperty("brand")
    private String brand;
    private String storeName;
}
