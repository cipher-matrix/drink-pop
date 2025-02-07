package com.birimarung.scraper;

import com.birimarung.config.WebDriverConfig;
import com.birimarung.constants.Constants;
import com.birimarung.data.Drinks;
import com.birimarung.data.Product;
import com.birimarung.data.Store;
import com.birimarung.dto.ProductDTO;
import com.birimarung.repository.DrinksRepository;
import com.birimarung.repository.ProductRepository;
import com.birimarung.repository.StoreRepository;
import com.birimarung.services.ProductsScrapingService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ScraperService {

    private final StoreRepository storeRepository;
    private final ProductRepository productRepository;
    private final ProductsScrapingService productsScrapingService;
    private final DrinksRepository drinksRepository;
    private final Constants constants;
    private final Logger logger = LoggerFactory.getLogger(ScraperService.class);

    @Scheduled(cron = "0 0 23 * * ?")
    @Transactional
    public void entryToScraping() {
        List<Store> allAvailableStores = storeRepository.findByIsActiveTrue();
        WebDriver webDriver;
        WebDriverConfig webDriverConfig = new WebDriverConfig();
        webDriver = webDriverConfig.getDriver();
        String url;
        try {
            for (Store storeName : allAvailableStores) {
                List<Drinks> allAvailableDrinks = drinksRepository.findAll();
                for (Drinks drinks : allAvailableDrinks) {
                    url = getStoreEndpoint(storeName.getStore_name(), drinks.getDrinkName());
                    webDriver.get(url);
                    List<ProductDTO> productDTOList = productsScrapingService.getProducts(storeName.getStore_name(), webDriver, drinks.getDrinkName(), url);


                    for (ProductDTO productDTO : productDTOList) {
                        Optional<Product> isDrinkAlreadyAvailable = productRepository.findProductByDrinkNameAndStoreId(productDTO.getDrinkName(), storeName.getId());

                        if (isDrinkAlreadyAvailable.isEmpty()) {
                            Product productEntity = new Product();
                            if (productDTO.getDrinkName().toLowerCase().contains(drinks.getDrinkName().toLowerCase())) {
                                productEntity.setStoreId(storeName.getId());
                                productEntity.setPrice(productDTO.getDrinkPrice());
                                productEntity.setImageUrl(productDTO.getDrinkImageUrl());
                                productEntity.setDrinkName(productDTO.getDrinkName());
                                productEntity.setDescription("Nothing yet");
                                if (productDTO.getSpecialDrinkPrice().isEmpty()) {
                                    productEntity.setDrinkOnSpecial(false);
                                } else {
                                    productEntity.setDrinkOnSpecial(true);
                                    productEntity.setSpecialPrice(productDTO.getSpecialDrinkPrice());
                                }
                                productRepository.save(productEntity);
                            }
                        } else {
                            logger.info("Product already exists with the same store_id, drink_name, and price.");
                        }

                    }
                }
            }
        } catch (Exception e) {
            logger.error("Something went wrong " + e.getMessage());
        } finally {
            webDriverConfig.quitDriver();
        }
    }


    public String getStoreEndpoint(String storeName, String drinkName) {
        String url = "";
        switch (storeName) {
            case "checkers":
                url = constants.CHECKERS_URL.replace("%s", drinkName);
                break;
            case "picknpay":
                url = constants.PICK_N_PAY_URL.replace("%s", drinkName);
                break;
            default:
                break;
        }
        return url;
    }


}
