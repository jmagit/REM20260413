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
			try {
//			demos.actores();
//			demos.consultas();
//			demos.relaciones();
//				demos.transaccion();
////				demos.transaccionMala();
//				demos.validaciones();
//				demos.proyecciones();
				demos.serializacion();
			} catch (Exception e) {
				e.printStackTrace();
			}
		};
	}

}
