<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page import="org.iesbelen.dto.DepartamentoDTO"%>
<%@page import="java.util.List"%>
<%@ page import="org.iesbelen.model.Departamento" %>
<%@ page import="org.iesbelen.model.Empleado" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Empleado</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

</head>
<body>
<main class="body_sec">
    <section id="Content">
        <div class="form-container">
            <div class="clearfix mb-4">
                <div class="d-flex justify-content-between">
                    <h1>Empleado</h1>
                    <form >
                        <input type="submit" class="btn btn-primary" value="Crear Empleado">
                    </form>
                </div>
            </div>
            <hr/>
            <div class="table-responsive">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th style="width: 10%;">Código</th>
                        <th style="width: 30%;">Nombre</th>
                        <th style="width: 20%;">Presupuestos</th>
                        <th style="width: 20%;">Gastos</th>
                        <th style="width: 20%;">Nº Empleados</th>
                        <th>Acciones</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        if (request.getAttribute("listaempleados") != null) {
                            List<Empleado> listaEmpleados = (List<Empleado>) request.getAttribute("listaempleados");

                            for (Empleado empleado : listaEmpleados) {

                                if (empleado.getApellido2() == null){
                                    empleado.setApellido2(" ");
                                }
                    %>
                    <tr>
                        <td><%= empleado.getCodigo() %></td>
                        <td><%= empleado.getNif() %></td>
                        <td><%= empleado.getNombre() %></td>
                        <td><%= empleado.getApellido1() %></td>
                        <td><%= empleado.getApellido2() %></td>
                        <td><%= empleado.getCodigoDepartamento() %></td>
                        <td class="text-center">
                            <form style="display: inline;">
                                <input type="submit" class="btn btn-info btn-sm" value="Ver Detalle" />
                            </form>
                            <form action="${pageContext.request.contextPath}/empresa/empleados/editar/<%= empleado.getCodigo() %>"  style="display: inline;">
                                <input type="submit" class="btn btn-warning btn-sm" value="Editar" />
                            </form>
                            <form style="display: inline;">
                                <input type="submit" class="btn btn-danger btn-sm" value="Eliminar" />
                            </form>
                        </td>
                    </tr>
                    <%
                        }
                    } else {
                    %>
                    <tr>
                        <td colspan="4" class="text-center">No hay registros de departamentos</td>
                    </tr>
                    <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </section>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-pzjw8f+ua7Kw1TIq0+Wv0vQWzFhVAqOG7xzw8lLa+d/b9huL3n2Qv3wUDeYsZkwv" crossorigin="anonymous"></script>
</body>
</html>