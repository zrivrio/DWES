package org.iesbelen.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.iesbelen.model.Usuario;

import java.io.IOException;

@WebFilter(
        urlPatterns = {"/proyecto/productos/*"},
        initParams = { @WebInitParam(name = "acceso-concedido-a-rol", value = "administrador,vendedor")}
)
public class ProductosFilter implements Filter {

    private String[] rolesAcceso;

    public ProductosFilter() {
        super();
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Dividir el parámetro de roles en un array para manejar múltiples roles
        String roles = filterConfig.getInitParameter("acceso-concedido-a-rol");
        rolesAcceso = roles.split(",");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession();
        String url = httpRequest.getRequestURI().toString();

        // Obtener el usuario logado de la sesión
        Usuario usuario = (Usuario) session.getAttribute("usuario-logado");

        // Verificar si el usuario está logado y tiene uno de los roles adecuados
        boolean accesoPermitido = false;
        if (usuario != null) {
            for (String rol : rolesAcceso) {
                if (usuario.getRol().equals(rol.trim())) {
                    accesoPermitido = true;
                    break;
                }
            }
        }

        // Permitir acceso a las URLs de creación, edición y eliminación si el rol es adecuado
        if (accesoPermitido) {
            chain.doFilter(request, response);
        } else if (url.endsWith("/productos/crear")
                || url.contains("/productos/editar")
                || url.contains("/productos/borrar")) {
            // Si no tiene acceso, redirigir a login
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/proyecto/usuarios/login");
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        // No es necesario hacer nada aquí en este caso
    }
}