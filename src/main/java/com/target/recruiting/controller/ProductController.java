package com.target.recruiting.controller;
import com.target.recruiting.api.ProductAPI;
import com.target.recruiting.common.utils.http.JsonUtils;
import com.target.recruiting.model.Product;
import com.target.recruiting.response.ProductResponse;
import com.target.recruiting.service.ProductDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController implements ProductAPI {

    private static final Logger logger
            = LoggerFactory.getLogger(ProductController.class);


    @Autowired
    private ProductDataService productDataService;

    @Override
    public ResponseEntity getProductDetails(@PathVariable("id") String productId) {

        logger.info("getProductDetails  API start");
        logger.info("Input Request for product id:{}", productId);
        try {
            Product productDetails = productDataService.fetchProductDetails(Integer.parseInt(productId));
            ProductResponse response = new ProductResponse();
            response.setProduct(productDetails);
            response.setStatus(200);
            logger.info("getProductDetails  API Response :{}", JsonUtils.toJson(response));
            return new ResponseEntity<ProductResponse>(response, HttpStatus.OK);
        }
        catch(Exception e){
            ProductResponse response = new ProductResponse();
            response.setMessage(e.getLocalizedMessage() + " "+ e.toString());
            response.setStatus(500);
            logger.error("Issues encountered in getting API results ", e);
            logger.error("getProductDetails  API Response :{}", JsonUtils.toJson(response));
            return new ResponseEntity<ProductResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
