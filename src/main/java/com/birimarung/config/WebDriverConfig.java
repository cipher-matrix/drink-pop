package com.birimarung.config;

import com.titusfortner.logging.SeleniumLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import jakarta.annotation.PreDestroy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;


public class WebDriverConfig {
    private WebDriver driver;


    public WebDriver getDriverProd() throws MalformedURLException {
        SeleniumLogger.enable();
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage", "--disable-gpu", "--incognito", "--disable-extensions", "--blink-settings=imagesEnabled=true", "--disable-notifications");
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");
        return new RemoteWebDriver(new URL("http://geckodriver:4444"), options);
    }

    public WebDriver getDriver() {
        // Use WebDriverManager to handle GeckoDriver installation
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        // Add arguments for headless execution
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        // Add arguments for enhanced debugging
        options.addArguments("--incognito");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-extensions");
        options.addArguments("--blink-settings=imagesEnabled=false");
        options.addArguments("--disable-notifications");
        // Initialize WebDriver with options
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
