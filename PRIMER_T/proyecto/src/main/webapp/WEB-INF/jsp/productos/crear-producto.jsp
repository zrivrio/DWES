<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="org.iesbelen.model.Artista"%>
<%@ page import="org.iesbelen.model.Categoria"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Crear Producto</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@ include file="/WEB-INF/jsp/fragmentos/nav.jspf" %>
<main class="container my-5">
    <h1 class="text-center">Crear Producto</h1>
    <% List<Artista> listaArtista = (List<Artista>) request.getAttribute("listaArtista"); %>
    <% List<Categoria> listaCategoria = (List<Categoria>) request.getAttribute("listaCategoria"); %>
    <form action="${pageContext.request.contextPath}/proyecto/productos/crear" method="post">
        <div class="mb-3">
            <label for="nombre" class="form-label">Nombre</label>
            <input type="text" id="nombre" name="nombre" required class="form-control"/>
        </div>
        <div class="mb-3">
            <label for="precio" class="form-label">Precio</label>
            <input type="number" id="precio" name="precio" min="0" step="0.01" required class="form-control"/>
        </div>
        <div class="mb-3">
            <label for="descripcion" class="form-label">Descripción</label>
            <textarea id="descripcion" name="descripcion" class="form-control" rows="3"></textarea>
        </div>
        <div class="mb-3">
            <label for="idArtista" class="form-label">Artista</label>
            <select id="idArtista" name="idArtista" class="form-select">
                <option value="null"> -Seleciona a un Artista- </option>
                <% for (Artista a : listaArtista) { %>
                <option value="<%= a.getIdArtista() %>">
                    <%= a.getNombre() %>
                </option>
                <% } %>
            </select>
        </div>
        <div class="mb-3">
            <label for="idCategoria" class="form-label">Categoría</label>
            <select id="idCategoria" name="idCategoria" class="form-select">
                <option value="null"> -Seleciona una Categoria- </option>
                <% for (Categoria c : listaCategoria) { %>
                <option value="<%= c.getIdCategoria() %>">
                    <%= c.getNombre() %>
                </option>
                <% } %>
            </select>
        </div>
        <div class="text-center">
            <button type="submit" class="btn btn-primary w-50">Crear Producto</button>
        </div>
    </form>
</main>
<%@ include file="/WEB-INF/jsp/fragmentos/footer.jspf" %>
</body>
</html>
