package org.iesbelen.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.iesbelen.dao.*;
import org.iesbelen.model.Producto;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "productoServlet", value = "/proyecto/productos/*")
public class ProductoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * HTTP Method: GET
     * Paths:
     * 		/productos/
     * 		/productos/{id}
     * 		/productos/editar{id}
     * 		/productos/crear
     */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher;

        String pathInfo = request.getPathInfo(); //
        ProductoDAO productoDAO = new ProductoDAOImpl();
        CategoriaDAO categoriaDAO = new CategoriaDAOImpl();
        ArtistaDAO artistaDAO = new ArtistaDAOImpl();

        if (pathInfo == null || "/".equals(pathInfo)) {


            //GET
            //	/productos/
            //	/productos


            String idCategoria = request.getParameter("idCategoria");
            String idArtista = request.getParameter("idArtista");

            if ((idCategoria != null && !idCategoria.isEmpty()) || (idArtista != null && !idArtista.isEmpty())) {
                List<Producto> productosFiltrados = productoDAO.getAll().stream().filter(producto -> {
                    boolean coincideCategoria = idCategoria != null && !idCategoria.isEmpty() &&
                            producto.getIdCategoria() == Integer.parseInt(idCategoria);
                    boolean coincideArtista = idArtista != null && !idArtista.isEmpty() &&
                            producto.getIdArtista() == Integer.parseInt(idArtista);
                    return coincideCategoria || coincideArtista;
                }).collect(Collectors.toList());
                request.setAttribute("listaProducto", productosFiltrados);
            } else {
                request.setAttribute("listaProducto", productoDAO.getAll());
            }

            request.setAttribute("listaCategoria", categoriaDAO.getAll());
            request.setAttribute("listaArtista", artistaDAO.getAll());
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/productos/producto.jsp");

        } else {
            // GET
            // 		/productos/{id}
            // 		/productos/{id}/
            // 		/productos/edit/{id}
            // 		/productos/edit/{id}/
            // 		/productos/crear
            // 		/productos/crear/

            pathInfo = pathInfo.replaceAll("/$", "");
            String[] pathParts = pathInfo.split("/");


            if (pathParts.length == 2 && "crear".equals(pathParts[1])) {

                // GET
                // /usuarios/crear
                request.setAttribute("listaCategoria", categoriaDAO.getAll());
                request.setAttribute("listaArtista", artistaDAO.getAll());
                dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/productos/crear-producto.jsp");

            }  else if (pathParts.length == 2) {

                // GET
                // /productos/{id}
                try {
                    request.setAttribute("producto",productoDAO.find(Integer.parseInt(pathParts[1])));
                    request.setAttribute("listaCategoria", categoriaDAO.getAll());
                    request.setAttribute("listaArtista", artistaDAO.getAll());
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/productos/detalle-producto.jsp");

                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/productos/producto.jsp");
                }
            } else if (pathParts.length == 3 && "editar".equals(pathParts[1]) ) {
                // GET
                // /productos/editar/{id}
                try {
                    request.setAttribute("producto",productoDAO.find(Integer.parseInt(pathParts[2])));
                    request.setAttribute("listaCategoria", categoriaDAO.getAll());
                    request.setAttribute("listaArtista", artistaDAO.getAll());
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/productos/editar-producto.jsp");

                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/productos/producto.jsp");
                }
            } else {
                System.out.println("Opción POST no soportada.");
                dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/productos/producto.jsp");

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
            ProductoDAO productoDAO = new ProductoDAOImpl();
            String nombre = request.getParameter("nombre");
            Double precio = Double.parseDouble(request.getParameter("precio"));
            String descripcion = request.getParameter("descripcion");
            int idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
            int idArtista = Integer.parseInt(request.getParameter("idArtista"));
            Producto nuevoProducto = new Producto();
            nuevoProducto.setNombre(nombre);
            nuevoProducto.setPrecio(precio);
            nuevoProducto.setDescripcion(descripcion);
            nuevoProducto.setIdCategoria(idCategoria);

            nuevoProducto.setIdArtista(idArtista);
            productoDAO.create(nuevoProducto);

        } else if (__method__ != null && "put".equalsIgnoreCase(__method__)) {
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

        //response.sendRedirect("../../../proyecto/productos");
        response.sendRedirect(request.getContextPath() + "/proyecto/productos/");
    }


    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ProductoDAO productoDAO = new ProductoDAOImpl();
        String nombre = request.getParameter("nombre");
        Double precio = Double.parseDouble(request.getParameter("precio"));
        String descripcion = request.getParameter("descripcion");
        int idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
        int idArtista = Integer.parseInt(request.getParameter("idArtista"));
        Producto nuevoProducto = new Producto();

        try {
            nuevoProducto.setNombre(nombre);
            nuevoProducto.setPrecio(precio);
            nuevoProducto.setDescripcion(descripcion);
            nuevoProducto.setIdCategoria(idCategoria);
            nuevoProducto.setIdArtista(idArtista);
            productoDAO.update(nuevoProducto);

        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
    {
        RequestDispatcher dispatcher;
        ProductoDAO productoDAO = new ProductoDAOImpl();
        String codigo = request.getParameter("id");

        try {
            int id = Integer.parseInt(codigo);

            productoDAO.delete(id);

        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
    }
}
