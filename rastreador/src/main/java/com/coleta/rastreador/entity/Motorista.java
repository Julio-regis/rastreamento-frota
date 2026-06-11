package com.coleta.rastreador.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "motoristas") // Define explicitamente o nome da tabela no banco
public class Motorista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150) // Nome não pode ser nulo
    private String nome;

    @Column(nullable = false, unique = true, length = 14) // CPF obrigatório, único e com tamanho controlado
    private String cpf;

    @ManyToOne
    @JoinColumn(name = "id_caminhao") // Nomeia explicitamente a coluna da chave estrangeira no banco
    private Caminhao caminhao;

    // Construtor padrão obrigatório para o JPA
    public Motorista() {
    }

    // Construtor opcional para ajudar a instanciar objetos no seu código
    public Motorista(String nome, String cpf, Caminhao caminhao) {
        this.nome = nome;
        this.cpf = cpf;
        this.caminhao = caminhao;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Caminhao getCaminhao() {
        return caminhao;
    }

    public void setCaminhao(Caminhao caminhao) {
        this.caminhao = caminhao;
    }
}