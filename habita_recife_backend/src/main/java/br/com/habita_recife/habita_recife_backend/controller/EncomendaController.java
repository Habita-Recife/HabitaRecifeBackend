package br.com.habita_recife.habita_recife_backend.controller;

import br.com.habita_recife.habita_recife_backend.service.EncomendaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/encomenda")
public class EncomendaController {
    private final EncomendaService encomendaService;

    public EncomendaController(EncomendaService encomendaService) {
        this.encomendaService = encomendaService;
    }
}
