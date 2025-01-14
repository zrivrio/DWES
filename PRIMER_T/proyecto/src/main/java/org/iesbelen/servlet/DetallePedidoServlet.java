package org.iesbelen.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.iesbelen.dao.DetallePedidoDAO;
import org.iesbelen.dao.DetallePedidoDAOImpl;
import java.io.IOException;

@WebServlet(name = "detallePedidoServlet", value = "/proyecto/detallesPedidos/*")
public class DetallePedidoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String __method__ = request.getParameter("__method__");

        if (__method__ != null && "delete".equalsIgnoreCase(__method__)) {
            doDelete(request, response);  // Llamar al método doDelete
        } else {
            System.out.println("Opción POST no soportada.");
        }

        // No llamar a sendRedirect aquí si ya se ha ejecutado en doDelete
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        DetallePedidoDAO detallePedidoDAO = new DetallePedidoDAOImpl();
        String codigo = request.getParameter("codigo");

        try {
            int id = Integer.parseInt(codigo);  // Obtener el ID del detalle del pedido a eliminar
            detallePedidoDAO.delete(id);  // Llamar al DAO para eliminar el detalle de pedido
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }

        // Redirigir después de la eliminación para evitar problemas de respuesta comprometida
        response.sendRedirect(request.getContextPath() + "/proyecto/pedidos/editar/");
    }
}
