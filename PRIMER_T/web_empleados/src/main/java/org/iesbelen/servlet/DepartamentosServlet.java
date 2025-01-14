package org.iesbelen.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.iesbelen.dao.DepartamentoDAO;
import org.iesbelen.dao.DepartamentoDAOImpl;
import org.iesbelen.dto.DepartamentoDTO;
import org.iesbelen.model.Departamento;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "departamentosServlet", value = "/empresa/departamentos/*")
public class DepartamentosServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher;

        String pathInfo = request.getPathInfo(); //
        DepartamentoDAO depDao = new DepartamentoDAOImpl();
        List<Departamento> listaDepartamento = depDao.getAll();
        List<DepartamentoDTO> departamentoDTOS = listaDepartamento.stream()
                .map(departamento -> new DepartamentoDTO(departamento, depDao.getnEmpleados(departamento.getCodigo()).orElse(0)))
                .toList();

        if (pathInfo == null || "/".equals(pathInfo)) {


//            Double rangoI = Double.parseDouble(request.getParameter("rangoI"));
//            Double rangoF = Double.parseDouble(request.getParameter("rangoF"));
//            List<DepartamentoDTO> listaRango = departamentoDTOS;
//
//            if (!rangoI.equals(0) && !rangoI.equals(0)) {
//                listaRango.stream().filter(departamentoDTO -> (departamentoDTO.getPresupuestos() - departamentoDTO.getGastos()) > rangoI && (departamentoDTO.getPresupuestos() - departamentoDTO.getGastos()) < rangoF).toList();
//                request.setAttribute("listaDepartamentos", listaRango);
//
//            }else {
                request.setAttribute("listaDepartamentos", departamentoDTOS);
//            }

            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/departamentos/departamentos.jsp");


        } else {

            pathInfo = pathInfo.replaceAll("/$", "");
            String[] pathParts = pathInfo.split("/");


            if (pathParts.length == 2 && "crear".equals(pathParts[1])) {
                dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/departamentos/crear-departamento.jsp");

            }  else {
                System.out.println("Opción POST no soportada.");
                dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/departamentos/departamentos.jsp");

            }
        }

        dispatcher.forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher;
        String __method__ = request.getParameter("__method__");

        if (__method__ == null) {
            DepartamentoDAO departamentoDAO = new DepartamentoDAOImpl();
            Departamento newDepartamento = new Departamento();

            String nombre = request.getParameter("nombre");
            Double gasto = Double.parseDouble(request.getParameter("gasto"));
            Double presupuesto = Double.parseDouble(request.getParameter("presupuesto"));
            newDepartamento.setNombre(nombre);
            departamentoDAO.create(newDepartamento);

        } else {
            System.out.println("Opción POST no soportada.");
        }

        response.sendRedirect(request.getContextPath() + "/empresa/departamentos");
    }

}
