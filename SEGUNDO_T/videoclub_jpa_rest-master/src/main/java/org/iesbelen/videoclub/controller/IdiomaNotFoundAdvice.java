package org.iesbelen.videoclub.controller;

import org.iesbelen.videoclub.exception.CategoriaNotFoundException;
import org.iesbelen.videoclub.exception.IdiomaNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class IdiomaNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(IdiomaNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String idiomaNotFoundHandler(IdiomaNotFoundException idiomaNotFoundException) {
        return idiomaNotFoundException.getMessage();
    }

}
