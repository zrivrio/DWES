package org.iesbelen.controller;

import org.iesbelen.domain.Categoria;
import org.iesbelen.service.CategoriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller

@RequestMapping("/categoria")
public class CategoriaController {

	private CategoriaService categoriaService;

	public CategoriaController(CategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}

	@GetMapping("/{id}")
	public String detalle(Model model, @PathVariable Integer id) {

		int conteo = categoriaService.conteoCateoria(id);
		Categoria categoria = categoriaService.one(id);
		double duracion = categoriaService.duracionMedia(id);

		model.addAttribute("conteo", conteo);
		model.addAttribute("duracion", duracion);
		model.addAttribute("categoria", categoria);

		return "categoria/detalle-categoria";
	}

}