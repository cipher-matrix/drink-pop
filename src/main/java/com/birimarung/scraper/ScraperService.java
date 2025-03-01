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
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Profile("dev")
@Service
@RequiredArgsConstructor
public class ScraperService {

    private final StoreRepository storeRepository;
    private final ProductRepository productRepository;
    private final ProductsScrapingService productsScrapingService;
    private final DrinksRepository drinksRepository;
    private final Constants constants;
    @Value("${SPRING_PROFILES_ACTIVE}")
    private String activeProfile;
    private final Logger logger = LoggerFactory.getLogger(ScraperService.class);

    @Scheduled(fixedDelay = 1000)
    @Transactional
    public void entryToScraping() throws MalformedURLException {
        List<Store> allAvailableStores = storeRepository.findByIsActiveTrue();
        WebDriver webDriver;
        WebDriverConfig webDriverConfig = new WebDriverConfig();
        if (activeProfile.equalsIgnoreCase("prod")) {
            webDriver = webDriverConfig.getDriverProd();
        } else {
            webDriver = webDriverConfig.getDriver();
        }

        String url;
        productRepository.deleteAll();
        try {
            for (Store storeName : allAvailableStores) {
                List<Drinks> allAvailableDrinks = drinksRepository.findByIsDrinkPubliclyAvailable(true);
                for (Drinks drinks : allAvailableDrinks) {
                    url = getStoreEndpoint(storeName.getStore_name(), drinks.getDrinkName());
                    webDriver.get(url);

                    List<ProductDTO> productDTOList = new ArrayList<>();
                    try {
                        productDTOList = productsScrapingService
                                .getProducts(storeName.getStore_name(), webDriver, drinks.getDrinkName());
                    } catch (Exception e) {
                        logger.info("Something went wrong");
                    }


                    if (productDTOList.isEmpty()) {
                        continue;
                    }
                    saveProductsToDatabase(productDTOList, drinks, storeName.getId());
                }
            }
        } catch (Exception e) {
            logger.error("Something went wrong " + e.getMessage());
        } finally {
            webDriverConfig.quitDriver();
        }
    }

    public void saveProductsToDatabase(List<ProductDTO> productDTOList, Drinks drinks, int storeId) {
        Optional<Store> storeOptional = storeRepository.findById(storeId);
        Store store = new Store();
        if (storeOptional.isPresent()) {
            store = storeOptional.get();
        }
        for (ProductDTO productDTO : productDTOList) {
            Optional<Product> isDrinkAlreadyAvailable = productRepository
                    .findProductByDrinkNameAndStoreId(productDTO.getDrinkName(), storeId);

            if (isDrinkAlreadyAvailable.isEmpty()) {
                Product productEntity = new Product();
                if (productDTO.getDrinkName().toLowerCase()
                        .contains(drinks.getDrinkName().toLowerCase())) {
                    productEntity.setStoreId(storeId);
                    productEntity.setPrice(productDTO.getDrinkPrice());
                    productEntity.setImageUrl(productDTO.getDrinkImageUrl());
                    productEntity.setDrinkName(productDTO.getDrinkName());
                    productEntity.setDescription("Nothing yet");
                    productEntity.setStoreName(store.getStore_name().toUpperCase());

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

    public String getStoreEndpoint(String storeName, String drinkName) {
        String url = "";
        switch (storeName) {
            case "checkers":
                url = constants.CHECKERS_URL.replace("{drink_name}", encodeParam(drinkName));
                break;
            case "picknpay":
                url = constants.PICK_N_PAY_URL.replace("{drink_name}", encodeParam(drinkName));
                break;
            case "shoprite":
                url = constants.SHOPRITE_URL.replace("{drink_name}", encodeParam(drinkName));
                break;
            case "liquorcity":
                url = constants.LIQUOR_CITY_URL;
                break;
            default:
                break;
        }
        return url;
    }

    public String encodeParam(String param) {
        return URLEncoder.encode(param, StandardCharsets.UTF_8);
    }
}
