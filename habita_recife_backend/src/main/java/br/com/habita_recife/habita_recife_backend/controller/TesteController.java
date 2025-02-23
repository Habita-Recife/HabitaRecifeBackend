package br.com.habita_recife.habita_recife_backend.controller;

import br.com.habita_recife.habita_recife_backend.model.Prefeitura;
import br.com.habita_recife.habita_recife_backend.service.TesteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/testes")
public class TesteController {

    private final TesteService testeService;

    @Autowired
    public TesteController(TesteService testeService) {
        this.testeService = testeService;
    }

    @GetMapping
    public ResponseEntity<List<Prefeitura>> listar() {
        List<Prefeitura> prefeituras = testeService.findAll();
        return ResponseEntity.ok(prefeituras);
    }

    @PostMapping
    public ResponseEntity<Prefeitura> salvarDados(@RequestBody Prefeitura prefeitura) {
        Prefeitura savedPrefeitura = testeService.save(prefeitura);
        return ResponseEntity.ok(savedPrefeitura);
    }
}
