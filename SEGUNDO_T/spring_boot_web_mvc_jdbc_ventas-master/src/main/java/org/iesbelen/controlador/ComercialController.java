package org.iesbelen.controlador;


import jakarta.validation.Valid;
import org.iesbelen.dto.ComercialDTO;
import org.iesbelen.dto.PedidoDTO;
import org.iesbelen.modelo.Comercial;
import org.iesbelen.service.ComercialService;
import org.iesbelen.service.PedidoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String submitCrear(@Valid @ModelAttribute("comercial") Comercial comercial, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("comercial", comercial);
            return "comercial/crear-comercial";
        }
        comercialService.newComercial(comercial);

        return "redirect:/comercial" ;

    }

    @GetMapping("/editar/{id}")
    public String editar(Model model, @PathVariable Integer id) {

        Comercial comercial = comercialService.one(id);
        model.addAttribute("comercial", comercial);

        return "comercial/editar-comercial";

    }

    @PostMapping("/editar/{id}")
    public String submitEditar(@ModelAttribute("comercial") Comercial comercial, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("comercial", comercial);
            return "comercial/editar-comercial";
        }
        comercialService.replaceComercial(comercial);

        return "redirect:/comercial";
    }

    @PostMapping("/borrar/{id}")
    public RedirectView submitBorrar(@PathVariable Integer id) {
        comercialService.deleteComercial(id);
        return new RedirectView("/comercial");
    }

    @GetMapping("/error-runtime")
    public void lanzarRuntimeException() {
        throw new RuntimeException("Error en el controlador.");
    }

    @ExceptionHandler(RuntimeException.class)
    public String manejarRuntimeException(RuntimeException ex, Model model) {
        model.addAttribute("message", ex.getMessage());
        return "error";
    }

}
