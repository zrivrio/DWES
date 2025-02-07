package org.iesbelen.controlador;


import org.iesbelen.excepcion.MiExcepcion;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Manejo global de RuntimeException
    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(RuntimeException ex, Model model) {
        model.addAttribute("message", ex.getMessage());
        return "error";
    }

    // Manejo global de MiExcepcion
    @ExceptionHandler(MiExcepcion.class)
    public String handleMiExcepcion(MiExcepcion ex, Model model) {
        model.addAttribute("message", ex.getMessage());
        return "error-mi-excepcion";
    }
}
