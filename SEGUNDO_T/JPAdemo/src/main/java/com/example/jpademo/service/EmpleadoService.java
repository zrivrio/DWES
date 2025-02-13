package com.example.jpademo.service;

import com.example.jpademo.model.Empleado;
import com.example.jpademo.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoService {

    @Autowired
    EmpleadoRepository empleadoRepository;

    public List<Empleado> getAll() {
    return empleadoRepository.findAll();
    }

    public Empleado guardaEmpleado(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }


}
