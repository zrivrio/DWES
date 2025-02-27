<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Optional"%>
<%@ page import="org.iesbelen.model.Categoria" %>
<%@ page import="org.iesbelen.model.Pedido" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Editar Categoria</title>
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
      <form action="${pageContext.request.contextPath}/proyecto/pedidos/editar" method="post">
        <input type="hidden" name="__method__" value="put" />
        <div class="clearfix">
          <div style="float: left; width: 50%">
            <h1>Editar Pedido</h1>
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

        <% Optional<Pedido> pedido = (Optional<Pedido>) request.getAttribute("pedido");
          if (pedido.isPresent()) {
        %>

        <div style="margin-top: 6px;" class="clearfix">
          <div style="float: left;width: 50%">
            <label>Código</label>
          </div>
          <div style="float: none;width: auto;overflow: hidden;">
            <input type="text" name="id" value="<%= pedido.get().getIdPedido() %>" readonly="readonly" class="form-control"/>
          </div>
        </div>

        <div style="margin-top: 6px;" class="clearfix">
          <div style="float: left;width: 50%">
            <label>Id Usuario</label>
          </div>
          <div style="float: none;width: auto;overflow: hidden;">
            <input type="text" name="idUsuario" value="<%= pedido.get().getIdUsuario() %>" class="form-control"/>
          </div>
        </div>

        <div style="margin-top: 6px;" class="clearfix">
          <div style="float: left;width: 50%">
            <label>Fecha Pedido</label>
          </div>
          <div style="float: none;width: auto;overflow: hidden;">
            <input type="date" name="fecha" value="<%= pedido.get().getFechaPedido() %>" class="form-control"/>
          </div>
        </div>

        <div style="margin-top: 6px;" class="clearfix">
          <div style="float: left;width: 50%">
            <label>Estado Pedido</label>
          </div>
          <div style="float: none;width: auto;overflow: hidden;">
            <input type="text" name="estado" value="<%= pedido.get().getEstadoPedido() %>" class="form-control"/>
          </div>
        </div>
        <% } %>
      </form>
    </div>
  </section>
</main>
</body>
<%@ include file="/WEB-INF/jsp/fragmentos/footer.jspf" %>
</html>