package com.example;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.example.aop.AuthenticationService;
import com.example.aop.DummyAsync;
import com.example.aop.introductions.Visible;
import com.example.contracts.application.services.MessagingService;
import com.example.ioc.ConstructorConValores;
import com.example.ioc.NotificationService;
import com.example.ioc.Rango;
import com.example.ioc.anotaciones.Twit;
import com.example.ioc.contratos.ServicioCadenas;
import com.example.ioc.notificaciones.Sender;
import com.example.nulabilidad.Dummy;

import jakarta.annotation.PreDestroy;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableScheduling
@EnableAsync
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
				if (s != null)
					dummy = new Dummy(s);
//				IO.println(dummy.getCadena().toLowerCase());
				dummy.setCadenaSegura(s);
				if (dummy.getCadenaSegura().isPresent()) {
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

//	@Bean
	CommandLineRunner demosIoC(@Twit Sender send1, @Qualifier("correo") Sender send2, Map<String, Sender> lista,
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

//	@Bean
	CommandLineRunner notificaciones(ServicioCadenas srv, NotificationService notify) {
		return arg -> {
			IO.println("Inicial -------------------");
//			notify.getListado().forEach(IO::println);
			notify.clear();
			IO.println("Sigo -------------------");
			notify.add(srv.get(1));
			notify.add(srv.get(2));
			srv.add("algo");
//			notify.getListado().forEach(IO::println);
			notify.clear();
		};
	}

//	@Bean
	CommandLineRunner configuracionEnXML() {
		return _ -> {
			try (var contexto = new FileSystemXmlApplicationContext("applicationContext.xml")) {
				var notify = contexto.getBean(NotificationService.class);
				System.out.println("configuracionEnXML ===================>");
				var srv = (ServicioCadenas) contexto.getBean("servicioCadenas");
				System.out.println(srv.getClass().getName());
				contexto.getBean(NotificationService.class).getListado().forEach(System.out::println);
				System.out.println("===================>");
				srv.get().forEach(notify::add);
				srv.add("Hola mundo");
				notify.add(srv.get(1));
				srv.modify("modificado");
				System.out.println("===================>");
				notify.getListado().forEach(System.out::println);
				notify.clear();
				System.out.println("<===================");
				((Sender) contexto.getBean("sender")).send("Hola mundo");
			}
		};
	}

//	@EventListener
//	void eventHandler(GenericoEvent ev) {
//		System.err.println("Evento recibido: %s -> %s".formatted(ev.origen(), ev.carga()));
//	}
//	@EventListener
//	void otroEventHandler(GenericoEvent ev) {
//		System.err.println("Otro tratamiento: %s -> %s".formatted(ev.origen(), ev.carga()));
//	}
//	@EventListener
//	void eventRepository(String ev) {
//		System.err.println("Evento del repositorio: %s".formatted(ev));
//	}

//	@Bean
	CommandLineRunner aop(ServicioCadenas srv, NotificationService notify, AuthenticationService auth) {
		return arg -> {
			try {
				IO.println(srv.getClass().getSimpleName());
				IO.println("Inicial -------------------");
//				notify.getListado().forEach(IO::println);
				notify.clear();
				IO.println("Sigo -------------------");
				notify.add(srv.get(1));
				notify.add(srv.get(2));
				try {
					srv.add("algo");
				} catch (Exception e) {
					e.printStackTrace();
				}
				auth.login();
				srv.add("algo");
				notify.getListado().forEach(IO::println);
				notify.clear();

			} catch (Exception e) {
				e.printStackTrace();
			}
			if (srv instanceof Visible v) {
				IO.println("Tiene interfaz Visible");
				IO.println(v.isVisible() ? "Es visible" : "Invisible");
				v.mostrar();
				IO.println(v.isVisible() ? "Es visible" : "Invisible");
				v.ocultar();
				IO.println(v.isVisible() ? "Es visible" : "Invisible");
			} else {
				IO.println("No tiene interfaz Visible");
			}
		};
	}

	@Autowired
	NotificationService notify;

//	@Bean
	CommandLineRunner asincrono(DummyAsync dummy) {
		return arg -> {
			var obj = dummy; // new DummyAsync();
			System.err.println(obj.getClass().getCanonicalName());
//			obj.ejecutarAutoInvocado(1);
//			obj.ejecutarAutoInvocado(2);
			obj.ejecutarTareaSimpleAsync(1);
			obj.ejecutarTareaSimpleAsync(2);
			obj.calcularResultadoAsync(10, 20, 30, 40, 50).thenAccept(result -> notify.add(result));
			obj.calcularResultadoAsync(1, 2, 3).thenAccept(result -> notify.add(result));
			obj.calcularResultadoAsync().thenAccept(result -> notify.add(result));
			System.err.println("Termino de mandar hacer las cosas");
		};
	}

	@Scheduled(timeUnit = TimeUnit.SECONDS, fixedRate = 5, initialDelay = 2)
	void periodico() {
//		System.out.println("Han pasado 5 segundos");
		if (notify.hasMessages()) {
			System.out.println("@Scheduled -------------------------------->");
			notify.getListado().forEach(System.out::println);
			notify.clear();
			System.out.println("<--------------------------------");
		}
	}

	@Autowired
	MessagingService mensajeria;

	@Bean
	CommandLineRunner demosCorreos() {
		return _ -> {
			mensajeria.sendEmailAsync("admin@example.com", "Aplicacion Init", "La aplicacion se ha iniciado");
			mensajeria.sendWelcomeEmailAsync("pgrillo@example.com", "Pepito Grillo");
		};
	}

	@PreDestroy
	void despidete() {
		var body = """
				<!DOCTYPE html>
				<html lang="es">
				<head>
				    <meta charset="UTF-8">
				    <meta name="viewport" content="width=device-width, initial-scale=1.0">
				    <title>Servicio</title>
				</head>
				<body>
				    <h1>%s</h1>
				    <p>%s</p>
				</body>
				</html>
				""".formatted("Aplicacion Close", "La aplicacion se ha cerrado");
		mensajeria.sendMimeEmail("pgrillo@example.com", "Aplicacion Close", body, true);
	}

}
