package com.example.demo.modelo;

public class Libro {
    private int id;
    private String titulo;
    private String autor;
    private String editorial;

    public Libro(int id, String titulo, String autor, String editorial) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
    }
    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getEditorial() {
        return editorial;
    }

    public String getAutor() { // Corrected method name
        return autor;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) { // Corrected method name
        this.autor = autor;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }
}
