package com.birimarung.controllers;

import com.birimarung.scraper.ScraperService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;

@RestController
@AllArgsConstructor
public class ScrapperController {
    private final ScraperService scraperService;

    @GetMapping("/scrape")
    public void scrape() throws MalformedURLException {
        scraperService.entryToScraping();
    }
}
