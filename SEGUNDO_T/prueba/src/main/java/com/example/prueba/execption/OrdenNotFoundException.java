package com.example.prueba.execption;

public class OrdenNotFoundException extends RuntimeException{
    public OrdenNotFoundException(Long id) {
        super("Not found Orden with id: " + id);
    }
}
