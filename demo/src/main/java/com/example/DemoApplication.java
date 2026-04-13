package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.nulabilidad.Dummy;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.err.println("Aplicacion arrancada");
	}

//	@Bean
	CommandLineRunner nulos() {
		return arg -> {
			var dummy = new Dummy();
			String s = null;
			try {
				s = "algo";
				if(s != null)
					dummy = new Dummy(s);
//				IO.println(dummy.getCadena().toLowerCase());
				dummy.setCadenaSegura(s);
				if(dummy.getCadenaSegura().isPresent()) {
					IO.println(dummy.getCadenaSegura().get().toLowerCase());
				} else {
					IO.println("cadena vacia");
				}
			} catch (Exception e) {
				System.err.println(e.getClass().getSimpleName() + ": " + e.getMessage());
			}
		};
	}
	@Bean
	CommandLineRunner demosIoC() {
		return arg -> {
		};
	}
}
