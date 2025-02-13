package com.example.jpademo.controller;

import ch.qos.logback.core.model.Model;
import com.example.jpademo.model.Empleado;
import com.example.jpademo.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//Sin el controller te lleva a una vista
@RestController
@RequestMapping("/empleado")
public class EmpleadoController {

    @Autowired EmpleadoService empleadoService;

    @GetMapping({"", "/"})
    public  List<Empleado> empleado() {
        return empleadoService.getAll();
    }
    @GetMapping("/crear")
    public Empleado create() {
        Empleado empleado = new Empleado();
        empleado.setNombre("Juan");
        empleado.setApellido("Garcia Garcia");
        return empleadoService.guardaEmpleado(empleado);
    }

}
