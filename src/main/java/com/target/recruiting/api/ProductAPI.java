package com.target.recruiting.api;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

public interface ProductAPI
{
    @GetMapping(path= "/products/{id}",produces = { MediaType.APPLICATION_JSON_VALUE })
    @RequestMapping(method = RequestMethod.GET,path="/products/{id}",produces = "application/json; charset=UTF-8")
    ResponseEntity getProductDetails(@PathVariable("id") String productId);
}
