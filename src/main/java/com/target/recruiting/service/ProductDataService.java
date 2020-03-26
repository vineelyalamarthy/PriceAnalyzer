package com.target.recruiting.service;
import com.target.recruiting.model.Product;

public interface ProductDataService {
    Product fetchProductDetails(Integer productId);
}
