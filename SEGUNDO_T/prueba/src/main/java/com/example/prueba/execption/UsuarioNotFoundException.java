package com.example.prueba.execption;

public class UsuarioNotFoundException extends RuntimeException{
    public UsuarioNotFoundException(Long id) {
        super("Not found Orden with id: " + id);
    }
}
