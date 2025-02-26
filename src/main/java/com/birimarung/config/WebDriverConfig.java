package com.birimarung.config;

import com.titusfortner.logging.SeleniumLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import jakarta.annotation.PreDestroy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;


public class WebDriverConfig {
    private WebDriver driver;

    public WebDriver getDriver() throws MalformedURLException {
        SeleniumLogger.enable();
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage", "--disable-gpu", "--incognito", "--disable-extensions", "--blink-settings=imagesEnabled=true", "--disable-notifications");
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");

        return new RemoteWebDriver(new URL("http://geckodriver:4444"), options);
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
