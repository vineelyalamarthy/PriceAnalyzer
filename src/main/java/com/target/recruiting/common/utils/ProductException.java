package com.target.recruiting.common.utils;

public class ProductException extends RuntimeException {
    public ProductException(String errorMessage,  Throwable e){
        super(errorMessage, e);
    }
}
