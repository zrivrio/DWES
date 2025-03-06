package com.example.examen.exception;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(Long id) {
        super("Not found product with id: " + id);
    }
}
