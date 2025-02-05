package org.iesbelen.tienda_informatica.controlador;

import org.iesbelen.tienda_informatica.dto.ProductoDTO;
import org.iesbelen.tienda_informatica.modelo.Fabricante;
import org.iesbelen.tienda_informatica.modelo.Producto;
import org.iesbelen.tienda_informatica.servicio.FabricanteService;
import org.iesbelen.tienda_informatica.servicio.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
//Para utilizar una base de url, pero se visualiza mejor con toda la ruta en los métodos
@RequestMapping("/productos")
public class ProductosController {

	@Autowired
	private ProductoService productoService;

	@Autowired
	private FabricanteService fabricanteService;

	@GetMapping("")
	public String init(Model model) {

		List<Producto> listAll =  productoService.listAll();
		model.addAttribute("listaProductos", listAll);

		return "productos/productos";

	}

	
	@GetMapping("/{id}")
	public String detalle(Model model, @PathVariable Integer id ) {
		
		ProductoDTO producto = productoService.productoDTO(id);
		model.addAttribute("producto", producto);
		
		return "productos/detalle-productos";
		
	}
	
	@GetMapping("/crear")
	public String crear(Model model) {
		
		Producto producto = new Producto();
		model.addAttribute("producto", producto);
		
		return "productos/crear-productos";
		
	}
	
	@PostMapping("/crear")
	public RedirectView submitCrear(@ModelAttribute("productos") Producto producto) {
		
		productoService.newProducto(producto);

				
		return new RedirectView("/productos") ;
		
	}
	
	@GetMapping("/editar/{id}")
	public String editar(Model model, @PathVariable Integer id) {

		List<Fabricante>listaFabricante = fabricanteService.listAll();
		Producto producto = productoService.one(id);
		model.addAttribute("producto", producto);
		model.addAttribute("listaFabricante", listaFabricante);
		
		return "productos/editar-productos";
		
	}
	
	
	@PostMapping("/editar/{id}")
	public RedirectView submitEditar(@ModelAttribute("producto") Producto producto) {
		
		productoService.replaceProducto(producto);
		
		return new RedirectView("/productos");
	}
	
	@PostMapping("/borrar/{id}")
	public RedirectView submitBorrar(@PathVariable Integer id) {
		
		productoService.deleteProducto(id);
		
		return new RedirectView("/productos");
	}
	
	
}
