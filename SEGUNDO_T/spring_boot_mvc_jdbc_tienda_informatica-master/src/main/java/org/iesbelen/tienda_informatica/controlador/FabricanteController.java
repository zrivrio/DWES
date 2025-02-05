package org.iesbelen.tienda_informatica.controlador;

import java.util.List;

import org.iesbelen.tienda_informatica.modelo.Fabricante;
import org.iesbelen.tienda_informatica.servicio.FabricanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
//Para utilizar una base de url, pero se visualiza mejor con toda la ruta en los métodos
@RequestMapping("/fabricantes")
public class FabricanteController {

	@Autowired
	private FabricanteService fabricanteService;

	@GetMapping("")
	public String init(Model model) {

		List<Fabricante> listAllFab =  fabricanteService.listAll();
		model.addAttribute("listaFabricantes", listAllFab);

		return "fabricantes/fabricantes";

	}

	
	@GetMapping("/{id}")
	public String detalle(Model model, @PathVariable Integer id ) {
		
		Fabricante fabricante = fabricanteService.one(id);
		model.addAttribute("fabricante", fabricante);
		
		return "fabricantes/detalle-fabricante";
		
	}
	
	@GetMapping("/crear")
	public String crear(Model model) {
		
		Fabricante fabricante = new Fabricante();
		model.addAttribute("fabricante", fabricante);
		
		return "fabricantes/crear-fabricante";
		
	}
	
	@PostMapping("/crear")
	public RedirectView submitCrear(@ModelAttribute("fabricante") Fabricante fabricante) {
		
		fabricanteService.newFabricante(fabricante);
				
		return new RedirectView("/fabricantes") ;
		
	}
	
	@GetMapping("/editar/{id}")
	public String editar(Model model, @PathVariable Integer id) {
		
		Fabricante fabricante = fabricanteService.one(id);
		model.addAttribute("fabricante", fabricante);
		
		return "fabricantes/editar-fabricante";
		
	}
	
	
	@PostMapping("/editar/{id}")
	public RedirectView submitEditar(@ModelAttribute("fabricante") Fabricante fabricante) {
		
		fabricanteService.replaceFabricante(fabricante);		
		
		return new RedirectView("/fabricantes");
	}
	
	@PostMapping("/borrar/{id}")
	public RedirectView submitBorrar(@PathVariable Integer id) {
		
		fabricanteService.deleteFabricante(id);
		
		return new RedirectView("/fabricantes");
	}
	
	
}
