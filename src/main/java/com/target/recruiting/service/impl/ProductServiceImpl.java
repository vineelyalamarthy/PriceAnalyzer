package com.target.recruiting.service.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.target.recruiting.model.Product;
import com.target.recruiting.service.ProductDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductServiceImpl implements ProductDataService {

    private RestTemplate restTemplate;

    @Autowired
    public ProductServiceImpl(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public Product fetchProductDetails(Integer productId) {

        String url = String.format("https://redsky.target.com/v2/pdp/tcin/%d?excludes=taxonomy,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics", 13860428);

        String result = this.restTemplate.getForObject(url, String.class);

        ResponseEntity<String> response
                = restTemplate.getForEntity(url, String.class);

        JsonElement jsonElement = new JsonParser().parse(response.getBody());

        JsonObject product = jsonElement.getAsJsonObject().get("product").getAsJsonObject();

        JsonObject priceData = product.get("price").getAsJsonObject().get("listPrice").getAsJsonObject();

        double price = priceData.get("price").getAsDouble();

        String formattedPrice = priceData.get("formattedPrice").getAsString();

        String itemName = product.get("item").getAsJsonObject().get("product_description").getAsJsonObject().get("title").getAsString();


        Product objectResponse = new Product();
        objectResponse.setName(itemName);
        objectResponse.setPrice(formattedPrice);

        return objectResponse;
    }

}
