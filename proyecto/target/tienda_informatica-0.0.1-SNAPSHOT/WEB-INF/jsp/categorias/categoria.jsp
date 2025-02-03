<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="org.iesbelen.model.Categoria" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Categorias</title>
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
        <div class="d-flex justify-content-between align-items-center mb-3">
            <h1>Categorias</h1>
            <%
                Usuario usuario = (Usuario) session.getAttribute("usuario-logado");
                if (usuario != null && "administrador".equals(usuario.getRol())) {
            %>
            <a href="${pageContext.request.contextPath}/proyecto/categorias/crear" class="btn btn-success">Crear Categoria</a>
            <% } %>
        </div>

        <!-- Lista de Categorias -->
        <%
            List<Categoria> listaCategoria = (List<Categoria>) request.getAttribute("listaCategoria");
            if (listaCategoria != null && !listaCategoria.isEmpty()) {
        %>
        <div class="row g-4">
            <% for (Categoria categoria : listaCategoria) { %>
            <div class="col-md-4">
                <div class="card shadow-lg h-100">
                    <div class="card-body d-flex flex-column">
                        <h5 class="card-title"><%= categoria.getNombre() %></h5>
                        <div class="mt-auto d-flex gap-2">
                            <% if (usuario != null && "administrador".equals(usuario.getRol())) { %>
                            <a href="${pageContext.request.contextPath}/proyecto/categorias/editar/<%= categoria.getIdCategoria() %>" class="btn btn-warning btn-sm">Editar</a>
                            <form action="${pageContext.request.contextPath}/proyecto/categorias/borrar/" method="post" onsubmit="return confirm('¿Estás seguro de que deseas eliminar este categoria?')" class="d-inline">
                                <input type="hidden" name="codigo" value="<%= categoria.getIdCategoria() %>">
                                <button type="submit" class="btn btn-danger btn-sm">Eliminar</button>
                            </form>
                            <% } %>
                        </div>
                    </div>
                </div>
            </div>
            <% } %>
        </div>
        <% } else { %>
        <p class="text-center text-muted">No hay categorias disponibles.</p>
        <% } %>
    </section>
</main>
<%@ include file="/WEB-INF/jsp/fragmentos/footer.jspf" %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
</body>
</html>
