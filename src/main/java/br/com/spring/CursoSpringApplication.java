package br.com.spring;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;


// pra limpar o projet
// mvn clean
// pra empacotar o projeto
// mvn packege
// pra rodar o programa
// java -jar Curso-Spring-0.0.1-SNAPSHOT.jar



@SpringBootApplication
public class CursoSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CursoSpringApplication.class, args);
	}

}
