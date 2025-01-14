package org.iesbelen.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.iesbelen.dao.ProductoDAO;
import org.iesbelen.dao.ProductoDAOImpl;
import org.iesbelen.model.Producto;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet(name = "carritoServlet", value = "/proyecto/carrito/*")
public class CarritoServlet extends HttpServlet {
        private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        Map<Integer, Integer> carrito = (Map<Integer, Integer>) session.getAttribute("carrito");
        if (carrito == null) {
            carrito = new HashMap<>();
            session.setAttribute("carrito", carrito);
        }

        // Obtener parámetros
        String codigoParam = request.getParameter("codigo");
        String accion = request.getParameter("accion");
        String eliminar = request.getParameter("eliminar");

        if (codigoParam != null) {
            try {
                int codigo = Integer.parseInt(codigoParam);

                if (accion != null) {
                    int currentQuantity = carrito.getOrDefault(codigo, 0);

                    if (accion.equals("aumentar")) {
                        carrito.put(codigo, currentQuantity + 1);  // Aumentar la cantidad
                    } else if (accion.equals("disminuir") && currentQuantity > 1) {
                        carrito.put(codigo, currentQuantity - 1);  // Disminuir la cantidad
                    }
                } else {
                    // Si no hay acción, añadir el producto incrementando la cantidad si ya está en el carrito
                    carrito.put(codigo, carrito.getOrDefault(codigo, 0) + 1);
                }

                // Manejo de eliminación de producto
                if (eliminar != null && eliminar.equals("eliminar")) {
                    carrito.remove(codigo);  // Eliminar el producto
                }

            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        ProductoDAO productoDAO = new ProductoDAOImpl();
        Map<Integer, Integer> cantidad = carrito;

        // Obtener la lista de productos con las cantidades correctas
        List<Producto> listaProductosCarrito = carrito.entrySet().stream()
                .map(entry -> {
                    Producto producto = productoDAO.find(entry.getKey()).orElse(null);
                    if (producto != null && cantidad.containsKey(entry.getKey())) {
                        producto.setCantidad(cantidad.get(entry.getKey()));
                    }
                    return producto;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        // Calcular el total del carrito
        double totalCarrito = listaProductosCarrito.stream()
                .mapToDouble(producto -> producto.getPrecio() * producto.getCantidad())
                .sum();

        // Pasar los datos al JSP
        request.setAttribute("listaProducto", listaProductosCarrito);
        request.setAttribute("totalCarrito", totalCarrito);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/carrito/carrito.jsp");
        dispatcher.forward(request, response);
    }


}

