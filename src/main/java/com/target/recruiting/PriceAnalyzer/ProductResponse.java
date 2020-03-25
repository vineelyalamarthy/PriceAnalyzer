package com.target.recruiting.PriceAnalyzer;

import org.springframework.http.HttpStatus;

public class ProductResponse {

    private HttpStatus status;

    private Product product;

    private String message;

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
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
                "status=" + status +
                ", product=" + product +
                '}';
    }
}
