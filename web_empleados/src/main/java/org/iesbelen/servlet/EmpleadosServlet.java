package org.iesbelen.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.iesbelen.dao.DepartamentoDAO;
import org.iesbelen.dao.DepartamentoDAOImpl;
import org.iesbelen.dao.EmpleadoDAO;
import org.iesbelen.dao.EmpleadoDAOImpl;
import org.iesbelen.dto.DepartamentoDTO;
import org.iesbelen.model.Departamento;
import org.iesbelen.model.Empleado;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "empleadosServlet", value = "/empresa/empleados/*")
public class EmpleadosServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher;

        String pathInfo = request.getPathInfo();

        EmpleadoDAO empleadoDAO = new EmpleadoDAOImpl();

        if (pathInfo == null || "/".equals(pathInfo)) {
            request.setAttribute("listaempleados", empleadoDAO.getAll());
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/empleados/empleados.jsp");

        } else {
            pathInfo = pathInfo.replaceAll("/$", "");
            String[] pathParts = pathInfo.split("/");

            if (pathParts.length == 3 && "editar".equals(pathParts[1]) )  {

                try {
                    request.setAttribute("empleado",empleadoDAO.find(Integer.parseInt(pathParts[2])));
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/empleados/editar-empleado.jsp");

                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/empleados/empleados.jsp");
                }

            }  else {
                System.out.println("Opción POST no soportada.");
                dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/empleados/empleados.jsp");

            }
        }

        dispatcher.forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher;
        String __method__ = request.getParameter("__method__");

        if (__method__ != null && "put".equalsIgnoreCase(__method__)) {
            doPut(request, response);
        } else {
            System.out.println("Opción POST no soportada.");
        }

        response.sendRedirect(request.getContextPath() + "/empresa/empleados");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EmpleadoDAO empleadoDAO = new EmpleadoDAOImpl();
        Empleado empleado = new Empleado();

        String nombre = request.getParameter("nombre");
        String nif = request.getParameter("nif");
        String apellido1 = request.getParameter("apellido1");
        String apellido2 = request.getParameter("apellido2");
        int codigoDep = Integer.parseInt(request.getParameter("codigoDepartamento"));

        try {
            empleado.setNombre(nombre);
            empleado.setNif(nif);
            empleado.setApellido1(apellido1);
            empleado.setApellido2(apellido2);
            empleado.setCodigoDepartamento(codigoDep);
            empleadoDAO.update(empleado);

        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }

    }

}
