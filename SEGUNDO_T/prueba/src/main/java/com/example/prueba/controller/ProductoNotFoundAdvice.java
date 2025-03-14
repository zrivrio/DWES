package com.example.prueba.controller;

import com.example.prueba.execption.PerfilNotFoundException;
import com.example.prueba.execption.ProductoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ProductoNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(ProductoNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String productoNotFoundHandler(ProductoNotFoundException productoNotFoundException) {
        return productoNotFoundException.getMessage();
    }

}
