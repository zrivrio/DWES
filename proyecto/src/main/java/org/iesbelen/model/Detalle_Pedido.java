package org.iesbelen.model;

public class Detalle_Pedido {
    private int idDetalle;
    private int idProducto;
    private int idPedido;
    private int Cantidad;

    public int getIdDetalle() {return idDetalle;}

    public int getIdProducto() {return idProducto;}

    public int getIdPedido() {return idPedido;}

    public int getCantidad() {return Cantidad;}

    public void setIdDetalle(int idDetalle) {this.idDetalle = idDetalle;}

    public void setIdProducto(int idProducto) {this.idProducto = idProducto;}

    public void setIdPedido(int idPedido) {this.idPedido = idPedido;}

    public void setCantidad(int cantidad) {Cantidad = cantidad;}
}
