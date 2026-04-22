package com.example;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.example.models.Actor;

@SpringBootApplication
public class DemoRestClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoRestClientApplication.class, args);
	}

	@Bean
	CommandLineRunner cliente() {
		return _ -> {
		var jsonClient = RestClient.builder().defaultHeader("accept", "application/json").build();
		System.out.println(
				jsonClient.get()
					.uri("http://localhost:8010/actores/v1/1")
					.retrieve()
					.body(Actor.class)
			);
		};
	}
	
	@Bean
    ActoresProxy proxy(RestClient.Builder builder) {
        // 1. Configuramos el cliente HTTP base
        RestClient restClient = builder.baseUrl("http://localhost:8010").build();
        
        // 2. Creamos el adaptador para RestClient
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        
        // 3. Creamos la factoría del proxy
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        
        // 4. Generamos la implementación de la interfaz
        return factory.createClient(ActoresProxy.class);
    }
	
	@Bean
	CommandLineRunner remoto(ActoresProxy proxy) {
		return _ -> {
			proxy.getAll("largo").forEach(IO::println);
			IO.println(proxy.getOne(1));
//			proxy.getAllShort().forEach(IO::println);
		};
	}

	static record ActorReactive(int actorId, String firstName, String lastName) {
	}

	@Bean
	CommandLineRunner reactivo() {
		return _ -> {
			var jsonClient = RestClient.builder().defaultHeader("accept", "application/json").build();
			System.out.println(
					jsonClient.get()
						.uri("http://localhost:8085/demos/datos/cabeceras")
						.retrieve()
						.body(String.class)
				);
			jsonClient.get()
				.uri("http://localhost:8085/actores/v1")
				.retrieve()
				.body(new ParameterizedTypeReference<List<ActorReactive>>() {})
				.forEach(IO::println);
		};
	}
}
