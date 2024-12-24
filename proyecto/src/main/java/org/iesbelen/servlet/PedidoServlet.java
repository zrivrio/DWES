package org.iesbelen.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.iesbelen.dao.PedidoDAO;
import org.iesbelen.dao.PedidoDAOImpl;
import org.iesbelen.dao.UsuarioDAO;
import org.iesbelen.dao.UsuarioDAOImpl;
import org.iesbelen.model.Pedido;
import org.iesbelen.model.Usuario;
import org.iesbelen.utilities.util;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.List;

@WebServlet(name = "pedidosServlet", value = "/proyecto/pedidos/*")
public class PedidoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * HTTP Method: GET
     * Paths:
     * 		/pedido/
     * 		/pedido/{id}
     * 		/pedido/editar{id}
     * 		/pedido/crear
     */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher;

        String pathInfo = request.getPathInfo(); //
        PedidoDAO pedidoDAO = new PedidoDAOImpl();
        if (pathInfo == null || "/".equals(pathInfo)) {


            //GET
            //	/pedidos/
            //	/pedidos

            request.setAttribute("listapedido", pedidoDAO.getAll());
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/pedidos/pedido.jsp");

        } else {
            // GET
            // 		/pedidos/{id}
            // 		/pedidos/{id}/
            // 		/pedidos/edit/{id}
            // 		/pedidos/edit/{id}/
            // 		/pedidos/crear
            // 		/pedidos/crear/

            pathInfo = pathInfo.replaceAll("/$", "");
            String[] pathParts = pathInfo.split("/");


            if (pathParts.length == 2 && "crear".equals(pathParts[1])) {

                // GET
                // /pedidos/crear
                dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/pedidos/crear-pedido.jsp");

            }else if (pathParts.length == 2) {

                // GET
                // /pedidos/{id}
                try {
                    request.setAttribute("pedido",pedidoDAO.find(Integer.parseInt(pathParts[1])));
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/pedidos/detalle-pedido.jsp");

                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/pedidos/pedido.jsp");
                }
            } else if (pathParts.length == 3 && "editar".equals(pathParts[1]) ) {


                // GET
                // /fabricantes/editar/{id}
                try {
                    request.setAttribute("pedido",pedidoDAO.find(Integer.parseInt(pathParts[2])));
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/pedidos/editar-pedido.jsp");

                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/pedidos/pedido.jsp");
                }
            } else {
                System.out.println("Opción POST no soportada.");
                dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/pedidos/pedido.jsp");

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
            PedidoDAO pedidoDAO = new PedidoDAOImpl();

            int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
            LocalDate fecha = LocalDate.parse(request.getParameter("fecha"));
            String estadoPedido = request.getParameter("estadoPedido");
            Pedido nuevoPedido = new Pedido();
            nuevoPedido.setIdUsuario(idUsuario);
            nuevoPedido.setFechaPedido(fecha);
            nuevoPedido.setEstadoPedido(estadoPedido);
            pedidoDAO.create(nuevoPedido);

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

        //response.sendRedirect("../../../proyecto/usuarios");
        response.sendRedirect(request.getContextPath() + "/proyecto/usuarios/");
    }


    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PedidoDAO pedidoDAO = new PedidoDAOImpl();
        int id = Integer.parseInt(request.getParameter("id"));
        int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
        LocalDate fecha = LocalDate.parse(request.getParameter("fecha"));
        String estadoPedido = request.getParameter("estadoPedido");
        Pedido pedido = new Pedido();

        try {
            pedido.setIdUsuario(idUsuario);
            pedido.setFechaPedido(fecha);
            pedido.setEstadoPedido(estadoPedido);
            pedidoDAO.update(pedido);

        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
    {
        RequestDispatcher dispatcher;
        UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
        String codigo = request.getParameter("codigo");

        try {
            int id = Integer.parseInt(codigo);

            usuarioDAO.delete(id);

        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
    }
}
