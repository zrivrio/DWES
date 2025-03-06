package org.iesbelen.videoclub.service;

import org.iesbelen.videoclub.domain.Idioma;
import org.iesbelen.videoclub.domain.Socio;
import org.iesbelen.videoclub.exception.IdiomaNotFoundException;
import org.iesbelen.videoclub.exception.SocioNotFoundException;
import org.iesbelen.videoclub.repository.IdiomaRepository;
import org.iesbelen.videoclub.repository.SocioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocioService {

    @Autowired
    private  SocioRepository socioRepository;


    public List<Socio> all() {
        return this.socioRepository.findAll();
    }

    public Socio save(Socio socio) {
        return this.socioRepository.save(socio);
    }

    public Socio one(Long id) {
        return this.socioRepository.findById(id)
                .orElseThrow(() -> new SocioNotFoundException(id));
    }

    public Socio replace(Long id, Socio socio) {

        return this.socioRepository.findById(id).map( p -> (id.equals(socio.getId())  ?
                                                            this.socioRepository.save(socio) : null))
                .orElseThrow(() -> new SocioNotFoundException(id));

    }

    public void delete(Long id) {
        this.socioRepository.findById(id).map(p -> {this.socioRepository.delete(p);
                                                        return p;})
                .orElseThrow(() -> new SocioNotFoundException(id));
    }

}
