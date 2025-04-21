package com.dealsrin.api.controller;

import com.dealsrin.api.model.Deal;
import com.dealsrin.api.service.DealService;
import com.dealsrin.api.service.TataNeuScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deals")
public class DealController {
    @Autowired
    private DealService dealService;
    @Autowired
    private TataNeuScraperService tataNeuScraperService;

    @PostMapping
    public ResponseEntity<Deal> createDeal(@RequestBody Deal deal){
        Deal created = dealService.createDeal(deal);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/scrape/tataneu")
    public ResponseEntity<String> scrapeTataNeu() {
        tataNeuScraperService.scrapeAndSaveDeals();
        return ResponseEntity.ok("Scraping complete and deals saved.");
    }

}
