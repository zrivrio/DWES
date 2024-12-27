<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="org.iesbelen.model.Producto" %>
<%@ page import="java.text.DecimalFormat" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Carrito de Compras</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
</head>
<body>
<%@ include file="/WEB-INF/jsp/fragmentos/nav.jspf" %>
<main class="container mt-4">
    <h1>Carrito de Compras</h1>

    <%
        List<Producto> listaProducto = (List<Producto>) request.getAttribute("listaProducto");
        Double totalCarrito = (Double) request.getAttribute("totalCarrito");

        // Formatear el total del carrito con dos decimales
        DecimalFormat df = new DecimalFormat("#.00");
        String totalFormatted = (totalCarrito != null) ? df.format(totalCarrito) : "0.00";
    %>

    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Nombre Producto</th>
            <th>Cantidad</th>
            <th>Precio Unitario</th>
            <th>Total</th>
            <th>Acción</th>
        </tr>
        </thead>
        <tbody>
        <%
            if (listaProducto != null && !listaProducto.isEmpty()) {
                for (Producto producto : listaProducto) {
                    double totalProducto = producto.getPrecio() * producto.getCantidad();
        %>
        <tr>
            <td><%= producto.getNombre() %></td>
            <td>
                <form action="<%= request.getContextPath() + "/proyecto/carrito/" %>" method="get" class="d-flex align-items-center">
                    <!-- Botón para disminuir cantidad -->
                    <button type="submit" class="btn btn-outline-secondary btn-sm" name="accion" value="disminuir">-</button>
                    <input type="number" name="cantidad" value="<%= producto.getCantidad() %>" class="form-control-sm w-25 text-center" readonly>
                    <!-- Botón para aumentar cantidad -->
                    <button type="submit" class="btn btn-outline-secondary btn-sm" name="accion" value="aumentar">+</button>
                    <input type="hidden" name="codigo" value="<%= producto.getIdProducto() %>">
                </form>
            </td>
            <td>$<%= producto.getPrecio() %></td>
            <td>$<%= totalProducto %></td>
            <td>
                <form action="<%= request.getContextPath() + "/proyecto/carrito/" %>" method="get" class="d-flex align-items-center">
                    <button type="submit" class="btn btn-danger btn-sm" name="eliminar" value="eliminar">Eliminar</button>
                    <input type="hidden" name="codigo" value="<%= producto.getIdProducto() %>">
                </form>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="5" class="text-center">No hay productos en el carrito.</td>
        </tr>
        <%
            }
        %>
        </tbody>

    </table>

    <div class="d-flex justify-content-between">
        <h3>Total del carrito: $<%= totalFormatted %></h3>
        <form action="${pageContext.request.contextPath}/proyecto/pedidos/crear/" method="post">
            <button type="submit" class="btn btn-success">Proceder al Pago</button>
        </form>
    </div>
</main>

<%@ include file="/WEB-INF/jsp/fragmentos/footer.jspf" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
</body>
</html>
