package org.iesbelen.model;

public class Usuario {
    private int idUsuario;
    private String nombre;
    private String password;
    private String direccion;
    private String rol;


    public Usuario() {}

    public int getIdUsuario() {return idUsuario;}
    public String getNombre() {return nombre;}
    public String getPassword() {return password;}
    public String getDireccion() {return direccion;}
    public String getRol() {return rol;}

    public void setIdUsuario(int idUsuario) {this.idUsuario = idUsuario;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public void setPassword(String password) {this.password = password;}
    public void setDireccion(String direccion) {this.direccion = direccion;}
    public void setRol(String rol) {this.rol = rol;}


}