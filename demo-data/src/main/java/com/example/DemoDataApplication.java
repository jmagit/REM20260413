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
	
	@Bean
	CommandLineRunner consulta(ActoresRepository dao) {
		return arg -> {
			dao.findAll().forEach(IO::println);
		};
	}

}
