package org.iesbelen.controlador;

import java.util.List;

import org.iesbelen.modelo.Cliente;
import org.iesbelen.service.ClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
//Se puede fijar ruta base de las peticiones de este controlador.
//Los mappings de los métodos tendrían este valor /clientes como
//prefijo.
//Si se descomenta el RequestMapping se debera poner siempre lo que quida y despues seguido lo que pidan las GetMapping
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
	public RedirectView submitCrear(@ModelAttribute("cliente") Cliente cliente) {

		clienteService.newCliente(cliente);

		return new RedirectView("/clientes") ;

	}

	@GetMapping("/editar/{id}")
	public String editar(Model model, @PathVariable Integer id) {

		Cliente cliente = clienteService.one(id);
		model.addAttribute("cliente", cliente);

		return "clientes/editar-cliente";

	}

	@PostMapping("/editar/{id}")
	public RedirectView submitEditar(@ModelAttribute("cliente") Cliente cliente) {

		clienteService.replaceCliente(cliente);

		return new RedirectView("/clientes");
	}

	@PostMapping("/borrar/{id}")
	public RedirectView submitBorrar(@PathVariable Integer id) {

		clienteService.deleteCliente(id);

		return new RedirectView("/clientes");
	}

}