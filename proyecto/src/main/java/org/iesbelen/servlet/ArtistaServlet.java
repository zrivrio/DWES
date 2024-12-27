package org.iesbelen.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.iesbelen.dao.ArtistaDAO;
import org.iesbelen.dao.ArtistaDAOImpl;
import org.iesbelen.dao.CategoriaDAO;
import org.iesbelen.dao.CategoriaDAOImpl;
import org.iesbelen.model.Artista;
import org.iesbelen.model.Categoria;

import java.io.IOException;

@WebServlet(name = "artistaServlet", value = "/proyecto/artistas/*")
public class ArtistaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * HTTP Method: GET
     * Paths:
     * 		/artistas/
     * 		/artistas/editar{id}
     * 		/artistas/crear
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher;

        String pathInfo = request.getPathInfo(); //
        ArtistaDAO artistaDAO = new ArtistaDAOImpl();
        if (pathInfo == null || "/".equals(pathInfo)) {


            //GET
            //	/artistas/
            //	/artistas

            request.setAttribute("listaArtista", artistaDAO.getAll());
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/artistas/artista.jsp");

        } else {
            // GET
            // 		/artistas/edit/{id}
            // 		/artistas/edit/{id}/
            // 		/artistas/crear
            // 		/artistas/crear/

            pathInfo = pathInfo.replaceAll("/$", "");
            String[] pathParts = pathInfo.split("/");


            if (pathParts.length == 2 && "crear".equals(pathParts[1])) {

                // GET
                // /artistas/crear
                dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/artistas/crear-artista.jsp");

            } else if (pathParts.length == 2) {

                // GET
                // /productos/{id}
                try {
                    request.setAttribute("artista",artistaDAO.find(Integer.parseInt(pathParts[1])));
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/artistas/detalle-artista.jsp");

                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/productos/producto.jsp");
                }
            }   else if (pathParts.length == 3 && "editar".equals(pathParts[1]) ) {

                // GET
                // /artistas/editar/{id}
                try {
                    request.setAttribute("artista",artistaDAO.find(Integer.parseInt(pathParts[2])));
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/artistas/editar-artista.jsp");

                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/artistas/artista.jsp");
                }
            } else {
                System.out.println("Opción POST no soportada.");
                dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/artistas/artista.jsp");

            }
        }

        dispatcher.forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String pathInfo = request.getPathInfo();
        pathInfo = pathInfo.replaceAll("/$", "");
        String[] pathParts = pathInfo.split("/");

        RequestDispatcher dispatcher;
        String __method__ = request.getParameter("__method__");

        if (__method__ == null) {
            // Crear uno nuevo
            ArtistaDAO artistaDAO = new ArtistaDAOImpl();

            String nombre = request.getParameter("nombre");
            String descripcion = request.getParameter("descripcion");
            String nacionalidad = request.getParameter("nacionalidad");
            int anioInicio = Integer.parseInt(request.getParameter("anioInicio"));
            Artista nuevoArtista = new Artista();
            nuevoArtista.setNombre(nombre);
            nuevoArtista.setAnioInicio(anioInicio);
            nuevoArtista.setNacionalidad(nacionalidad);
            nuevoArtista.setDescripcion(descripcion);
            artistaDAO.create(nuevoArtista);

        }else if (__method__ != null && "put".equalsIgnoreCase(__method__)) {
            // Actualizar uno existente
            //Dado que los forms de html sólo soportan method GET y POST utilizo parámetro oculto para indicar la operación de actulización PUT.
            doPut(request, response);

        } else if (__method__ != null && "delete".equalsIgnoreCase(__method__)) {
            // Borrar uno existente
            //Dado que los forms de html sólo soportan method GET y POST utilizo parámetro oculto para indicar la operación de actulización DELETE.
            doDelete(request, response);
        } else {
            System.out.println("Opción POST no soportada.");
        }

        //response.sendRedirect("../../../proyecto/artistas");
        response.sendRedirect(request.getContextPath() + "/proyecto/artistas/");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ArtistaDAO artistaDAO = new ArtistaDAOImpl();
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        String nacionalidad = request.getParameter("nacionalidad");
        int anioInicio = Integer.parseInt(request.getParameter("anioInicio"));
        Artista artista = new Artista();

        try {
            artista.setIdArtista(id);
            artista.setNombre(nombre);
            artista.setAnioInicio(anioInicio);
            artista.setNacionalidad(nacionalidad);
            artista.setDescripcion(descripcion);
            artistaDAO.update(artista);

        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
    {
        RequestDispatcher dispatcher;
        ArtistaDAO artistaDAO = new ArtistaDAOImpl();
        String codigo = request.getParameter("codigo");

        try {
            int id = Integer.parseInt(codigo);
            artistaDAO.delete(id);

        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
    }


}
