package com.birimarung.services;


import com.birimarung.dto.ProductDTO;
import com.birimarung.page_objects.PageObjects;

import com.birimarung.utils.WebDriverUtils;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class ProductsScrapingService {

    private final PageObjects pageObjects;
    private static final Logger logger = LoggerFactory.getLogger(ProductsScrapingService.class);
    private WebDriverUtils webDriverUtils;


    public List<ProductDTO> getProducts(String storeName, WebDriver driver, String drinkName, String url) {
        List<ProductDTO> productDTOList = new ArrayList<>();
        if (storeName.equalsIgnoreCase("checkers")) {
            String imageUrlText = "//img[@alt='%s']";
            String anchorTagText = "//a[@title='%s']";
            List<WebElement> productsContainers = driver.findElements(pageObjects.checkersAllProductsDiv);

            for (int i = 0; i < productsContainers.size(); i++) {

                List<WebElement> productNamesArray = driver.findElements(pageObjects.productNameCheckers);

                WebElement productNameElement = productNamesArray.get(i);
                String productName = productNameElement.getText();
                boolean contains = productName.toLowerCase().contains(drinkName.toLowerCase());
                if (!contains) {
                    continue;
                }
                String specialPrice = "";
                String priceNow = "";
                String imageSrc = "";

                ProductDTO productDTO = new ProductDTO();
                List<WebElement> anchorTagsName = driver.findElements(By.xpath(anchorTagText.replace("%s", productName)));

                // Click the product link
                anchorTagsName.get(1).click();
                imageSrc = imageUrlText.replace("%s", productName);
                WebElement imageElement = driver.findElement(By.xpath(imageSrc));
                imageSrc = imageElement.getAttribute("src");
                priceNow = webDriverUtils.waitForElementPresenceGetText(driver, pageObjects.priceNowForProductCheckers, 20).replace("R", "");
                specialPrice = webDriverUtils.waitForElementPresenceGetText(driver, pageObjects.specialPriceContainerCheckers, 20);

                productDTO.setDrinkPrice(Double.parseDouble(priceNow));
                productDTO.setDrinkName(productName);
                productDTO.setDrinkImageUrl(imageSrc);
                productDTO.setSpecialDrinkPrice(specialPrice);


                productDTOList.add(productDTO);

                // Go back to the previous page and wait for the page to reload
                driver.navigate().back();
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
                wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(pageObjects.checkersAllProductsDiv));

                // After waiting, retrieve the product containers again
                productsContainers = driver.findElements(pageObjects.checkersAllProductsDiv);
            }

        }

        if (storeName.equalsIgnoreCase("picknpay")) {
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
        }
        return productDTOList;
    }

}

