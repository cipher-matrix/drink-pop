package com.birimarung.config;

import jakarta.annotation.PreDestroy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.time.Duration;


public class WebDriverConfig {
    private WebDriver driver;

    public WebDriver getDriver() {
        ChromeOptions options = new ChromeOptions();
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        options.setBinary("/usr/bin/google-chrome");
        options.addArguments("--remote-debugging-port=9515");
        options.addArguments("--incognito");
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-extensions");
        options.addArguments("--blink-settings=imagesEnabled=false");
        options.addArguments("--disable-notifications");
        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("/usr/local/bin/chromedriver"))
                .usingAnyFreePort()
                .build();

        WebDriver driver = new ChromeDriver(service, options);
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
