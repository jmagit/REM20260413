package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestClient;

@SpringBootApplication
public class DemoClientRestApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoClientRestApplication.class, args);
	}

	record Actor(int id, String nombre, String apellidos) {}
	
	@Override
	public void run(String... args) throws Exception {
		var jsonClient = RestClient.builder().defaultHeader("accept", "application/json").build();
		System.out.println(
				jsonClient.get()
					.uri("http://localhost:8010/actores/v1/1")
					.retrieve()
					.body(Actor.class)
			);
	}

}
