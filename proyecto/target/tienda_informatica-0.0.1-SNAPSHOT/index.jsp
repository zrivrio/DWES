<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Seleccionar Vista</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
    <style>
        .card-equal {
            display: flex;
            flex-direction: column;
            justify-content: space-between;
            height: 100%;
        }
        .card-img-top {
            object-fit: cover;
            max-height: 200px;
        }
    </style>
</head>
<body>
<%@ include file="/WEB-INF/jsp/fragmentos/nav.jspf" %>
<div class="container vh-100 d-flex flex-column justify-content-center align-items-center">
    <h1 class="mb-5 text-center">¿Qué deseas explorar?</h1>
    <div class="row w-100">
        <!-- Sección de Productos -->
        <div class="col-md-4 text-center mb-4">
            <a href="<%=application.getContextPath()%>/proyecto/productos/" class="text-decoration-none">
                <div class="card shadow-lg card-equal">
                    <img src="<%=application.getContextPath()%>/assets/images/productos.jpg" alt="Explorar Productos" class="card-img-top">
                    <div class="card-body">
                        <h3 class="card-title">Productos</h3>
                        <p class="card-text">Explora nuestros productos disponibles para ti.</p>
                        <button class="btn btn-primary">Ir a Productos</button>
                    </div>
                </div>
            </a>
        </div>

        <!-- Sección de Categorías -->
        <div class="col-md-4 text-center mb-4">
            <a href="<%=application.getContextPath()%>/proyecto/categorias/" class="text-decoration-none">
                <div class="card shadow-lg card-equal">
                    <img src="<%=application.getContextPath()%>/assets/images/categorias.webp" alt="Navegar Categorías" class="card-img-top">
                    <div class="card-body">
                        <h3 class="card-title">Categorías</h3>
                        <p class="card-text">Navega por nuestras categorías.</p>
                        <button class="btn btn-secondary">Ir a Categorías</button>
                    </div>
                </div>
            </a>
        </div>

        <!-- Sección de Artistas -->
        <div class="col-md-4 text-center mb-4">
            <a href="<%=application.getContextPath()%>/proyecto/artistas/" class="text-decoration-none">
                <div class="card shadow-lg card-equal">
                    <img src="<%=application.getContextPath()%>/assets/images/artistas.jpg" alt="Explorar Artistas" class="card-img-top">
                    <div class="card-body">
                        <h3 class="card-title">Artistas</h3>
                        <p class="card-text">Descubre nuestros artistas destacados.</p>
                        <button class="btn btn-info">Ir a Artistas</button>
                    </div>
                </div>
            </a>
        </div>

        <%
            if (usuarioLogado != null && "administrador".equals(usuarioLogado.getRol())) {
        %>
        <!-- Sección de Usuarios -->
        <div class="col-md-4 text-center mb-4">
            <a href="<%=application.getContextPath()%>/proyecto/usuarios/" class="text-decoration-none">
                <div class="card shadow-lg card-equal">
                    <img src="<%=application.getContextPath()%>/assets/images/usuarios.jpg" alt="Gestionar Usuarios" class="card-img-top">
                    <div class="card-body">
                        <h3 class="card-title">Usuarios</h3>
                        <p class="card-text">Gestiona y edita los usuarios registrados.</p>
                        <button class="btn btn-success">Ir a Usuarios</button>
                    </div>
                </div>
            </a>
        </div>
        <div class="col-md-4 text-center mb-4">
            <a href="<%=application.getContextPath()%>/proyecto/pedidos/" class="text-decoration-none">
                <div class="card shadow-lg card-equal">
                    <img src="<%=application.getContextPath()%>/assets/images/pedidos.png" alt="Gestionar Pedidos" class="card-img-top">
                    <div class="card-body">
                        <h3 class="card-title">Pedidos</h3>
                        <p class="card-text">Gestiona y actualiza los pedidos registrados.</p>
                        <button class="btn btn-dark">Ir a Pedidos</button>
                    </div>
                </div>
            </a>
        </div>
        <%
            }
        %>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/fragmentos/footer.jspf" %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
