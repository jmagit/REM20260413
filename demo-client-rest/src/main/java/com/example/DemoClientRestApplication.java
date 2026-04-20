package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.example.models.Actor;

@SpringBootApplication
public class DemoClientRestApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoClientRestApplication.class, args);
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

	@Bean
	CommandLineRunner remoto(ActoresProxy proxy) {
		return _ -> {
			proxy.getAll("largo").forEach(IO::println);
			IO.println(proxy.getOne(1));
//			proxy.getAllShort().forEach(IO::println);
		};
	}
}
