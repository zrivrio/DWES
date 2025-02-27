package org.iesbelen.model;

import java.time.LocalDate;
import java.util.Date;

public class Pedido {
    private int idPedido;
    private int idUsuario;
    private LocalDate fechaPedido;
    private String estadoPedido;

    public int getIdPedido() {return idPedido;}

    public int getIdUsuario() {return idUsuario;}

    public LocalDate getFechaPedido() {return fechaPedido;}

    public void setIdPedido(int idPedido) {this.idPedido = idPedido;}

    public void setEstadoPedido(String estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public String getEstadoPedido() {
        return estadoPedido;
    }

    public void setIdUsuario(int idUsuario) {this.idUsuario = idUsuario;}

    public void setFechaPedido(LocalDate fechaPedido) {this.fechaPedido = fechaPedido;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Producto)) return false;

        Pedido pedido = (Pedido) o;

        return getIdPedido() == pedido.getIdPedido();
    }

    @Override
    public int hashCode() {
        return getIdUsuario();
    }
}
