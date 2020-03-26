package com.target.recruiting.response;

import com.target.recruiting.model.Product;
import org.springframework.http.HttpStatus;

public class ProductResponse {

    private int statusCode;

    private Product product;

    private String message;

    public int  getStatus() {
        return statusCode;
    }

    public void setStatus(int status) {
        this.statusCode = status;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ProductResponse{" +
                "status=" + statusCode +
                ", product=" + product +
                '}';
    }
}
