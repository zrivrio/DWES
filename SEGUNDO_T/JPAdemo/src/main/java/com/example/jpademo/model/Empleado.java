package com.example.jpademo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
//El tbale se utiliza cuando la tabla tiene otro nombre que en tu bas ede datos por ello se pone el nombre ahi de tu tabla
//@Table()
public class Empleado {

    @Id
    //Se increamenta automaticamente la id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Column(nullable = false)
    private String nombre;

    //cuando se pone name es cuando la tabla no se llama igual que en tu base de datos
    //@Column(name = apellido)
    @Column(nullable = false)
    private String apellido;

    public Empleado() {
    }
}
