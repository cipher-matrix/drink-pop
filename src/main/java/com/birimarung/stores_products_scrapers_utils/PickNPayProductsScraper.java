package com.birimarung.stores_products_scrapers_utils;

import com.birimarung.dto.ProductDTO;
import com.birimarung.page_objects.PageObjects;
import com.birimarung.utils.WebDriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PickNPayProductsScraper {

    public List<ProductDTO> pickNPayProducts(WebDriver driver, PageObjects pageObjects, List<ProductDTO> productDTOList) {
        List<WebElement> allProducts = driver.findElements(pageObjects.pickNPayAllProductsDiv);
        for (int i = 0; i < allProducts.size(); i++) {
            ProductDTO productDTO = new ProductDTO();

            List<WebElement> productNameElement = driver.findElements(pageObjects.productNameElementPickNPay);
            String productName = productNameElement.get(i).getText();

            String imageUrlText = "//img[@alt='%s']";

            WebElement productImageElementPickNPay = driver.findElement(By.xpath(imageUrlText.replace("%s", productName)));
            String imageUrl = productImageElementPickNPay.getAttribute("src");


            List<WebElement> productPriceElement = driver.findElements(pageObjects.productPricePickNPay);
            String productPrice = productPriceElement.get(i).getText();
            String[] twoPrices = productPrice.split(" ");
            String specialPrice = twoPrices[0].replace("R", "");
            String normalPrice = twoPrices[1].replace("R", "");
            productDTO.setDrinkName(productName);
            productDTO.setDrinkPrice(Double.parseDouble(normalPrice));
            productDTO.setDrinkImageUrl(imageUrl);
            productDTO.setSpecialDrinkPrice(specialPrice);
            productDTOList.add(productDTO);
        }
        return productDTOList;
    }
}
