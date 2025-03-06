package org.iesbelen.videoclub.controller;

import org.iesbelen.videoclub.exception.IdiomaNotFoundException;
import org.iesbelen.videoclub.exception.SocioNotFoundException;
import org.iesbelen.videoclub.exception.TarjetaNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SocioNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(TarjetaNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String tarjetaNotFoudAdvice (TarjetaNotFoundException tarjetaNotFoundException) {
        return tarjetaNotFoundException.getMessage();
    }

}
