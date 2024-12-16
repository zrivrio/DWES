package org.iesbelen.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.iesbelen.dao.DetallePedidoDAO;
import org.iesbelen.dao.DetallePedidoDAOImpl;
import org.iesbelen.model.DetallePedido;
import org.iesbelen.model.Usuario;
import org.iesbelen.utilities.util;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.List;

@WebServlet(name = "detallePedidoServlet", value = "/proyecto/detallesPedidos/*")
public class DetallePedidoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * HTTP Method: GET
     * Paths:
     * 		/detallesPedidos/
     * 		/detallesPedidos/{id}
     * 		/detallesPedidos/editar{id}
     * 		/detallesPedidos/crear
     */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher;

        String pathInfo = request.getPathInfo(); //
        DetallePedidoDAO detallePedidoDAO = new DetallePedidoDAOImpl();
        List<DetallePedido> listaDetallesPedidos = detallePedidoDAO.getAll();
        if (pathInfo == null || "/".equals(pathInfo)) {


            //GET
            //	/detallesPedidos/
            //	/detallesPedidos

            request.setAttribute("listaDetallesPedidos", detallePedidoDAO.getAll());
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/detallesPedidos/detallePedido.jsp");

        } else {
            // GET
            // 		/detallesPedidos/{id}
            // 		/detallesPedidos/{id}/
            // 		/detallesPedidos/edit/{id}
            // 		/detallesPedidos/edit/{id}/
            // 		/detallesPedidos/crear
            // 		/detallesPedidos/crear/

            pathInfo = pathInfo.replaceAll("/$", "");
            String[] pathParts = pathInfo.split("/");


            if (pathParts.length == 2 && "crear".equals(pathParts[1])) {

                // GET
                // /usuarios/crear
                dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/detallesPedidos/crear-detallePedido.jsp");

            } else if (pathParts.length == 2) {

                // GET
                // /usuarios/{id}
                try {
                    request.setAttribute("detallePedido",detallePedidoDAO.find(Integer.parseInt(pathParts[1])));
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/detallesPedidos/detalle-detallePedido.jsp");

                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/detallesPedidos/detallePedido.jsp");
                }
            } else if (pathParts.length == 3 && "editar".equals(pathParts[1]) ) {


                // GET
                // /detallesPedidos/editar/{id}
                try {
                    request.setAttribute("detallePedido",detallePedidoDAO.find(Integer.parseInt(pathParts[2])));
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/detallesPedidos/editar-detallePedido.jsp");

                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/detallesPedidos/detallePedido.jsp");
                }
            } else {
                System.out.println("Opción POST no soportada.");
                dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/detallesPedidos/detallePedido.jsp");

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
            DetallePedidoDAO detallePedidoDAO = new DetallePedidoDAOImpl();
            int idProducto = Integer.parseInt(request.getParameter("idProducto"));
            int idPedido = Integer.parseInt(request.getParameter("idPedido"));
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));
            DetallePedido nuevoDetallePedido = new DetallePedido();
            nuevoDetallePedido.setIdProducto(idProducto);
            nuevoDetallePedido.setIdPedido(idPedido);
            nuevoDetallePedido.setCantidad(cantidad);
            detallePedidoDAO.create(nuevoDetallePedido);

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

        //response.sendRedirect("../../../proyecto/detallesPedidos");
        response.sendRedirect(request.getContextPath() + "/proyecto/detallesPedidos/");
    }


    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DetallePedidoDAO detallePedidoDAO = new DetallePedidoDAOImpl();
        int id = Integer.parseInt(request.getParameter("id"));
        int idProducto = Integer.parseInt(request.getParameter("idProducto"));
        int idPedido = Integer.parseInt(request.getParameter("idPedido"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        DetallePedido detallePedido = new DetallePedido();

        try {
            detallePedido.setIdDetalle(id);
            detallePedido.setIdProducto(idProducto);
            detallePedido.setIdPedido(idPedido);
            detallePedido.setCantidad(cantidad);
            detallePedidoDAO.update(detallePedido);

        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
    {
        RequestDispatcher dispatcher;
        DetallePedidoDAO usuarioDAO = new DetallePedidoDAOImpl();
        String codigo = request.getParameter("codigo");

        try {
            int id = Integer.parseInt(codigo);

            usuarioDAO.delete(id);

        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
    }

}
