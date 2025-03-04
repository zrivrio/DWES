package com.example.prueba.execption;

public class ProductoNotFoundException extends RuntimeException{
    public ProductoNotFoundException(Long id) {
        super("Not found Orden with id: " + id);
    }
}
