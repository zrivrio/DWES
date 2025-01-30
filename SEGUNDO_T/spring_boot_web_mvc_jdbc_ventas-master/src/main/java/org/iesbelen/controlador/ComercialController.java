package org.iesbelen.controlador;


import org.iesbelen.dto.PedidoDTO;
import org.iesbelen.modelo.Cliente;
import org.iesbelen.modelo.Comercial;
import org.iesbelen.dto.ComercialDTO;
import org.iesbelen.modelo.Pedido;
import org.iesbelen.service.ClienteService;
import org.iesbelen.service.ComercialService;
import org.iesbelen.service.PedidoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/comercial")
public class ComercialController {

    private final ComercialService comercialService;
    private final PedidoService pedidoService;


    public ComercialController(ComercialService comercialService, PedidoService pedidoService) {
        this.comercialService = comercialService;
        this.pedidoService = pedidoService;
    }

    @GetMapping("")
    public String listar(Model model) {
        List<Comercial> listaComercial = comercialService.listAll();
        model.addAttribute("listaComercial", listaComercial);
        return "comercial/comercial";
    }
    @GetMapping("/{id}")
    public String detalle(Model model, @PathVariable Integer id) {
        ComercialDTO comercial = comercialService.comercialDTO(id);
        List<PedidoDTO> pedidoDTO = pedidoService.listPedidosDTO(id);



        model.addAttribute("comercial", comercial);
        model.addAttribute("pedidosDTO", pedidoDTO);

        return "comercial/detalle-comercial";
    }

    @GetMapping("/crear")
    public String crear(Model model) {

        Comercial comercial = new Comercial();
        model.addAttribute("comercial", comercial);

        return "comercial/crear-comercial";

    }

    @PostMapping("/crear")
    public RedirectView submitCrear(@ModelAttribute("comercial") Comercial comercial) {

        comercialService.newComercial(comercial);

        return new RedirectView("/comercial") ;

    }

    @GetMapping("/editar/{id}")
    public String editar(Model model, @PathVariable Integer id) {

        Comercial comercial = comercialService.one(id);
        model.addAttribute("comercial", comercial);

        return "comercial/editar-comercial";

    }

    @PostMapping("/editar/{id}")
    public RedirectView submitEditar(@ModelAttribute("comercial") Comercial comercial) {

        comercialService.replaceComercial(comercial);

        return new RedirectView("/comercial");
    }

    @PostMapping("/borrar/{id}")
    public RedirectView submitBorrar(@PathVariable Integer id) {

        comercialService.deleteComercial(id);

        return new RedirectView("/comercial");
    }

}
