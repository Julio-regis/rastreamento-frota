package com.coleta.rastreador.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

@RestController
public class LocalizacaoController {

    public record LocalizacaoResponse(double latitude, double longitude) {}

    // Coordenadas iniciais fixas (Centro de Gurinhém)
    private double atualLat = -7.1240;
    private double atualLng = -35.4230;

    @GetMapping("/localizacao")
    public ResponseEntity<LocalizacaoResponse> obterLocalizacao() {
        ThreadLocalRandom random = ThreadLocalRandom.current();

        // Altera a rota aos poucos (passos pequenos entre -0.0004 e +0.0004)
        // Isso simula o caminhão andando pela rua de verdade em vez de dar pulos
        atualLat += (random.nextDouble() * 0.0008) - 0.0004;
        atualLng += (random.nextDouble() * 0.0008) - 0.0004;

        // Mantém o caminhão dentro do perímetro urbano aproximado de Gurinhém
        if (atualLat < -7.135) atualLat = -7.124;
        if (atualLat > -7.115) atualLat = -7.124;
        if (atualLng < -35.435) atualLng = -35.423;
        if (atualLng > -35.415) atualLng = -35.423;

        return ResponseEntity.ok(new LocalizacaoResponse(atualLat, atualLng));
    }
}