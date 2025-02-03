package org.iesbelen.controlador;

import org.iesbelen.excepxiones.MiExcepcion;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Manejo global de RuntimeException
    @ExceptionHandler(RuntimeException.class)
    public ModelAndView handleRuntimeException(RuntimeException ex) {
        ModelAndView mav = new ModelAndView("/errores/error"); // Redirige a error.html
        mav.addObject("message", ex.getMessage());
        return mav;
    }

    // Manejo global de MiExcepcion
    @ExceptionHandler(MiExcepcion.class)
    public ModelAndView handleMiExcepcion(MiExcepcion ex) {
        ModelAndView mav = new ModelAndView("/errores/error-mi-excepcion"); // Redirige a error-mi-excepcion.html
        mav.addObject("message", ex.getMessage());
        return mav;
    }
}
