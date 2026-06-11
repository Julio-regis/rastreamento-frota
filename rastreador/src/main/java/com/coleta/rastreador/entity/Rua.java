package com.coleta.rastreador.entity;

import com.coleta.rastreador.Enum.StatusRua; // Certifique-se de importar o seu Enum
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ruas")
public class Rua {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_rua", nullable = false, length = 150)
    private String nomeRua;

    @Column(nullable = false, length = 100)
    private String bairro;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;

    // Substituído de String para o Enum que você já possui, garantindo que só aceite LIMPA ou INCOMPLETA
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusRua status;

    @Column(name = "data_coleta")
    private LocalDateTime dataColeta;

    @ManyToOne
    @JoinColumn(name = "id_caminhao")
    private Caminhao caminhao;

    @ManyToOne
    @JoinColumn(name = "id_motorista")
    private Motorista motorista;

    // Construtor padrão obrigatório para o JPA
    public Rua() {
    }

    // Getters e Setters
    public Long getId() { return id; }

    public String getNomeRua() { return nomeRua; }
    public void setNomeRua(String nomeRua) { this.nomeRua = nomeRua; }

    public String getBairro() { return bairro; }
    public void setBairro(String bairro) { this.bairro = bairro; }

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }

    // Tipo alterado para StatusRua
    public StatusRua getStatus() { return status; }
    public void setStatus(StatusRua status) { this.status = status; }

    public LocalDateTime getDataColeta() { return dataColeta; }
    public void setDataColeta(LocalDateTime dataColeta) { this.dataColeta = dataColeta; }

    public Caminhao getCaminhao() { return caminhao; }
    public void setCaminhao(Caminhao caminhao) { this.caminhao = caminhao; }

    public Motorista getMotorista() { return motorista; }
    public void setMotorista(Motorista motorista) { this.motorista = motorista; }
}