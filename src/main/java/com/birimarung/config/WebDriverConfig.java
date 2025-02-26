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
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--disable-gpu", "--incognito", "--disable-extensions", "--blink-settings=imagesEnabled=true", "--disable-notifications");

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
