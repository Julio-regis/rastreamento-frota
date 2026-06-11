package com.coleta.rastreador.controller;

import com.coleta.rastreador.entity.Caminhao;
import com.coleta.rastreador.repository.CaminhaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

@RestController
public class CaminhaoPosicaoController {

    @Autowired
    private CaminhaoRepository caminhaoRepository;

    //iPhone conversar com o sistema
    @GetMapping("/api/posicao")
    public ResponseEntity<?> receberGpsDoTraccar(
            @RequestParam("id") Long idCaminhao,
            @RequestParam("lat") Double latitude,
            @RequestParam("lon") Double longitude) {

        System.out.println("====== SINAL GPS REAL RECEBIDO ======");
        System.out.println("ID do Veículo: " + idCaminhao + " | Latitude: " + latitude + " | Longitude: " + longitude);

        Optional<Caminhao> caminhaoOpcional = caminhaoRepository.findById(idCaminhao);

        if (caminhaoOpcional.isPresent()) {
            Caminhao caminhao = caminhaoOpcional.get();
            caminhao.setLatitude(latitude);
            caminhao.setLongitude(longitude);
            caminhaoRepository.save(caminhao);

            return ResponseEntity.ok("Coordenadas atualizadas com sucesso.");
        } else {
            System.out.println("Aviso: Recebido sinal, mas o ID " + idCaminhao + " não existe no banco.");
            return ResponseEntity.notFound().build();
        }
    }
}