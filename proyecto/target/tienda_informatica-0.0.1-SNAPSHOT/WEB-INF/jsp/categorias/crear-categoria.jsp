<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Crear Usuario</title>
  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@ include file="/WEB-INF/jsp/fragmentos/nav.jspf" %>

<main class="container my-5">
  <section class="row justify-content-center">
    <div class="col-md-8">
      <div class="card shadow-sm">
        <div class="card-header text-center bg-primary text-white">
          <h1>Crear Categoria</h1>
        </div>
        <div class="card-body">
          <form action="${pageContext.request.contextPath}/proyecto/categorias/crear/" method="post">

            <!-- Nombre -->
            <div class="mb-3">
              <label for="nombre" class="form-label">Nombre</label>
              <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Ingrese su nombre" maxlength="25" required>
            </div>

            <!-- Botón Crear -->
            <div class="text-center">
              <input type="submit" class="btn btn-primary w-50" value="Crear">
            </div>
          </form>
        </div>
      </div>
    </div>
  </section>
</main>

<%@ include file="/WEB-INF/jsp/fragmentos/footer.jspf" %>

<!-- Bootstrap JS Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
