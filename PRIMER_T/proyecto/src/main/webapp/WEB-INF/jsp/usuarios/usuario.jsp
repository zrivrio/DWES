<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page import="org.iesbelen.model.Usuario"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Usuarios</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@ include file="/WEB-INF/jsp/fragmentos/nav.jspf" %>

<main class="body_sec m-4">
  <section id="Content">
    <div class="container">
      <div class="d-flex justify-content-between align-items-center mb-4">
        <h1>Usuarios</h1>
        <% Usuario u = (Usuario) session.getAttribute("usuario-logado");
          String rol = (u != null) ? u.getRol() : " ";
          if ("administrador".equals(rol)) { %>
        <form action="${pageContext.request.contextPath}/proyecto/usuarios/crear">
          <input type="submit" class="btn btn-primary" value="Crear Usuario">
        </form>
        <% } %>
      </div>

      <!-- Tabla de Usuarios -->
      <div class="table-responsive">
        <table class="table table-striped table-bordered">
          <thead>
          <tr>
            <th>Código</th>
            <th>Usuario</th>
            <th>Rol</th>
            <% if ("administrador".equals(rol)) { %>
            <th>Acciones</th>
            <% } %>
          </tr>
          </thead>
          <tbody>
          <%
            if (request.getAttribute("listaUsuario") != null) {
              List<Usuario> listaUsuario = (List<Usuario>) request.getAttribute("listaUsuario");
              for (Usuario usuario : listaUsuario) {
          %>
          <tr>
            <td><%= usuario.getIdUsuario() %></td>
            <td><%= usuario.getNombre() %></td>
            <td>
              <% if ("administrador".equals(usuario.getRol())) { %>
              <span class="badge bg-primary">Administrador</span>
              <% } else if ("usuario".equals(usuario.getRol())) { %>
              <span class="badge bg-secondary">Usuario</span>
              <% } else {%>
              <span class="badge bg-success">Vendedor</span>
              <% } %>
            </td>
            <% if ("administrador".equals(rol)) { %>
            <td class="action-btns">
              <form action="${pageContext.request.contextPath}/proyecto/usuarios/<%= usuario.getIdUsuario() %>" style="display: inline;">
                <input type="submit" class="btn btn-info btn-sm" value="Ver Detalle" />
              </form>
              <form action="${pageContext.request.contextPath}/proyecto/usuarios/editar/<%= usuario.getIdUsuario() %>" style="display: inline;">
                <input type="submit" class="btn btn-warning btn-sm" value="Editar" />
              </form>
              <form action="${pageContext.request.contextPath}/proyecto/usuarios/borrar/" method="post" style="display: inline;">
                <input type="hidden" name="__method__" value="delete"/>
                <input type="hidden" name="codigo" value="<%= usuario.getIdUsuario() %>"/>
                <input type="submit" class="btn btn-danger btn-sm" value="Eliminar" />
              </form>
            </td>
            <% } %>
          </tr>
          <%
            }
          } else {
          %>
          <tr>
            <td colspan="5" class="no-users">No hay registros de Usuarios.</td>
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
