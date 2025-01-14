package org.iesbelen.model;

public class Producto {
    private int idProducto;
    private String nombre;
    private double precio;
    private String descripcion;
    private int idCategoria;
    private int idArtista;
    private int cantidad;

    public int getCantidad() {return cantidad;}
    public void setCantidad(int cantidad) {  this.cantidad = cantidad;}
    public int getIdProducto() {
        return idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public int getIdArtista() {
        return idArtista;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public void setIdArtista(int idArtista) {
        this.idArtista = idArtista;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Producto)) return false;

        Producto producto = (Producto) o;

        return getIdProducto() == producto.getIdProducto();
    }

    @Override
    public int hashCode() {
        return getIdProducto();
    }
}
