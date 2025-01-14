<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Optional"%>
<%@ page import="org.iesbelen.model.Producto" %>
<%@ page import="org.iesbelen.model.Categoria" %>
<%@ page import="org.iesbelen.model.Artista" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Detalle Producto</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

  <style>
    .clearfix::after {
      content: "";
      display: block;
      clear: both;
    }
  </style>
</head>
<body>
<%@ include file="/WEB-INF/jsp/fragmentos/nav.jspf" %>
<main class="container py-5">
  <section id="Content">
    <div class="row">
      <div class="col-md-6">
        <h1>Detalle Producto</h1>
      </div>
      <div class="col-md-6 text-end">
        <form action="${pageContext.request.contextPath}/proyecto/productos/" >
          <input type="submit" value="Volver" class="btn btn-secondary" />
        </form>
      </div>
    </div>

    <hr/>

    <% Optional<Producto> producto = (Optional<Producto>) request.getAttribute("producto");
      if (producto.isPresent()) {
        Producto prod = producto.get();
        List<Artista> listaArtista = (List<Artista>) request.getAttribute("listaArtista");
        List<Categoria> listaCategoria = (List<Categoria>) request.getAttribute("listaCategoria");

        // Buscar el artista y la categoría para el producto
        Artista artista = null;
        for (Artista a : listaArtista) {
          if (prod.getIdArtista() == a.getIdArtista()) {
            artista = a;
            break;
          }
        }

        Categoria categoria = null;
        for (Categoria c : listaCategoria) {
          if (prod.getIdCategoria() == c.getIdCategoria()) {
            categoria = c;
            break;
          }
        }
    %>

    <div class="row mb-3">
      <div class="col-md-4">
        <label><strong>Código</strong></label>
      </div>
      <div class="col-md-8">
        <span class="form-control-plaintext"><%= producto.get().getIdProducto() %></span>
      </div>
    </div>

    <div class="row mb-3">
      <div class="col-md-4">
        <label><strong>Nombre</strong></label>
      </div>
      <div class="col-md-8">
        <span class="form-control-plaintext"><%= producto.get().getNombre() %></span>
      </div>
    </div>

    <div class="row mb-3">
      <div class="col-md-4">
        <label><strong>Precio</strong></label>
      </div>
      <div class="col-md-8">
        <span class="form-control-plaintext"><%= producto.get().getPrecio() %></span>
      </div>
    </div>

    <div class="row mb-3">
      <div class="col-md-4">
        <label><strong>Descripción</strong></label>
      </div>
      <div class="col-md-8">
        <span class="form-control-plaintext"><%= producto.get().getDescripcion() %></span>
      </div>
    </div>

    <div class="row mb-3">
      <div class="col-md-4">
        <label><strong>Artista</strong></label>
      </div>
      <div class="col-md-8">
        <span class="form-control-plaintext"><%= artista != null ? artista.getNombre() : "No disponible" %></span>
      </div>
    </div>

    <div class="row mb-3">
      <div class="col-md-4">
        <label><strong>Categoría</strong></label>
      </div>
      <div class="col-md-8">
        <span class="form-control-plaintext"><%= categoria != null ? categoria.getNombre() : "No disponible" %></span>
      </div>
    </div>

    <% } %>
  </section>
</main>

<%@ include file="/WEB-INF/jsp/fragmentos/footer.jspf" %>
</body>
</html>