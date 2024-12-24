package org.iesbelen.model;

public class Artista {
    private int idArtista;
    private String nombre;
    private String nacionalidad;
    private String descripcion;
    private int anioInicio;

    public int getIdArtista() {return idArtista;}
    public String getNombre() {return nombre;}
    public String getNacionalidad() {return nacionalidad;}
    public String getDescripcion() {return descripcion;}
    public int getAnioInicio() {return anioInicio;}

    public void setIdArtista(int idArtista) {this.idArtista = idArtista;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public void setNacionalidad(String nacionalidad) {this.nacionalidad = nacionalidad;}
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}
    public void setAnioInicio(int anioInicio) {this.anioInicio = anioInicio;}
}
