package com.birimarung.stores_products_scrapers_utils;

import com.birimarung.dto.ProductDTO;
import com.birimarung.page_objects.PageObjects;
import com.birimarung.utils.WebDriverUtils;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class PickNPayProductsScraper {
    private final Logger logger = LoggerFactory.getLogger(PickNPayProductsScraper.class);
    private final WebDriverUtils webDriverUtils;

    public List<ProductDTO> pickNPayProducts(WebDriver driver, PageObjects pageObjects, List<ProductDTO> productDTOList) {
        try {
            List<WebElement> allProducts = driver.findElements(pageObjects.pickNPayAllProductsDiv);
            String specialPrice, normalPrice, productPrice, imageUrlText, productName, imageUrl;
            for (int i = 0; i < allProducts.size(); i++) {
                ProductDTO productDTO = new ProductDTO();

                List<WebElement> productNameElement = driver.findElements(pageObjects.productNameElementPickNPay);
                productName = productNameElement.get(i).getText();

                imageUrlText = "//img[@alt='%s']";

                WebElement productImageElementPickNPay = driver.findElement(By.xpath(imageUrlText.replace("%s", productName)));
                imageUrl = productImageElementPickNPay.getAttribute("src");


                List<WebElement> productPriceElement = driver.findElements(pageObjects.productPricePickNPay);
                productPrice = productPriceElement.get(i).getText();
                String[] twoPrices = productPrice.split(" ");


                boolean smartShopperPrice = webDriverUtils.isElementVisible(driver, pageObjects.smartShopperPrice);
                if (smartShopperPrice) {
                    specialPrice = webDriverUtils.waitForElementPresenceGetText(driver, pageObjects.smartShopperPrice);
                    normalPrice = twoPrices[0].replace("R", "");
                } else {
                    specialPrice = twoPrices[0].replace("R", "");
                    normalPrice = twoPrices[1].replace("R", "");
                }
                productDTO.setDrinkName(productName);
                productDTO.setDrinkPrice(Double.parseDouble(normalPrice));
                productDTO.setDrinkImageUrl(imageUrl);
                productDTO.setSpecialDrinkPrice(specialPrice);
                productDTOList.add(productDTO);
            }
        } catch (Exception e) {
            logger.info("Something went wrong");
        }

        return productDTOList;
    }
}
