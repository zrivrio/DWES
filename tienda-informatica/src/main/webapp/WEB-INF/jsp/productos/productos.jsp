<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="org.iesbelen.model.Fabricante"%>
<%@page import="java.util.List"%>
<%@ page import="org.iesbelen.model.Producto" %>


<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Usuarios</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
	<style>
		.clearfix::after {
			content: "";
			display: block;
			clear: both;
		}
		.form-container {
			max-width: 900px;
			margin: 30px auto;
		}
		.user-actions form {
			display: inline-block;
			margin-right: 10px;
		}
		.no-users {
			text-align: center;
			font-size: 18px;
			color: #777;
		}
		.table th, .table td {
			text-align: center;
			vertical-align: middle;
		}
		.table {
			margin-top: 20px;
		}
		.btn-sm {
			padding: 5px 10px;
		}
	</style>
	<%@ include file="/WEB-INF/jsp/fragmentos/estilo.jspf" %>
</head>
<body>

<%@ include file="/WEB-INF/jsp/fragmentos/header.jspf" %>
<%@ include file="/WEB-INF/jsp/fragmentos/nav.jspf" %>

<main class="body_sec m-4">
	<section id="Content">
		<div class="form-container">
			<div class="clearfix mb-4">
				<div style="float: left; width: 50%">
					<h1>Usuarios</h1>
				</div>
				<% Usuario usuario = (Usuario) session.getAttribute("usuario-logado");
					String rol = (usuario != null) ? usuario.getRol() : " ";
					if ("administrador".equals(rol)) { %>
				<div style="float: none;width: auto; overflow: hidden;">
					<form action="${pageContext.request.contextPath}/tienda/usuarios/crear" style="float: right;">
						<input type="submit" class="btn btn-primary" value="Crear Usuario">
					</form>
				</div>
				<% } %>
			</div>

			<div class="clearfix mb-4">
				<form action="${pageContext.request.contextPath}/tienda/usuarios/" method="get" class="d-flex justify-content-end">
					<input type="search" name="filtrar-por-nombre" class="form-control me-2" placeholder="Filtrar por nombre">
					<button type="submit" class="btn btn-secondary">Filtrar</button>
				</form>
			</div>

			<div class="table-responsive">
				<table class="table table-bordered">
					<thead>
					<tr>
						<th style="width: 10%;">Código</th>
						<th style="width: 30%;">Usuario</th>
						<th style="width: 20%;">Contraseña</th>
						<th style="width: 20%;">Rol</th>
						<% if ("administrador".equals(rol)) { %>
						<th style="width: 20%;">Acciones</th>
						<% } %>
					</tr>
					</thead>
					<tbody>
					<%
						if (request.getAttribute("listaUsuario") != null) {
							List<Usuario> listaUsuario = (List<Usuario>) request.getAttribute("listaUsuario");
							for (Usuario usuarioItem : listaUsuario) {
					%>
					<tr>
						<td><%= usuarioItem.getIdUsuario() %></td>
						<td><%= usuarioItem.getUsuario() %></td>
						<td><%= usuarioItem.getPassword().substring(0, 4) %></td>
						<td><%= usuarioItem.getRol() %></td>
						<% if ("administrador".equals(rol)) { %>
						<td class="user-actions">
							<form action="${pageContext.request.contextPath}/tienda/usuarios/<%= usuarioItem.getIdUsuario() %>" style="display: inline;">
								<input type="submit" class="btn btn-info btn-sm" value="Ver Detalle" />
							</form>
							<form action="${pageContext.request.contextPath}/tienda/usuarios/editar/<%= usuarioItem.getIdUsuario() %>" style="display: inline;">
								<input type="submit" class="btn btn-warning btn-sm" value="Editar" />
							</form>
							<form action="${pageContext.request.contextPath}/tienda/usuarios/borrar/" method="post" style="display: inline;">
								<input type="hidden" name="__method__" value="delete"/>
								<input type="hidden" name="codigo" value="<%= usuarioItem.getIdUsuario() %>"/>
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

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-pzjw8f+ua7Kw1TIq0+Wv0vQWzFhVAqOG7xzw8lLa+d/b9huL3n2Qv3wUDeYsZkwv" crossorigin="anonymous"></script>
</body>
</html>