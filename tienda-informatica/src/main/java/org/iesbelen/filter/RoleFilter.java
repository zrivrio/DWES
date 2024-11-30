package org.iesbelen.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.iesbelen.model.Usuario;

import java.io.IOException;

public class RoleFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig)  throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httptResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession();
        Usuario usuario = (session != null) ? (Usuario) session.getAttribute("usuari-logado") : null;

        if(usuario == null || (!"administrador".equals(usuario.getRol()))) {
            httptResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Acceso denegado por falta de permisos");
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {}

}
