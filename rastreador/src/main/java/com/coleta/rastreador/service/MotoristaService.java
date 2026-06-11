package com.coleta.rastreador.service;

import com.coleta.rastreador.entity.Motorista;
import com.coleta.rastreador.repository.MotoristaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotoristaService {

    private final MotoristaRepository repository;

    // Injeção via construtor: mais seguro e alinhado com as boas práticas de mercado
    public MotoristaService(MotoristaRepository repository) {
        this.repository = repository;
    }

    public Motorista salvar(Motorista motorista) {
        return repository.save(motorista);
    }

    public List<Motorista> listar() {
        return repository.findAll();
    }
}