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
        urlPatterns = {"/proyecto/artistas/*"},
        initParams = { @WebInitParam(name = "acceso-concedido-a-rol", value = "administrador")}
)

public class ArtistasFilter implements Filter {

    private String rolAcceso;

    public ArtistasFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
       this.rolAcceso = filterConfig.getInitParameter("acceso-concedido-a-rol");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest =(HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse)response;

        HttpSession session = httpRequest.getSession();
        String url = httpRequest.getRequestURI().toString();

        Usuario usuarios = null;

        if(session != null
                && (usuarios = (Usuario) session.getAttribute("usuario-logado")) != null
                && rolAcceso.equals(usuarios.getRol())) {
            chain.doFilter(request, response);
        } else if (url.endsWith("/artistas/crear")
        || url.contains("/artistas/editar")
        || url.contains("/artistas/borrar")) {

            httpResponse.sendRedirect(httpRequest.getContextPath() + "/proyecto/usuarios/login");
            return;
        }else {
            chain.doFilter(request, response);
        }
    }


    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
