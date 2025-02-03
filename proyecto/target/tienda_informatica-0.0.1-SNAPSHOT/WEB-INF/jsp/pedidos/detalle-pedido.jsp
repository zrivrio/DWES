<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="org.iesbelen.model.DetallePedido" %>
<%@ page import="java.util.List" %>
<%@ page import="org.iesbelen.model.Pedido" %>
<%@ page import="java.util.Optional" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Detalle Pedido</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
    <style>
        .card-img-top {
            height: 200px;
            object-fit: cover;
        }
        select.form-select {
            width: 100%;
            padding: 0.375rem 0.75rem;
        }
    </style>
</head>
<body>
<%@ include file="/WEB-INF/jsp/fragmentos/nav.jspf" %>
<main class="container mt-4">
    <section>
        <!-- Tabla de pedidos -->
        <div class="d-flex justify-content-between align-items-center mb-3">
            <h1>Detalle Pedido</h1>

            <div class="table-responsive">
                <table class="table table-striped table-bordered">
                    <thead>
                    <tr>
                        <th>Código</th>
                        <th>Usuario</th>
                        <th>Fecha</th>
                        <th>Estado</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        Optional<Pedido> pedido = (Optional<Pedido>) request.getAttribute("pedido");
                        if (pedido != null && pedido.isPresent()) {
                    %>
                    <tr>
                        <td><%= pedido.get().getIdPedido() %></td>
                        <td><%= pedido.get().getIdUsuario() %></td>
                        <td><%= pedido.get().getFechaPedido() %></td>
                        <td>
                            <% if ("pendiente".equals(pedido.get().getEstadoPedido())) { %>
                            <span class="badge bg-primary">Pendiente</span>
                            <% } else if ("procesado".equals(pedido.get().getEstadoPedido())) { %>
                            <span class="badge bg-secondary">Procesado</span>
                            <% } else if ("entregado".equals(pedido.get().getEstadoPedido())) { %>
                            <span class="badge bg-success">Entregado</span>
                            <% } else { %>
                            <span class="badge bg-danger">Cancelado</span>
                            <% } %>
                        </td>
                    </tr>
                    <% } else { %>
                    <tr>
                        <td colspan="4" class="no-users">No hay registros de Pedidos.</td>
                    </tr>
                    <% } %>
                    </tbody>
                </table>
            </div>
        </div>

        <!-- Detalles del Pedido (debajo de la tabla) -->
        <div class="mt-4">
            <%
                List<DetallePedido> listaDestalle = (List<DetallePedido>) request.getAttribute("listaDestalle");
                if (listaDestalle != null && !listaDestalle.isEmpty()) {
            %>
            <div class="row g-4">
                <% for (DetallePedido detallePedido : listaDestalle) { %>
                <div class="col-md-4">
                    <div class="card shadow-lg h-100">
                        <div class="card-body d-flex flex-column">
                            <h5 class="card-title">Id Pedido: <%= detallePedido.getIdPedido() %></h5>
                            <p class="card-text">Id Producto: <%= detallePedido.getIdProducto() %></p>
                            <p class="card-text">Cantidad: <%= detallePedido.getCantidad() %></p>
                            <div class="mt-auto d-flex gap-2">
                                <form action="${pageContext.request.contextPath}/proyecto/detallesPedidos/" method="post" onsubmit="return confirm('¿Estás seguro de que deseas eliminar este producto?')" class="d-inline">
                                    <input type="hidden" name="__method__" value="delete">
                                    <input type="hidden" name="codigo" value="<%= detallePedido.getIdDetalle() %>">
                                    <button type="submit" class="btn btn-danger btn-sm">Eliminar</button>
                                </form>

                            </div>
                        </div>
                    </div>
                </div>
                <% } %>
            </div>
            <% } else { %>
            <p class="text-center text-muted">No hay detalles de pedido.</p>
            <% } %>
        </div>
    </section>
</main>
<%@ include file="/WEB-INF/jsp/fragmentos/footer.jspf" %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
</body>
</html>
