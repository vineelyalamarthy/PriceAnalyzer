package com.target.recruiting.service.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.target.recruiting.common.utils.http.RestClient;
import com.target.recruiting.model.Product;
import com.target.recruiting.service.ProductDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductDataService {

    @Autowired
    private RestClient restClient;

    @Override
    public Product fetchProductDetails(Integer productId) {

        // TODO: Get this from a separate config file.
        String url = String.format("https://redsky.target.com/v2/pdp/tcin/%d?excludes=taxonomy,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics", 13860428);

        ResponseEntity<String> response = this.restClient.doGet(url, null, String.class);

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
