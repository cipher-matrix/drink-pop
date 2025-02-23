package com.birimarung.config;

import jakarta.annotation.PreDestroy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;


public class WebDriverConfig {
    private WebDriver driver;

    public WebDriver getDriver() {
        ChromeOptions options = new ChromeOptions();


        options.addArguments("--incognito");
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--remote-debugging-port=9222");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-extensions");
        options.addArguments("--blink-settings=imagesEnabled=false");
        options.addArguments("--disable-notifications");
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        return driver;
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    @PreDestroy
    public void cleanup() {
        quitDriver();
    }
}
