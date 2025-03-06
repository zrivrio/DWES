package org.iesbelen.videoclub.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesbelen.videoclub.domain.Socio;
import org.iesbelen.videoclub.domain.Tarjeta;
import org.iesbelen.videoclub.service.SocioService;
import org.iesbelen.videoclub.service.TarjetaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/tarjeta")
public class TarjetaController {
    private final TarjetaService tarjetaService;

    public TarjetaController(TarjetaService tarjetaService) {
        this.tarjetaService = tarjetaService;
    }

    @GetMapping({"","/"})
    public List<Tarjeta> all() {
        log.info("Accediendo a todas las socio");
        return this.tarjetaService.all();
    }

    @PostMapping({"","/"})
    public Tarjeta newTarjeta (@RequestBody Tarjeta tarjeta) {
        return this.tarjetaService.save(tarjeta);
    }

    @GetMapping("/{id}")
    public Tarjeta one(@PathVariable("id") Long id) {
        return this.tarjetaService.one(id);
    }

    @PutMapping("/{id}")
    public Tarjeta replaceSocio(@PathVariable("id") Long id, @RequestBody Tarjeta tarjeta ) {
        return this.tarjetaService.replace(id, tarjeta);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteSocio(@PathVariable("id") Long id) {
        this.tarjetaService.delete(id);
    }


}
