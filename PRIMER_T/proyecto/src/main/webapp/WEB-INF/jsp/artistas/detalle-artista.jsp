<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Optional"%>
<%@ page import="org.iesbelen.model.Artista" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Detalle Artista</title>
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
            <div class="clearfix">
                <div style="float: left; width: 50%">
                    <h1>Detalle Usuario</h1>
                </div>
                <div style="float: none;width: auto;overflow: hidden;min-height: 80px;position: relative;">
                    <div style="position: absolute; left: 39%; top : 39%;">
                        <form action="${pageContext.request.contextPath}/proyecto/artistas/" >
                            <input type="submit" value="Volver" class="btn btn-secondary" />
                        </form>
                    </div>
                </div>
            </div>
            <div class="clearfix">
                <hr/>
            </div>
            <% Optional<Artista> artista = (Optional<Artista>) request.getAttribute("artista");
                if (artista.isPresent()) {
            %>
            <div style="margin-top: 6px;" class="clearfix">
                <div style="float: left;width: 50%">
                    <label><strong>Código</strong></label>
                </div>
                <div style="float: none;width: auto;overflow: hidden;">
                    <span class="form-control-plaintext"><%= artista.get().getIdArtista() %></span>
                </div>
            </div>

            <div style="margin-top: 6px;" class="clearfix">
                <div style="float: left;width: 50%">
                    <label><strong>Nombre</strong></label>
                </div>
                <div style="float: none;width: auto;overflow: hidden;">
                    <span class="form-control-plaintext"><%= artista.get().getNombre() %></span>
                </div>
            </div>

            <div style="margin-top: 6px;" class="clearfix">
                <div style="float: left;width: 50%">
                    <label><strong>Descripcion</strong></label>
                </div>
                <div style="float: none;width: auto;overflow: hidden;">
                    <span class="form-control-plaintext"><%= artista.get().getDescripcion() %></span>
                </div>
            </div>

            <div style="margin-top: 6px;" class="clearfix">
                <div style="float: left;width: 50%">
                    <label><strong>Nacionalidad</strong></label>
                </div>
                <div style="float: none;width: auto;overflow: hidden;">
                    <span class="form-control-plaintext"><%= artista.get().getNacionalidad() %></span>
                </div>
            </div>

            <div style="margin-top: 6px;" class="clearfix">
                <div style="float: left;width: 50%">
                    <label><strong>Año de Inicio</strong></label>
                </div>
                <div style="float: none;width: auto;overflow: hidden;">
                    <span class="form-control-plaintext"><%= artista.get().getAnioInicio() %></span>
                </div>
            </div>

            <% } %>
        </div>
    </section>
</main>
</body>
<%@ include file="/WEB-INF/jsp/fragmentos/footer.jspf" %>
</html>