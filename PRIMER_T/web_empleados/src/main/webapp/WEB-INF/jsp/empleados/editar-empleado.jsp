<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page import="java.util.Optional"%>
<%@ page import="org.iesbelen.model.Empleado" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Empleado</title>
    <style>
        .clearfix::after {
            content: "";
            display: block;
            clear: both;
        }
    </style>
</head>
<body>
<main class = "body_sec">
    <section  id="Content">
        <div id="contenedora" style="float:none; margin: 0 auto;width: 900px;" >
            <form action="${pageContext.request.contextPath}/empresa/empleados/editar/" method="post" >
                <input type="hidden" name="__method__" value="put" />
                <div class="clearfix">
                    <div style="float: left; width: 50%">
                        <h1>Editar Empleado</h1>
                    </div>
                    <div style="float: none;width: auto;overflow: hidden;min-height: 80px;position: relative;">

                        <div style="position: absolute; left: 39%; top : 39%;">
                            <input type="submit" value="Guardar" />
                        </div>

                    </div>
                </div>

                <div class="clearfix">
                    <hr/>
                </div>

                    <% 	Optional<Empleado> empleado = (Optional<Empleado>) request.getAttribute("empleado");
                        if(empleado.isPresent()) {
                     %>

                <div style="margin-top: 6px;" class="clearfix">
                    <div style="float: left;width: 50%">
                        <label>Código</label>
                    </div>
                    <div style="float: none;width: auto;overflow: hidden;">
                        <input value="<%= empleado.get().getCodigo() %>" readonly="readonly" />
                    </div>
                </div>
                <div style="margin-top: 6px;" class="clearfix">
                    <div style="float: left;width: 50%">
                        <label>Nombre</label>
                    </div>
                    <div style="float: none;width: auto;overflow: hidden;">
                        <input name="nombre" value="<%= empleado.get().getNombre() %>" />
                    </div>
                    <div style="float: left;width: 50%">
                        <label>NIF</label>
                    </div>
                    <div style="float: none;width: auto;overflow: hidden;">
                        <input name="nif" value="<%= empleado.get().getNif() %>" />
                    </div>
                    <div style="float: left;width: 50%">
                        <label>Apellido1</label>
                    </div>
                    <div style="float: none;width: auto;overflow: hidden;">
                        <input name="apellido1" value="<%= empleado.get().getApellido1() %>" />
                    </div>
                    <div style="float: left;width: 50%">
                    <label>Apellido2</label>
                </div>
                    <div style="float: none;width: auto;overflow: hidden;">
                        <input name="apellido2" value="<%= empleado.get().getApellido2() %>" />
                    </div>
                    <div style="float: left;width: 50%">
                        <label>Godigo Departamento</label>
                    </div>
                    <div style="float: none;width: auto;overflow: hidden;">
                        <input name="codigoDepartamento" value="<%= empleado.get().getCodigoDepartamento() %>" />
                    </div>
                </div>

                    <% 	} else { %>

                request.sendRedirect("empleados/");

                    <% 	} %>

        </div>
    </section>
</main>
</body>
</html>