package com.birimarung.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Duration;


@Component
public class WebDriverUtils {
    private final Logger logger = LoggerFactory.getLogger(WebDriverUtils.class);
    private int timeoutInSeconds = 30;

    public void waitForElementToBeClickable(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
    }


    public void waitForElementToBeVisibleEnterText(WebDriver driver, By locator, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).sendKeys(text);
    }


    public String waitForElementPresenceGetText(WebDriver driver, By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return driver.findElement(locator).getText();
        } catch (Exception e) {

            logger.info("Something went wrong with " + locator);
            return null;
        }

    }

    public boolean isElementVisible(WebDriver driver, By locator) {
        timeoutInSeconds = 10;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        boolean isElementVisible = false;
        try {
            isElementVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (Exception e) {
            logger.info("Element " + locator + " Not found");
        }


        return isElementVisible;
    }


}
