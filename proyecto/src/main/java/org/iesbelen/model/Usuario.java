package org.iesbelen.model;

public class Usuario {
    private int idUsuario;
    private String usuario;
    private String password;
    private String direccion;
    private String rol;


    public Usuario() {}

    public int getIdUsuario() {return idUsuario;}
    public String getUsuario() {return usuario;}
    public String getPassword() {return password;}
    public String getDireccion() {return direccion;}
    public String getRol() {return rol;}

    public void setIdUsuario(int idUsuario) {this.idUsuario = idUsuario;}
    public void setUsuario(String usuario) {this.usuario = usuario;}
    public void setPassword(String password) {this.password = password;}
    public void setDireccion(String direccion) {this.direccion = direccion;}
    public void setRol(String rol) {this.rol = rol;}


}