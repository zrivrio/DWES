package com.example.prueba.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.*;

@Embeddable //Indica que direccion es un componente reutilizable ,pero no es una entidad idependiente
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Direccion {

    private String calle;
    private String ciudad;
    private String codigoPostal;
}
