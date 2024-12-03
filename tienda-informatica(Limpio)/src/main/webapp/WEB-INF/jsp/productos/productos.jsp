<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="org.iesbelen.model.Fabricante"%>
<%@page import="java.util.List"%>
<%@ page import="org.iesbelen.model.Producto" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Productos</title>
	<style>
		.clearfix::after {
			content: "";
			display: block;
			clear: both;
		}
	</style>
</head>
<body>
	<div id="contenedora" style="float:none; margin: 0 auto;width: 900px;" >
		<div class="clearfix">
			<div style="float: left; width: 50%">
				<h1>Productos</h1>
			</div>
			<div style="float: none;width: auto;overflow: hidden;min-height: 80px;position: relative;">

				<div style="position: absolute; left: 39%; top : 39%;">

						<form action="${pageContext.request.contextPath}/tienda/productos/crear">
							<input type="submit" value="Crear">
						</form>
					</div>

			</div>
		</div>

		<form action="${pageContext.request.contextPath}/tienda/productos/" method="get">
			<input type="search" name="filtrar-por-nombre" class="form-control me-2" placeholder="Busca un producto">
			<button type="submit" class="btn btn-outline-secondary">Filtrar</button>
		</form>

		<div class="clearfix">
			<hr/>
		</div>
		<div class="clearfix">
			<div style="float: left;width: 10%">Código</div>
			<div style="float: left;width: 30%">Nombre</div>
			<div style="float: left;width: 20%">Precio</div>
			<div style="float: left;width: 20%;overflow: hidden;">Acción</div>
		</div>
		<div class="clearfix">
			<hr/>
		</div>
			<%
    		List<Producto> listaProducto = (List<Producto>) request.getAttribute("listaProductos");
		if (listaProducto != null && !listaProducto.isEmpty()) { // Si la lista no está vacía
        for (Producto producto : listaProducto) {
%>
		<div style="margin-top: 6px;" class="clearfix">
			<div style="float: left;width: 10%"><%= producto.getIdProducto() %></div>
			<div style="float: left;width: 30%"><%= producto.getNombre() %></div>
			<div style="float: left;width: 20%"><%= producto.getPrecio() %></div>
			<div style="float: none;width: auto;overflow: hidden;">
				<form action="${pageContext.request.contextPath}/tienda/productos/<%= producto.getIdProducto()%>" style="display: inline;">
					<input type="submit" value="Ver Detalle" />
				</form>
				<form action="${pageContext.request.contextPath}/tienda/productos/editar/<%= producto.getIdProducto()%>" style="display: inline;">
					<input type="submit" value="Editar" />
				</form>
				<form action="${pageContext.request.contextPath}/tienda/productos/borrar/" method="post" style="display: inline;">
					<input type="hidden" name="__method__" value="delete"/>
					<input type="hidden" name="codigo" value="<%= producto.getIdProducto()%>"/>
					<input type="submit" value="Eliminar" />
				</form>
			</div>
		</div>
			<%
        }
    } else { // Si la lista está vacía
%>
		<div>No se encontraron productos.</div>
			<%
    }
%>
</body>
</html>