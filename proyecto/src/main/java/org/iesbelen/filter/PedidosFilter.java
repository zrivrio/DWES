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
        urlPatterns = {"/proyecto/*"},
        initParams = { @WebInitParam(name = "acceso-concedido-a-rol", value = "cliente,administrador,vendedor")}
)
public class PedidosFilter implements Filter {

    private String rolAcceso;

    public PedidosFilter() {
        super();
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.rolAcceso = filterConfig.getInitParameter("acceso-concedido-a-rol");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession();
        String url = httpRequest.getRequestURI().toString();

        Usuario usuario = (Usuario) session.getAttribute("usuario-logado");

        // Verificar si el usuario está logueado y tiene un rol adecuado
        if (usuario != null && (rolAcceso.contains(usuario.getRol()) || url.endsWith("/pedidos/crear"))) {
            // Permitir acceso a la página de creación de productos o roles específicos
            chain.doFilter(request, response);
        } else if (url.endsWith("/pedidos/") || url.endsWith("/pedidos/crear")
                || url.contains("/pedidos/editar")
                || url.contains("/pedidos/borrar")) {
            // Redirigir a login si el usuario no tiene acceso
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/proyecto/usuarios/login");
        } else {
            // Permitir el acceso si no hay restricción
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
