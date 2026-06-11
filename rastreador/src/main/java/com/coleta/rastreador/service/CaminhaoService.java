package com.coleta.rastreador.service;

import com.coleta.rastreador.entity.Caminhao;
import com.coleta.rastreador.repository.CaminhaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CaminhaoService {

    private final CaminhaoRepository repository;

    public CaminhaoService(CaminhaoRepository repository) {
        this.repository = repository;
    }

    public Caminhao salvar(Caminhao caminhao) {
        return repository.save(caminhao);
    }

    public List<Caminhao> listar() {
        return repository.findAll();
    }

    public Optional<Caminhao> buscarPorId(Long id) {
        return repository.findById(id);
    }

    // ADICIONE ESTE MÉTODO (Ele vai sumir com o erro da linha 37 do Controller)
    public Caminhao atualizar(Long id, Caminhao caminhaoAtualizado) {
        return repository.findById(id).map(caminhao -> {
            caminhao.setPlaca(caminhaoAtualizado.getPlaca());
            caminhao.setModelo(caminhaoAtualizado.getModelo());
            caminhao.setAtivo(caminhaoAtualizado.isAtivo());
            return repository.save(caminhao);
        }).orElseThrow(() -> new RuntimeException("Caminhão com ID " + id + " não encontrado."));
    }

    public void deletar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Caminhão com ID " + id + " não encontrado.");
        }
    }
}