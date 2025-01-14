<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Pedido Realizado</title>

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
</head>
<body>

<%@ include file="/WEB-INF/jsp/fragmentos/nav.jspf" %>

<div class="content">

  <div class="alert alert-success" role="alert">
    <h4 class="alert-heading">¡Pedido Realizado con Éxito!</h4>
    <p>Tu pedido ha sido realizado con éxito y está en proceso. Puedes ver un resumen de tu pedido a continuación.</p>
    <hr>
    <p class="mb-0">Gracias por confiar en nosotros. ¡Nos encargaremos de que tu pedido llegue pronto!</p>
  </div>


  <!-- Actions -->
  <div class="mt-4 text-center">
    <a href="${pageContext.request.contextPath}//proyecto/carrito/" class="btn btn-primary">Volver a Carrito</a>
    <a href="${pageContext.request.contextPath}/proyecto/productos/" class="btn btn-success">Ir al Productos</a>
  </div>
</div>

<%@ include file="/WEB-INF/jsp/fragmentos/footer.jspf" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
</body>
</html>
