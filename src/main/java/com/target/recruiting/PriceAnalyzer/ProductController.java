package com.target.recruiting.PriceAnalyzer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController implements ProductAPI {

    @Autowired
    private ProductDataService productDataService;

    @Override
    public ResponseEntity getProductDetails(@PathVariable("id") String productId) {
        Product productDetails = productDataService.fetchProductDetails(Integer.parseInt(productId));
        ProductResponse response = new ProductResponse();
        response.setProduct(productDetails);
        response.setStatus(HttpStatus.OK);
        return new ResponseEntity<ProductResponse>(response, HttpStatus.OK);
    }
}
