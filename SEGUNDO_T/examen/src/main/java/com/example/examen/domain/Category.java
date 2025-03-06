package com.example.examen.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Negative;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "category")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_categoria;

    private String name;

    private String descrip;

    @OneToMany( mappedBy = "category")
    @JsonIgnore
    private List<Product> products;

    public long getId_categoria() {
        return id_categoria;
    }

    public String getName() {
        return name;
    }

    public String getDescrip() {
        return descrip;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setId_categoria(long id_categoria) {
        this.id_categoria = id_categoria;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
