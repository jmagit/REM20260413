package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.contracts.domain.repositories.ActoresRepository;

@SpringBootApplication
public class DemoDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoDataApplication.class, args);
	}

	// @Bean
	CommandLineRunner consulta(ActoresRepository dao) {
		return arg -> {
			dao.findAll().forEach(IO::println);
		};
	}

	@Bean
	CommandLineRunner ejemplos(EjemplosDatos demos) {
		return arg -> {
//			demos.actores();
			demos.consultas();
//			demos.relaciones();
//			try {
//				demos.transaccion();
////				demos.transaccionMala();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
		};
	}

}
