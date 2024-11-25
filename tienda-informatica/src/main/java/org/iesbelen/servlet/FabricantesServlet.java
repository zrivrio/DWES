package org.iesbelen.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.iesbelen.dao.FabricanteDAO;
import org.iesbelen.dao.FabricanteDAOImpl;
import org.iesbelen.model.Fabricante;
import org.iesbelen.model.FabricanteDTO;

@WebServlet(name = "fabricantesServlet", value = "/tienda/fabricantes/*")
public class FabricantesServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	/**
	 * HTTP Method: GET
	 * Paths: 
	 * 		/fabricantes/
	 * 		/fabricantes/{id}
	 * 		/fabricantes/editar{id}
	 * 		/fabricantes/crear
	 */		
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		RequestDispatcher dispatcher;
				
		String pathInfo = request.getPathInfo(); //
		FabricanteDAO fabDAO = new FabricanteDAOImpl();
		List<Fabricante> listaFabricantes = fabDAO.getAll();
		List<FabricanteDTO> fabricanteDTOS = listaFabricantes.stream()
				.map(fabricante -> new FabricanteDTO(fabricante, fabDAO.getCountProductos(fabricante.getIdFabricante()).orElse(0)))
				.toList();

		if (pathInfo == null || "/".equals(pathInfo)) {

			
			//GET 
			//	/fabricantes/
			//	/fabricantes

			request.setAttribute("listaFabricantes", fabricanteDTOS);
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/fabricantes/fabricantes.jsp");



				List<FabricanteDTO> listaOrdenar = fabricanteDTOS;
				String orden_por = request.getParameter("orden-por");
				String modo_ordenar = request.getParameter("orden-por");

				if (orden_por != null && modo_ordenar != null) {
					if(orden_por.equals("nombre")){
						if (modo_ordenar.equals("asc")) {
							listaOrdenar = fabricanteDTOS.stream().sorted(Comparator.comparing(Fabricante::getNombre)).toList();

						}
						else if (modo_ordenar.equals("desc")) {
							listaOrdenar = fabricanteDTOS.stream().sorted(Comparator.comparing(Fabricante::getNombre).reversed()).toList();

						}
					}
					else if(orden_por.equals("codigo")){
						if (modo_ordenar.equals("asc")) {
							listaOrdenar = fabricanteDTOS.stream().sorted(Comparator.comparing(Fabricante::getIdFabricante)).toList();

						}
						else if (modo_ordenar.equals("desc")) {
							listaOrdenar = fabricanteDTOS.stream().sorted(Comparator.comparing(Fabricante::getIdFabricante).reversed()).toList();

						}
					}
				}


				request.setAttribute("listaFabricantes", listaOrdenar);
				dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/fabricantes/fabricantes.jsp");


		} else {
			// GET
			// 		/fabricantes/{id}
			// 		/fabricantes/{id}/
			// 		/fabricantes/edit/{id}
			// 		/fabricantes/edit/{id}/
			// 		/fabricantes/crear
			// 		/fabricantes/crear/
			
			pathInfo = pathInfo.replaceAll("/$", "");
			String[] pathParts = pathInfo.split("/");


			 if (pathParts.length == 2 && "crear".equals(pathParts[1])) {
				
				// GET
				// /fabricantes/crear
				dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/fabricantes/crear-fabricante.jsp");

			} else if (pathParts.length == 2) {

				// GET
				// /fabricantes/{id}
				try {
					int idFabricante = Integer.parseInt(pathParts[1]);
					Optional<Fabricante> fabricante = fabDAO.find(idFabricante);
					Optional<FabricanteDTO> fabricanteDTO = fabricante.map(fab -> new FabricanteDTO(fab, fabDAO.getCountProductos(idFabricante).orElse(0)));
					request.setAttribute("fabricante",fabricanteDTO);

					dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/fabricantes/detalle-fabricante.jsp");

				} catch (NumberFormatException nfe) {
					nfe.printStackTrace();
					dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/fabricantes/fabricantes.jsp");
				}
			} else if (pathParts.length == 3 && "editar".equals(pathParts[1]) ) {

				
				// GET
				// /fabricantes/editar/{id}
				try {
					request.setAttribute("fabricante",fabDAO.find(Integer.parseInt(pathParts[2])));
					dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/fabricantes/editar-fabricante.jsp");
					        								
				} catch (NumberFormatException nfe) {
					nfe.printStackTrace();
					dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/fabricantes/fabricantes.jsp");
				}
			} else {
				System.out.println("Opción POST no soportada.");
				dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/fabricantes/fabricantes.jsp");
			
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
			// Crear uno nuevo
			FabricanteDAO fabDAO = new FabricanteDAOImpl();
			
			String nombre = request.getParameter("nombre");
			Fabricante nuevoFab = new Fabricante();
			nuevoFab.setNombre(nombre);
			fabDAO.create(nuevoFab);			
			
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

		//response.sendRedirect("../../../tienda/fabricantes");
		response.sendRedirect(request.getContextPath() + "/tienda/fabricantes");
	}
	
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		FabricanteDAO fabDAO = new FabricanteDAOImpl();
		String codigo = request.getParameter("codigo");
		String nombre = request.getParameter("nombre");
		Fabricante fab = new Fabricante();
		
		try {
			int id = Integer.parseInt(codigo);
			fab.setIdFabricante(id);
			fab.setNombre(nombre);
			fabDAO.update(fab);
			
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
		}
		
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
	{
		RequestDispatcher dispatcher;
		FabricanteDAO fabDAO = new FabricanteDAOImpl();
		String codigo = request.getParameter("codigo");
		
		try {
			int id = Integer.parseInt(codigo);
		
			fabDAO.delete(id);
			
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
		}
	}
}
