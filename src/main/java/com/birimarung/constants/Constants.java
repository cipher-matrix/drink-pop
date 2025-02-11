package com.birimarung.constants;

import org.springframework.stereotype.Component;

@Component
public class Constants {
    public String CHECKERS_URL = "https://www.checkers.co.za/search/all/refine-products?q={drink_name}%3AsearchRelevance%3AregularSaveInSites%3A57861%3AxtraSavingInSites%3A57861%3AbrowseAllStoresFacetOff%3AbrowseAllStoresFacetOff%3AbrowseAllStoresFacet%3AbrowseAllStoresFacet";
    public String PICK_N_PAY_URL = "https://www.pnp.co.za/search/{drink_name}?query={drink_name}:relevance:isOnPromotion:On%20Promotion";

    public String SHOPRITE_URL = "https://www.shoprite.co.za/search/all/refine-products?q={drink_name}%3AsearchRelevance%3AregularSaveInSites%3A1894%3AxtraSavingInSites%3A1894%3AxtraSavingInSites%3APSWC%3AbrowseAllStoresFacet%3AbrowseAllStoresFacet%3AbrowseAllStoresFacetOff%3AbrowseAllStoresFacetOff";

    public String LIQUOR_CITY_URL = "https://shop.liquorcity.co.za/api/search_products";
}
