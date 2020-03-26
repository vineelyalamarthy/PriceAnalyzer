package com.target.recruiting.controller;
import com.target.recruiting.api.ProductAPI;
import com.target.recruiting.model.Product;
import com.target.recruiting.response.ProductResponse;
import com.target.recruiting.service.ProductDataService;
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

        try {
            Product productDetails = productDataService.fetchProductDetails(Integer.parseInt(productId));
            ProductResponse response = new ProductResponse();
            response.setProduct(productDetails);
            response.setStatus(200);
            return new ResponseEntity<ProductResponse>(response, HttpStatus.OK);
        }
        catch(Exception e){
            ProductResponse response = new ProductResponse();
            response.setMessage(e.getLocalizedMessage() + " "+ e.toString());
            response.setStatus(500);
            return new ResponseEntity<ProductResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
