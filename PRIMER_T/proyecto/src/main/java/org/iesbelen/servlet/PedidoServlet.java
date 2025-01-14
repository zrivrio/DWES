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
import org.iesbelen.dao.PedidoDAO;
import org.iesbelen.dao.PedidoDAOImpl;
import org.iesbelen.model.DetallePedido;
import org.iesbelen.model.Pedido;
import org.iesbelen.model.Usuario;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@WebServlet(name = "pedidoServlet", value = "/proyecto/pedidos/*")
public class PedidoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher;

        String pathInfo = request.getPathInfo(); //
        PedidoDAO pedidoDAO = new PedidoDAOImpl();
        DetallePedidoDAO detallePedidoDAO = new DetallePedidoDAOImpl();
        if (pathInfo == null || "/".equals(pathInfo)) {


            //GET
            //	/pedidos/
            //	/pedidos

            request.setAttribute("listaPedido", pedidoDAO.getAll());
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

            }  else if (pathParts.length == 2) {

                // GET
                // /pedidos/{id}
                try {
                    Optional<Pedido> pedio = pedidoDAO.find(Integer.parseInt(pathParts[1]));

                    if (pedio.isPresent()) { // Verifica si el Optional contiene un valor
                        Pedido pedidoEncontrado = pedio.get();

                        List<DetallePedido> detallePedidos = detallePedidoDAO.getAll().stream()
                                .filter(detallePedido -> detallePedido.getIdPedido() == pedidoEncontrado.getIdPedido())
                                .toList(); // Recoge el resultado filtrado en una lista
                        request.setAttribute("listaDestalle", detallePedidos);
                    } else {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Pedido no encontrado");
                    }

                    request.setAttribute("pedido",pedio);
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/pedidos/detalle-pedido.jsp");

                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/pedidos/pedido.jsp");
                }
            } else if (pathParts.length == 3 && "editar".equals(pathParts[1]) ) {


                // GET
                // /pedidos/editar/{id}
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
        pathInfo = pathInfo != null ? pathInfo.replaceAll("/$", "") : "";
        String[] pathParts = pathInfo.split("/");

        String __method__ = request.getParameter("__method__");

        if (__method__ == null) {
            HttpSession session = request.getSession(false); // Recupera la sesión existente, si la hay
            if (session == null || session.getAttribute("usuario-logado") == null) {
                // Si no hay sesión activa o el usuario no está logado
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/usuarios/login.jsp");
                dispatcher.forward(request, response);
                return; // Termina el flujo para evitar el redirect posterior
            }

            Usuario usuarioLogado = (Usuario) session.getAttribute("usuario-logado");
            int idUsuario = usuarioLogado.getIdUsuario(); // Obtén el ID del usuario logado

            try {
                String estadoPedido = "pendiente";
                LocalDate fecha = LocalDate.now();

                Pedido nuevoPedido = new Pedido();
                nuevoPedido.setIdUsuario(idUsuario);
                nuevoPedido.setEstadoPedido(estadoPedido);
                nuevoPedido.setFechaPedido(fecha);

                PedidoDAO pedidoDAO = new PedidoDAOImpl();
                pedidoDAO.create(nuevoPedido);
                if (nuevoPedido.getIdPedido() > 0) {
                    Map<Integer, Integer> carrito = (Map<Integer, Integer>) session.getAttribute("carrito");
                    if (carrito == null || carrito.isEmpty()) {
                        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "El carrito está vacío.");
                        return;
                    }
                    carrito.forEach((producto,cantidad)-> {
                        DetallePedido nuevoDetallePedido = new DetallePedido();
                        nuevoDetallePedido.setIdPedido(nuevoPedido.getIdPedido());
                        nuevoDetallePedido.setIdProducto(producto);
                        nuevoDetallePedido.setCantidad(cantidad);
                        DetallePedidoDAO detallePedidoDAO = new DetallePedidoDAOImpl();
                        detallePedidoDAO.create(nuevoDetallePedido);
                    });
                }
                // Redirige tras crear el pedido exitosamente
                response.sendRedirect(request.getContextPath() + "/proyecto/pedidos/crear/");
                return;
            } catch (IllegalArgumentException | DateTimeParseException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
                return; // Termina el flujo tras enviar el error
            }
        } else if ("put".equalsIgnoreCase(__method__)) {
            // Actualizar uno existente
            doPut(request, response);
        } else if ("delete".equalsIgnoreCase(__method__)) {
            // Borrar uno existente
            doDelete(request, response);
        } else {
            System.out.println("Opción POST no soportada.");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Opción POST no soportada.");
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PedidoDAO pedidoDAO = new PedidoDAOImpl();
        int id = Integer.parseInt(request.getParameter("id"));
        int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
        String estadoPedido = request.getParameter("estado");
        LocalDate fecha = LocalDate.parse(request.getParameter("fecha"));

        Pedido pedido = new Pedido();

        try {
            pedido.setIdPedido(id);
            pedido.setIdUsuario(idUsuario);
            pedido.setEstadoPedido(estadoPedido);
            pedido.setFechaPedido(fecha);
            pedidoDAO.update(pedido);
            response.sendRedirect(request.getContextPath() + "/proyecto/pedidos/");
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
    {
        RequestDispatcher dispatcher;
        PedidoDAO pedidoDAO = new PedidoDAOImpl();
        String codigo = request.getParameter("codigo");

        try {
            int id = Integer.parseInt(codigo);

            pedidoDAO.delete(id);
            response.sendRedirect(request.getContextPath() + "/proyecto/pedidos/");
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
