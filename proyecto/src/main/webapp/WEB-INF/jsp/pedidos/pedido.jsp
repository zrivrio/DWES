<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<%@ page import="org.iesbelen.model.Producto" %>

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
        // Recuperamos los productos del carrito desde la sesión (carrito es un Map<Producto, Integer>)
        Map<Producto, Integer> cartItems = (Map<Producto, Integer>) session.getAttribute("cartItems");
        if (cartItems == null || cartItems.isEmpty()) {
    %>
    <p>El carrito está vacío.</p>
    <%
    } else {
    %>
    <form action="<%= request.getContextPath() + "/proyecto/pedidos/actualizarCarrito" %>" method="post">
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
                double totalCarrito = 0.0;
                for (Map.Entry<Producto, Integer> entry : cartItems.entrySet()) {
                    Producto producto = entry.getKey();
                    int cantidad = entry.getValue();
                    double totalProducto = producto.getPrecio() * cantidad;
                    totalCarrito += totalProducto;
            %>
            <tr>
                <td><%= producto.getNombre() %></td>
                <td>
                    <!-- Botones para aumentar y reducir la cantidad -->
                    <div class="d-flex align-items-center">
                        <button type="submit" name="action" value="decrease_<%= producto.getIdProducto() %>" class="btn btn-outline-secondary btn-sm">-</button>
                        <input type="number" name="cantidad_<%= producto.getIdProducto() %>" value="<%= cantidad %>" class="form-control-sm w-25 text-center" readonly>
                        <button type="submit" name="action" value="increase_<%= producto.getIdProducto() %>" class="btn btn-outline-secondary btn-sm">+</button>
                    </div>
                </td>
                <td>$<%= producto.getPrecio() %></td>
                <td>$<%= totalProducto %></td>
                <td>
                    <a href="<%= request.getContextPath() + "/proyecto/pedidos/eliminarProducto?id=" + producto.getIdProducto() %>" class="btn btn-danger btn-sm">Eliminar</a>
                </td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
        <div class="d-flex justify-content-between">
            <h3>Total del carrito: $<%= totalCarrito %></h3>
            <button type="submit" class="btn btn-success">Actualizar Carrito</button>
        </div>
    </form>
    <%
        }
    %>

</main>
<%@ include file="/WEB-INF/jsp/fragmentos/footer.jspf" %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
</body>
</html>
