package br.fiap.techchallenge.api.producao;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
class ApiProducaoApplication{
	public static void main(String[] args) {
		SpringApplication.run(ApiProducaoApplication.class , args);
		
	}
}
		


