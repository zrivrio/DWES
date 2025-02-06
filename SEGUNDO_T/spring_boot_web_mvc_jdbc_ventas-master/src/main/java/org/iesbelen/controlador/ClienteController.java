package org.iesbelen.controlador;

import jakarta.validation.Valid;
import org.iesbelen.excepcion.MiExcepcion;
import org.iesbelen.modelo.Cliente;
import org.iesbelen.service.ClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
//Se puede fijar ruta base de las peticiones de este controlador.
//Los mappings de los métodos tendrían este valor /clientes como
//prefijo.
//Si se comenta el RequestMapping se debera poner siempre lo que quida y despues seguido lo que pidan las GetMapping
@RequestMapping("/clientes")
public class ClienteController {
	
	private ClienteService clienteService;
	
	//Se utiliza inyección automática por constructor del framework Spring.
	//Por tanto, se puede omitir la anotación Autowired
	//@Autowired
	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@GetMapping("")
	public String listar(Model model) {

		List<Cliente> listAll =  clienteService.listAll();
		model.addAttribute("listaClientes", listAll);

		return "clientes/clientes";

	}

	@GetMapping("/{id}")
	public String detalle(Model model, @PathVariable Integer id) {
		Cliente cliente = clienteService.one(id);
		model.addAttribute("cliente", cliente);

		return "clientes/detalle-cliente";
	}

	@GetMapping("/crear")
	public String crear(Model model) {
		Cliente cliente = new Cliente();
		model.addAttribute("cliente", cliente);

		return "clientes/crear-cliente";

	}

	@PostMapping("/crear")
	public String submitCrear(@Valid @ModelAttribute("cliente") Cliente cliente, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("cliente", cliente);
			return "clientes/crear-cliente";
		}
		clienteService.newCliente(cliente);
		return "redirect:/clientes";
	}


	@GetMapping("/editar/{id}")
	public String editar(Model model, @PathVariable Integer id) {

		Cliente cliente = clienteService.one(id);
		model.addAttribute("cliente", cliente);

		return "clientes/editar-cliente";

	}

	@PostMapping("/editar/{id}")
	public String submitEditar(@Valid @ModelAttribute("cliente") Cliente cliente, BindingResult result, Model model) {

		if (result.hasErrors()) {


			model.addAttribute("cliente", cliente);
			return "clientes/editar-cliente"; // Nombre de la vista del formulario de edición
		}

		clienteService.replaceCliente(cliente);
		return "redirect:/clientes";
	}

	@PostMapping("/borrar/{id}")
	public RedirectView submitBorrar(@PathVariable Integer id) {

		clienteService.deleteCliente(id);

		return new RedirectView("/clientes");
	}


	@GetMapping("/error-runtime")
	public String RuntimeExceptionLanzar(RuntimeException ex, Model model) {
		model.addAttribute("message", ex.getMessage());
		return "error";
	}
	@GetMapping("/error-personalizada")
	public String MiExcepcionLanzar(MiExcepcion ex, Model model) {
		model.addAttribute("message", ex.getMessage());
		return "error-mi-excepcion";
	}

}