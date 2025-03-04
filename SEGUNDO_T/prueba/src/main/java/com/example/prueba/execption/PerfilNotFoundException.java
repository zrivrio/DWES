package com.example.prueba.execption;

public class PerfilNotFoundException extends RuntimeException{
    public PerfilNotFoundException(Long id) {
        super("Not found Orden with id: " + id);
    }
}
