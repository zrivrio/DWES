package com.example.prueba.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class Orden {

    @Id //Señalizar en la base de datos que esta es la primary key
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "id_orden") //Nombre que se le asigna en la base de datos
    private Long id;

    private LocalDateTime fecha = LocalDateTime.now();

    @ManyToOne //Indican que muchas ordenes pueden pertenece a un solo usuario
    @JoinColumn(name = "id_usuario") //id_usuario en la tabla Orden es la clave foranea de usuario
    private Usuario usuario; //Muchas ordenes pertenecen a un solo usuario

    @ManyToMany //Indica que una orden puede tener varios productos y un producto puede estar en varias ordenes
    @JoinTable( //Define la tabla intermedia
            name = "orden_producto", //El nombre que va a tener la tabla
            joinColumns = @JoinColumn(name =  "id_orden"),
            inverseJoinColumns =  @JoinColumn(name = "id_producto")
    )
    private List<Producto> productos; //Relacion de muchos a muchos con Productos


}
