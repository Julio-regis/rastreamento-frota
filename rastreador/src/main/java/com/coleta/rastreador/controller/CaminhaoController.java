package com.coleta.rastreador.controller;

import com.coleta.rastreador.entity.Caminhao;
import com.coleta.rastreador.service.CaminhaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/caminhoes")
public class CaminhaoController {

    private final CaminhaoService service;

    public CaminhaoController(CaminhaoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Caminhao> salvar(@RequestBody Caminhao caminhao) {
        Caminhao caminhaoSalvo = service.salvar(caminhao);
        return ResponseEntity.status(HttpStatus.CREATED).body(caminhaoSalvo);
    }

    @GetMapping
    public ResponseEntity<List<Caminhao>> listar() {
        List<Caminhao> lista = service.listar();
        return ResponseEntity.ok(lista);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Caminhao> atualizar(@PathVariable Long id, @RequestBody Caminhao caminhao) {
        try {
            Caminhao caminhaoAtualizado = service.atualizar(id, caminhao);
            return ResponseEntity.ok(caminhaoAtualizado); // Retorna 200 OK com os dados novos
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // Retorna 404 se o ID não existir
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}