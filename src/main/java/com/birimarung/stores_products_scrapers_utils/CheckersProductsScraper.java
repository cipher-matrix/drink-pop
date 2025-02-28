package com.birimarung.stores_products_scrapers_utils;

import com.birimarung.dto.ProductDTO;
import com.birimarung.page_objects.PageObjects;
import com.birimarung.utils.WebDriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;

@Component
public class CheckersProductsScraper {
    public List<ProductDTO> checkersProducts(WebDriver driver, PageObjects pageObjects, WebDriverUtils webDriverUtils, List<ProductDTO> productDTOList, String drinkName) throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.presenceOfElementLocated(pageObjects.checkersAllProductsDiv));
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
            String specialPrice, priceNow, imageSrc, validTillDate, validTillPlace;

            ProductDTO productDTO = new ProductDTO();
            String alcoholName = drinkName.equalsIgnoreCase("Corona") ? productName + " " : productName;
            List<WebElement> anchorTagsName = driver.findElements(By.xpath(anchorTagText.replace("%s", alcoholName)));

            // Click the product link
            anchorTagsName.get(1).click();
            imageSrc = imageUrlText.replace("%s", alcoholName);
            WebElement imageElement = driver.findElement(By.xpath(imageSrc));

            imageSrc = imageElement.getAttribute("src");
            boolean isSpecialPriceTop = false;
            int isSpecialPriceTopCount = 0;
            while(!isSpecialPriceTop){
                isSpecialPriceTop = driver.findElement(pageObjects.checkersTopSpecialPrice).isDisplayed();
                isSpecialPriceTopCount++;
                if(isSpecialPriceTopCount == 20){
                    break;
                }
            }

            if(isSpecialPriceTop){
                specialPrice = webDriverUtils.waitForElementPresenceGetText(driver,pageObjects.checkersTopSpecialPrice,30) + " " + webDriverUtils.waitForElementPresenceGetText(driver,pageObjects.checkersTopSpecialPriceValidTill,30);
                priceNow = webDriverUtils.waitForElementPresenceGetText(driver,pageObjects.priceBeforeCheckers,30).replace("R", "");
            }else{
                priceNow = webDriverUtils.waitForElementPresenceGetText(driver, pageObjects.priceNowForProductCheckers, 20).replace("R", "");
                specialPrice = webDriverUtils.waitForElementPresenceGetText(driver, pageObjects.specialPriceContainerCheckers, 20);
            }


            productDTO.setDrinkPrice(Double.parseDouble(priceNow));
            productDTO.setDrinkName(productName);
            productDTO.setDrinkImageUrl(imageSrc);
            productDTO.setSpecialDrinkPrice(specialPrice.replace("\n", " "));


            productDTOList.add(productDTO);
            driver.navigate().back();
            wait.until(ExpectedConditions.presenceOfElementLocated(pageObjects.checkersAllProductsDiv));
            // After waiting, retrieve the product containers again
            productsContainers = driver.findElements(pageObjects.checkersAllProductsDiv);
        }
        return productDTOList;

    }
}
