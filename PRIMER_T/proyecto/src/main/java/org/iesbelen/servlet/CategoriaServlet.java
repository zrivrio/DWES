package org.iesbelen.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.iesbelen.dao.CategoriaDAO;
import org.iesbelen.dao.CategoriaDAOImpl;
import org.iesbelen.model.Categoria;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "categoriaServlet", value = "/proyecto/categorias/*")
public class CategoriaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * HTTP Method: GET
     * Paths:
     * 		/categorias/
     * 		/categorias/editar{id}
     * 		/categorias/crear
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher;

        String pathInfo = request.getPathInfo(); //
        CategoriaDAO categoriaDAO = new CategoriaDAOImpl();
        if (pathInfo == null || "/".equals(pathInfo)) {


            //GET
            //	/fabricantes/
            //	/fabricantes

            request.setAttribute("listaCategoria", categoriaDAO.getAll());
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/categorias/categoria.jsp");

        } else {
            // GET
            // 		/categorias/edit/{id}
            // 		/categorias/edit/{id}/
            // 		/categorias/crear
            // 		/categorias/crear/

            pathInfo = pathInfo.replaceAll("/$", "");
            String[] pathParts = pathInfo.split("/");


            if (pathParts.length == 2 && "crear".equals(pathParts[1])) {

                // GET
                // /categorias/crear
                dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/categorias/crear-categoria.jsp");

            }  else if (pathParts.length == 3 && "editar".equals(pathParts[1]) ) {

                // GET
                // /categorias/editar/{id}
                try {
                    request.setAttribute("categoria",categoriaDAO.find(Integer.parseInt(pathParts[2])));
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/categorias/editar-categoria.jsp");

                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/categorias/categoria.jsp");
                }
            } else {
                System.out.println("Opción POST no soportada.");
                dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/categorias/categoria.jsp");

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
            CategoriaDAO categoriaDAO = new CategoriaDAOImpl();

            String nombre = request.getParameter("nombre");
            Categoria nuevaCategoria = new Categoria();
            nuevaCategoria.setNombre(nombre);
            categoriaDAO.create(nuevaCategoria);

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

        //response.sendRedirect("../../../proyecto/categorias");
        response.sendRedirect(request.getContextPath() + "/proyecto/categorias/");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CategoriaDAO categoriaDAO = new CategoriaDAOImpl();
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        Categoria categoria = new Categoria();

        try {
            categoria.setIdCategoria(id);
            categoria.setNombre(nombre);
            categoriaDAO.update(categoria);

        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
    {
        RequestDispatcher dispatcher;
        CategoriaDAO categoriaDAO = new CategoriaDAOImpl();
        String codigo = request.getParameter("codigo");

        try {
            int id = Integer.parseInt(codigo);
            categoriaDAO.delete(id);

        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
    }


}
