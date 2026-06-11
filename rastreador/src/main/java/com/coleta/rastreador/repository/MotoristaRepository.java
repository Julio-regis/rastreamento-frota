package com.coleta.rastreador.repository;

import com.coleta.rastreador.entity.Motorista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MotoristaRepository extends JpaRepository<Motorista, Long> {
}