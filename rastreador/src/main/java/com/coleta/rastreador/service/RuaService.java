package com.coleta.rastreador.service;

import com.coleta.rastreador.Enum.StatusRua;
import com.coleta.rastreador.entity.Rua;
import com.coleta.rastreador.repository.RuaRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RuaService {

    private final RuaRepository repository;

    // Injeção via construtor (Boa prática padrão do projeto)
    public RuaService(RuaRepository repository) {
        this.repository = repository;
    }

    public Rua salvar(Rua rua) {
        return repository.save(rua);
    }

    public List<Rua> listar() {
        return repository.findAll();
    }

    /**
     * ADICIONADO: Método para atualizar a rua quando o caminhão passar perto dela.
     * Esse método será chamado pelo PUT do seu RuaController.
     */
    public Rua atualizar(Long id, Rua dadosNovos) {
        return repository.findById(id)
                .map(ruaExistente -> {
                    ruaExistente.setNomeRua(dadosNovos.getNomeRua());
                    ruaExistente.setBairro(dadosNovos.getBairro());
                    ruaExistente.setStatus(dadosNovos.getStatus());

                    // Se o status mudou para LIMPA, grava o momento exato da coleta
                    if (dadosNovos.getStatus() == StatusRua.LIMPA) {
                        ruaExistente.setDataColeta(LocalDateTime.now());
                    } else {
                        ruaExistente.setDataColeta(null);
                    }

                    return repository.save(ruaExistente);
                })
                .orElseThrow(() -> new RuntimeException("Rua não encontrada com o ID: " + id));
    }

    /**
     * ADICIONADO: Cronograma automático (Sheduler).
     * Este método roda sozinho todo Domingo à meia-noite (0 0 0 * * SUN).
     * Ele reseta todas as ruas de volta para INCOMPLETA (sujas) para a nova semana.
     */
    @Scheduled(cron = "0 0 0 * * SUN")
    @Transactional
    public void resetarRuasSemanais() {
        System.out.println("🔄 [AGENDADOR] Executando reset semanal: Todas as ruas precisam de coleta novamente!");
        List<Rua> ruas = repository.findAll();
        for (Rua rua : ruas) {
            rua.setStatus(StatusRua.INCOMPLETA);
            rua.setDataColeta(null);
            repository.save(rua);
        }
    }
}