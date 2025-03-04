package com.example.prueba.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class Producto {

    @Id //Señalizar en la base de datos que esta es la primary key
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto") //Nombre que se le asigna en la base de datos
    private Long id;

    private String nombre;
    private Double precio;

    @ManyToMany(mappedBy = "productos") //Define el lado inverso de la relacion con Orden
    private List<Orden> ordenes; //Un producto puede estar en muchas ordenes
}
