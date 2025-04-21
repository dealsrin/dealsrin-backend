package com.dealsrin.api.service;

import com.dealsrin.api.model.Deal;
import com.dealsrin.api.repository.DealRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class TataNeuScraperService {

    @Autowired
    private DealRepository dealRepository;

    private final String[] urls = {
            "https://www.tataneu.com/native-electronics/product-listing?query=earphones&category=All",
            "https://www.tataneu.com/native-electronics/product-listing?query=headphones&category=All"
    };

    public void scrapeAndSaveDeals() {
        for (String url : urls) {
            try {
                Document doc = Jsoup.connect(url).get();
                Elements productElements = doc.select("div.product-card-wrapper");

                for (Element product : productElements) {
                    String title = product.select("div.title").text();
                    String priceText = product.select("div.price").text().replaceAll("[^0-9.]", "");
                    double price = priceText.isEmpty() ? 0.0 : Double.parseDouble(priceText);

                    String productUrl = "https://www.tataneu.com" + product.select("a").attr("href");
                    String category = url.contains("headphone") ? "Headphones" : "Earphones";

                    Deal deal = Deal.builder()
                            .title(title)
                            .description("") // Optional for now
                            .url(productUrl)
                            .price(price)
                            .category(category)
                            .build();

                    dealRepository.save(deal);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}