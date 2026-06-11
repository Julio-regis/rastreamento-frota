package com.coleta.rastreador.repository;

import com.coleta.rastreador.entity.Caminhao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaminhaoRepository extends JpaRepository<Caminhao, Long> {
}