package com.coleta.rastreador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling; // Importa o agendador

@SpringBootApplication
@EnableScheduling // Ativa o agendamento de tarefas no projeto
public class RastreadorApplication {

	public static void main(String[] args) {
		SpringApplication.run(RastreadorApplication.class, args);
	}

}