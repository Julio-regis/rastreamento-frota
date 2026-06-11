package com.coleta.rastreador.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "caminhoes") // Boa prática: define explicitamente o nome da tabela no banco
public class Caminhao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 10) // Placa não pode ser nula e deve ser única
    private String placa;

    @Column(nullable = false) // Modelo não pode ser nulo
    private String modelo;

    private boolean ativo;

    // ADICIONADO: Campos de localização para o mapa Leaflet ler do banco
    private Double latitude;
    private Double longitude;

    // Construtor padrão exigido pelo JPA (Boa prática deixar explícito)
    public Caminhao() {
    }

    // Construtor opcional para facilitar a criação de novos objetos no seu código
    public Caminhao(String placa, String modelo, boolean ativo) {
        this.placa = placa;
        this.modelo = modelo;
        this.ativo = ativo;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    // ADICIONADO: Getters e Setters para Latitude e Longitude
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}