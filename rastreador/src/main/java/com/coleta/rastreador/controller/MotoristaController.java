package com.coleta.rastreador.controller;

import com.coleta.rastreador.entity.Motorista;
import com.coleta.rastreador.service.MotoristaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/motoristas")
public class MotoristaController { // Corrigido para Maiúsculo!

    private final MotoristaService service;

    // Injeção via construtor (Melhor prática que o @Autowired sozinho)
    public MotoristaController(MotoristaService service) {
        this.service = service;
    }

    @PostMapping
    public Motorista salvar(@RequestBody Motorista m) {
        return service.salvar(m);
    }

    @GetMapping
    public List<Motorista> listar() {
        return service.listar();
    }
}