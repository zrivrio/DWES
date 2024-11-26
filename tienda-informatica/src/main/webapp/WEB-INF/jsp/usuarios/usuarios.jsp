<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page import="org.iesbelen.model.Fabricante"%>
<%@page import="java.util.List"%>
<%@ page import="org.iesbelen.model.Producto" %>
<%@ page import="org.iesbelen.model.Usuario" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Usuarios</title>
    <style>
        .clearfix::after {
            content: "";
            display: block;
            clear: both;
        }
    </style>
    <%@ include file="/WEB-INF/jsp/fragmentos/estilo.jspf" %>
</head>
<body>
<%@ include file="/WEB-INF/jsp/fragmentos/header.jspf" %>
<%@ include file="/WEB-INF/jsp/fragmentos/nav.jspf" %>
<main class = "body_sec">
    <section id="Content">
        <div id="contenedora" style="float:none; margin: 0 auto;width: 900px;" >
            <div class="clearfix">
                <div style="float: left; width: 50%">
                    <h1>Usuarios</h1>
                </div>
                <div style="float: none;width: auto;overflow: hidden;min-height: 80px;position: relative;">

                    <div style="position: absolute; left: 39%; top : 39%;">

                        <form action="${pageContext.request.contextPath}/tienda/usuarios/crear">
                            <input type="submit" value="Crear">
                        </form>
                    </div>

                </div>
            </div>
            <div class="clearfix">
                <hr/>
            </div>
            <div class="clearfix">
                <div style="float: left;width: 10%">Código</div>
                <div style="float: left;width: 30%">Usuario</div>
                <div style="float: left;width: 20%">Contraseña</div>
                <div style="float: left;width: 20%">Rol</div>
                <div style="float: left;width: 20%;overflow: hidden;">Accion</div>
            </div>
            <div class="clearfix">
                <hr/>
            </div>
            <%
                if (request.getAttribute("listaUsuario") != null) {
                    List<Usuario>listaUsuario = (List<Usuario>)request.getAttribute("listaUsuario");

                    for (Usuario usuario : listaUsuario) {
            %>

            <div style="margin-top: 6px;" class="clearfix">
                <div style="float: left;width: 10%"><%= usuario.getIdUsuario()%></div>
                <div style="float: left;width: 30%"><%= usuario.getUsuario()%></div>
                <div style="float: left;width: 20%"><%= usuario.getPassword().substring(0,4)%></div>
                <div style="float: left;width: 20%"><%= usuario.getRol()%></div>

                <div style="float: none;width: auto;overflow: hidden;">
                    <form action="${pageContext.request.contextPath}/tienda/usuarios/<%= usuario.getIdUsuario()%>" style="display: inline;">
                        <input type="submit" value="Ver Detalle" />
                    </form>
                    <form action="${pageContext.request.contextPath}/tienda/usuarios/editar/<%= usuario.getIdUsuario()%>" style="display: inline;">
                        <input type="submit" value="Editar" />
                    </form>
                    <form action="${pageContext.request.contextPath}/tienda/usuarios/borrar/" method="post" style="display: inline;">
                        <input type="hidden" name="__method__" value="delete"/>
                        <input type="hidden" name="codigo" value="<%= usuario.getIdUsuario()%>"/>
                        <input type="submit" value="Eliminar" />
                    </form>
                </div>
            </div>
            <%
                }
            } else {
            %>
            No hay registros de Usuarios
            <% } %>
        </div>
    </section>
</main>
</body>
<%@ include file ="/WEB-INF/jsp/fragmentos/footer.jspf"%>
</html>