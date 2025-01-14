<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page import="org.iesbelen.dto.DepartamentoDTO"%>
<%@page import="java.util.List"%>
<%@ page import="org.iesbelen.model.Departamento" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Departamentos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

</head>
<body>
<main class="body_sec">
    <section id="Content">
        <div class="form-container">
            <div class="clearfix mb-4">
                <div class="d-flex justify-content-between">
                    <h1>Departamento</h1>
                    <form action="${pageContext.request.contextPath}/empresa/departamentos/crear">
                        <input type="submit" class="btn btn-primary" value="Crear Departamento">
                    </form>
                </div>
            </div>
            <hr/>
            <form class="d-flex mb-3" action="${pageContext.request.contextPath}/empresa/departamentos/" method="get">
                <input type="search" name="rangoI" class="form-control me-2" placeholder="Rango Inicial">
                <input type="search" name="rangoF" class="form-control me-2" placeholder="Rango Final">
                <button type="submit" class="btn btn-outline-secondary">Filtrar</button>
            </form>
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
                        if (request.getAttribute("listaDepartamentos") != null) {
                            List<DepartamentoDTO> listaDepartamentos = (List<DepartamentoDTO>) request.getAttribute("listaDepartamentos");

                            for (DepartamentoDTO departamento : listaDepartamentos) {
                    %>
                    <tr>
                        <td><%= departamento.getCodigo() %></td>
                        <td><%= departamento.getNombre() %></td>
                        <td><%= departamento.getPresupuestos() %></td>
                        <td><%= departamento.getGastos() %></td>
                        <td><%= departamento.getnEmpleados() %></td>
                        <td class="text-center">
                            <form style="display: inline;">
                                <input type="submit" class="btn btn-info btn-sm" value="Ver Detalle" />
                            </form>
                            <form  style="display: inline;">
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