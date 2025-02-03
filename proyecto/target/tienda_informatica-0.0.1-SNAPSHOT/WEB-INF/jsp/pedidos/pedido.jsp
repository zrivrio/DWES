<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page import="org.iesbelen.model.Usuario"%>
<%@page import="java.util.List"%>
<%@ page import="org.iesbelen.model.Pedido" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pedidos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@ include file="/WEB-INF/jsp/fragmentos/nav.jspf" %>

<main class="body_sec m-4">
    <section id="Content">
        <div class="container">
            <!-- Tabla de Pedidos -->
            <div class="table-responsive">
                <table class="table table-striped table-bordered">
                    <thead>
                    <tr>
                        <th>Código</th>
                        <th>Usuario</th>
                        <th>Fecha</th>
                        <th>Estado</th>
                        <th>Acciones</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        if (request.getAttribute("listaPedido") != null) {
                            List<Pedido> listaPedido = (List<Pedido>) request.getAttribute("listaPedido");
                            for (Pedido pedido : listaPedido) {
                    %>
                    <tr>
                        <td><%= pedido.getIdPedido() %></td>
                        <td><%= pedido.getIdUsuario() %></td>
                        <td><%= pedido.getFechaPedido() %></td>
                        <td>
                            <% if ("pendiente".equals(pedido.getEstadoPedido())) { %>
                            <span class="badge bg-primary">Pendiente</span>
                            <% } else if ("procesado".equals(pedido.getEstadoPedido())) { %>
                            <span class="badge bg-secondary">Procesado</span>
                            <% } else if ("entregado".equals(pedido.getEstadoPedido())) { %>
                            <span class="badge bg-success">Entregado</span>
                            <% } else {%>
                            <span class="badge bg-danger">Cancelado</span>
                            <% } %>
                        </td>
                        <td class="action-btns">
                            <form action="${pageContext.request.contextPath}/proyecto/pedidos/<%= pedido.getIdPedido() %>" style="display: inline;">
                                <input type="submit" class="btn btn-info btn-sm" value="Ver Detalle" />
                            </form>
                            <form action="${pageContext.request.contextPath}/proyecto/pedidos/editar/<%= pedido.getIdPedido() %>" style="display: inline;">
                                <input type="submit" class="btn btn-warning btn-sm" value="Editar" />
                            </form>
                            <form action="${pageContext.request.contextPath}/proyecto/pedidos/borrar/" method="post" style="display: inline;">
                                <input type="hidden" name="__method__" value="delete"/>
                                <input type="hidden" name="codigo" value="<%= pedido.getIdPedido() %>"/>
                                <input type="submit" class="btn btn-danger btn-sm" value="Eliminar" />
                            </form>
                        </td>
                        <% } %>
                    </tr>
                    <%
                    } else {
                    %>
                    <tr>
                        <td colspan="5" class="no-users">No hay registros de Pedidos.</td>
                    </tr>
                    <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </section>
</main>

<%@ include file="/WEB-INF/jsp/fragmentos/footer.jspf" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
