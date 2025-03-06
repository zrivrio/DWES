package org.iesbelen.videoclub.service;

import org.iesbelen.videoclub.domain.Socio;
import org.iesbelen.videoclub.domain.Tarjeta;
import org.iesbelen.videoclub.exception.SocioNotFoundException;
import org.iesbelen.videoclub.exception.TarjetaNotFoundException;
import org.iesbelen.videoclub.repository.SocioRepository;
import org.iesbelen.videoclub.repository.TarjetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarjetaService {

    @Autowired
    private TarjetaRepository tarjetaRepository;


    public List<Tarjeta> all() {
        return this.tarjetaRepository.findAll();
    }

    public Tarjeta save(Tarjeta tarjeta) {
        return this.tarjetaRepository.save(tarjeta);
    }

    public Tarjeta one(Long id) {
        return this.tarjetaRepository.findById(id)
                .orElseThrow(() -> new TarjetaNotFoundException(id));
    }

    public Tarjeta replace(Long id, Tarjeta tarjeta) {

        return this.tarjetaRepository.findById(id).map( p -> (id.equals(tarjeta.getId())  ?
                                                            this.tarjetaRepository.save(tarjeta) : null))
                .orElseThrow(() -> new TarjetaNotFoundException(id));

    }

    public void delete(Long id) {
        this.tarjetaRepository.findById(id).map(p -> {this.tarjetaRepository.delete(p);
                                                        return p;})
                .orElseThrow(() -> new TarjetaNotFoundException(id));
    }

}
