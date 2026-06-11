package com.coleta.rastreador.controller;

import com.coleta.rastreador.entity.Rua;
import com.coleta.rastreador.service.RuaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ruas")
public class RuaController {

    private final RuaService service;

    public RuaController(RuaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Rua> salvar(@RequestBody Rua rua) {
        Rua ruaSalva = service.salvar(rua);
        return ResponseEntity.status(HttpStatus.CREATED).body(ruaSalva);
    }

    @GetMapping
    public ResponseEntity<List<Rua>> listar() {
        List<Rua> lista = service.listar();
        return ResponseEntity.ok(lista);
    }

    /**
     * ADICIONADO: Recebe a atualização do mapa quando o caminhão passa perto da rua.
     * Atualiza o status para LIMPA e salva a data/hora da coleta.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Rua> atualizar(@PathVariable Long id, @RequestBody Rua rua) {
        try {
            Rua ruaAtualizada = service.atualizar(id, rua);
            return ResponseEntity.ok(ruaAtualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}