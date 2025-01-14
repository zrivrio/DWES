<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Optional"%>
<%@ page import="org.iesbelen.model.Producto" %>
<%@ page import="org.iesbelen.model.Artista" %>
<%@ page import="java.util.List" %>
<%@ page import="org.iesbelen.model.Categoria" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Editar Producto</title>
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
<main class="body_sec">
	<section id="Content">
		<div id="contenedora" style="float:none; margin: 0 auto;width: 900px;">
			<form action="${pageContext.request.contextPath}/proyecto/productos/editar" method="post">
				<input type="hidden" name="__method__" value="put" />
				<div class="clearfix">
					<div style="float: left; width: 50%">
						<h1>Editar Producto</h1>
					</div>
					<div style="float: none;width: auto;overflow: hidden;min-height: 80px;position: relative;">
						<div style="position: absolute; left: 39%; top: 39%;">
							<input type="submit" class="btn btn-primary" value="Guardar Cambios" />
						</div>
					</div>
				</div>

				<div class="clearfix">
					<hr/>
				</div>

				<% Optional<Producto> producto = (Optional<Producto>) request.getAttribute("producto");
					if (producto.isPresent()) {
						List<Artista> listaArtista = (List<Artista>) request.getAttribute("listaArtista");
						List<Categoria> listaCategoria = (List<Categoria>) request.getAttribute("listaCategoria");
				%>

				<div class="mb-3">
					<label for="codigo" class="form-label">Código</label>
					<input type="text" id="codigo" name="idProducto" value="<%= producto.get().getIdProducto() %>" readonly class="form-control"/>
				</div>

				<div class="mb-3">
					<label for="nombre" class="form-label">Nombre</label>
					<input type="text" id="nombre" name="nombre" value="<%= producto.get().getNombre() %>" required class="form-control"/>
				</div>

				<div class="mb-3">
					<label for="precio" class="form-label">Precio</label>
					<input type="text" id="precio" name="precio" value="<%= producto.get().getPrecio() %>" required class="form-control"/>
				</div>

				<div class="mb-3">
					<label for="descripcion" class="form-label">Descripción</label>
					<textarea id="descripcion" name="descripcion" class="form-control" rows="3"><%= producto.get().getDescripcion() %></textarea>
				</div>

				<div class="mb-3">
					<label for="idArtista" class="form-label">Artista</label>
					<select id="idArtista" name="idArtista" class="form-select">
						<% for (Artista a : listaArtista) { %>
						<option value="<%= a.getIdArtista() %>"
								<%= a.getIdArtista() == producto.get().getIdArtista() ? "selected" : "" %>>
							<%= a.getNombre() %>
						</option>
						<% } %>
					</select>
				</div>

				<div class="mb-3">
					<label for="idCategoria" class="form-label">Categoría</label>
					<select id="idCategoria" name="idCategoria" class="form-select">
						<% for (Categoria c : listaCategoria) { %>
						<option value="<%= c.getIdCategoria() %>"
								<%= c.getIdCategoria() == producto.get().getIdCategoria() ? "selected" : "" %>>
							<%= c.getNombre() %>
						</option>
						<% } %>
					</select>
				</div>
				<%
					} %>
			</form>
		</div>
	</section>
</main>
</body>
<%@ include file="/WEB-INF/jsp/fragmentos/footer.jspf" %>
</html>