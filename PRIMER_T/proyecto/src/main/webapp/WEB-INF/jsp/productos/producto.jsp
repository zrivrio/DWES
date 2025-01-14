<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="org.iesbelen.model.Producto, org.iesbelen.model.Categoria, org.iesbelen.model.Artista" %>

<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Productos</title>
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
      <h1>Productos</h1>
      <!-- Filtros -->
      <form action="${pageContext.request.contextPath}/proyecto/productos/" method="get" class="d-flex gap-2">
        <!-- Filtro por Categoría -->
        <select name="idCategoria" class="form-select">
          <option value="" selected>Seleccionar categoría</option>
          <%
            List<Categoria> listaCategoria = (List<Categoria>) request.getAttribute("listaCategoria");
            if (listaCategoria != null) {
              for (Categoria categoria : listaCategoria) {
          %>
          <option value="<%= categoria.getIdCategoria() %>"><%= categoria.getNombre() %></option>
          <%
              }
            }
          %>
        </select>
        <!-- Filtro por Artista -->
        <select name="idArtista" class="form-select">
          <option value="" selected>Seleccionar artista</option>
          <%
            List<Artista> listaArtista = (List<Artista>) request.getAttribute("listaArtista");
            if (listaArtista != null) {
              for (Artista artista : listaArtista) {
          %>
          <option value="<%= artista.getIdArtista() %>"><%= artista.getNombre() %></option>
          <%
              }
            }
          %>
        </select>
        <button type="submit" class="btn btn-primary">Filtrar</button>
      </form>
      <!-- Crear producto -->
      <%
        Usuario usuario = (Usuario) session.getAttribute("usuario-logado");
        if (usuario != null && ("administrador".equals(usuario.getRol()) || "vendedor".equals(usuario.getRol()))) {
      %>
      <a href="${pageContext.request.contextPath}/proyecto/productos/crear" class="btn btn-success">Crear Producto</a>
      <% } %>
    </div>
    <!-- Productos -->
    <%
      List<Producto> listaProducto = (List<Producto>) request.getAttribute("listaProducto");
      if (listaProducto != null && !listaProducto.isEmpty()) {
    %>
    <div class="row g-4">
      <% for (Producto producto : listaProducto) { %>
      <div class="col-md-4">
        <div class="card shadow-lg h-100">
          <img src="<%= request.getContextPath() + "/assets/productos/" + producto.getIdProducto() + ".jpg" %>" alt="<%= producto.getNombre() %>" class="card-img-top">
          <div class="card-body d-flex flex-column">
            <h5 class="card-title"><%= producto.getNombre() %></h5>
            <p class="card-text">Precio: <strong>$<%= producto.getPrecio() %></strong></p>
            <div class="mt-auto d-flex gap-2">
              <a href="${pageContext.request.contextPath}/proyecto/productos/<%= producto.getIdProducto() %>" class="btn btn-info btn-sm">Ver Detalle</a>
              <form action="${pageContext.request.contextPath}/proyecto/carrito/" method="get">
                <input type="hidden" name="codigo" value="<%= producto.getIdProducto() %>">
                <button class="btn btn-success btn-sm">Añadir Al Carrito</button>
              </form>
              <% if (usuario != null && "administrador".equals(usuario.getRol())) { %>
              <a href="${pageContext.request.contextPath}/proyecto/productos/editar/<%= producto.getIdProducto() %>" class="btn btn-warning btn-sm">Editar</a>
              <form action="${pageContext.request.contextPath}/proyecto/productos/borrar/" method="post" onsubmit="return confirm('¿Estás seguro de que deseas eliminar este producto?')" class="d-inline">
                <input type="hidden" name="codigo" value="<%= producto.getIdProducto() %>">
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
    <p class="text-center text-muted">No hay productos disponibles.</p>
    <% } %>
  </section>
</main>
<%@ include file="/WEB-INF/jsp/fragmentos/footer.jspf" %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
</body>
</html>
