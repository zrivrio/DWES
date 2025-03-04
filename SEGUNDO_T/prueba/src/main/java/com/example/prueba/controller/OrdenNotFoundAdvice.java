package com.example.prueba.controller;

import com.example.prueba.execption.OrdenNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class OrdenNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(OrdenNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String ordenNotFoundHandler(OrdenNotFoundException ordenNotFoundException) {
        return ordenNotFoundException.getMessage();
    }

}
