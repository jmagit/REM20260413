package com.example;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.ioc.NotificationServiceImpl;
import com.example.ioc.Rango;
import com.example.ioc.contratos.ServicioCadenas;
import com.example.ioc.implementaciones.RepositorioCadenasImpl;
import com.example.ioc.implementaciones.ServicioCadenasImpl;
import com.example.ioc.notificaciones.ConstructorConValores;
import com.example.ioc.notificaciones.Sender;
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
	
	@Autowired	
	ServicioCadenas srv;
	@Value("${info.app.description}")
	String description;
	@Autowired	
	Rango rango;
	
	@Bean
	CommandLineRunner demosIoC(@Qualifier("twit") Sender send1, @Qualifier("correo") Sender send2, Map<String, Sender> lista, 
			ConstructorConValores x, ConstructorConValores y) {
		return arg -> {
//			var n = new NotificationServiceImpl();
//			ServicioCadenas srv = new ServicioCadenasImpl(new RepositorioCadenasImpl(n), n);
			IO.println(srv.get(1));
//			send1.send("envio un twitter");
//			send2.send("envio por correo");
			lista.forEach((name, obj) -> {
				obj.send("Envio con " + name);
			});
			IO.println(description);
			IO.println(rango.toString());
		};
	}
}
