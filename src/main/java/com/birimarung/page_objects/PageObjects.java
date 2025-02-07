package com.birimarung.page_objects;

import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

@Component
public class PageObjects {
    //Checkers Page Objects

    public By productNameCheckers = By.xpath("(//h3[@class='item-product__name'])");
    public By specialPriceContainerCheckers = By.xpath("//div[@class='extra-message extra-message--xtra-savings']");
    public By priceNowForProductCheckers = By.xpath("//span[@class='now']");

    public By checkersAllProductsDiv = By.xpath("//figure[@class='item-product__content']");



    // Pick n Pay PageObjects
    public By pickNPayAllProductsDiv = By.xpath("//div[@class='product-grid-item list-mobile ng-star-inserted']");
    public By productNameElementPickNPay = By.xpath("//div[@class='product-grid-item list-mobile ng-star-inserted']//a[@class='product-grid-item__info-container__name product-action']");
    public By productPricePickNPay = By.xpath("//div[@class='price price_promo']");
}
