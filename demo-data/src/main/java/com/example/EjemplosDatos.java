package com.example;

import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.contracts.domain.repositories.ActoresRepository;
import com.example.contracts.domain.repositories.PeliculasRepository;
import com.example.domain.entities.Actor;
import com.example.domain.entities.models.ActorDTO;
import com.example.domain.entities.models.ActorShort;

import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.SerializationFeature;

@Component
public class EjemplosDatos {
	public void run() {
		System.out.println("Hola mundo");
//		relaciones();
	}

	@Autowired
	ActoresRepository daoActors;

	@Transactional(rollbackFor = { Exception.class })
	public void actores() {
		System.out.println(">>> Create");
		var id = daoActors.save(new Actor("Pepito", "Grillo")).getActorId();
		daoActors.findAll().forEach(System.out::println);
		var item = daoActors.findById(id);
		if (item.isEmpty()) {
			System.err.println("No encontrado");
		} else {
			var actor = item.get();
			System.out.println("Leido: " + actor);
			actor.setFirstName(actor.getFirstName().toUpperCase());
			daoActors.save(actor);
		}
		System.out.println(">>> Update");
		daoActors.findAll().forEach(System.out::println);
		daoActors.deleteById(id);
		System.out.println(">>> Delete");
		daoActors.findAll().forEach(System.out::println);

	}

	public void consultas() {
		daoActors.findTop5ByFirstNameStartingWithIgnoreCaseOrderByLastNameDesc("p").forEach(System.out::println);
		daoActors.findTop5ByFirstNameStartingWithIgnoreCase("p", Sort.by("firstName").descending())
				.forEach(System.out::println);
		daoActors.findByActorIdGreaterThanEqual(195).forEach(System.out::println);
		daoActors.findNovedadesJPQL(195).forEach(System.out::println);
		daoActors.findNovedadesSQL(195).forEach(System.out::println);
		daoActors.findAll((root, query, builder) -> builder.greaterThanOrEqualTo(root.get("id"), 195))
				.forEach(System.out::println);
//		daoActors.findAll((root, query, builder) -> builder.lessThanOrEqualTo(root.get("id"), 5)).forEach(System.out::println);
	}

//	
//	@Transactional
	public void relaciones() {
		daoActors.findNovedadesJPQL(198).forEach(item -> {
			System.out.println(item);
			item.getFilmActors().forEach(
					p -> System.out.println("\t%d - %s".formatted(p.getFilm().getFilmId(), p.getFilm().getTitle())));
		});
	}

//	@Autowired
//	CategoriesRepository daoCategories;
//	
//	
	public void transaccionMala() throws Exception {
		((EjemplosDatos) AopContext.currentProxy()).transaccion();
	}

	@Transactional(rollbackFor = { Exception.class })
	public void transaccion() throws Exception {
		daoActors.save(new Actor("Pepito", "Grillo"));
		daoActors.save(new Actor("Carmelo", "Coton"));
//		daoActors.deleteById(1);
//		daoCategories.save(new Category(0, "Serie B"));
//		if(true)
//			throw new Exception("Error forzado para hacer rollback");
//		daoActors.deleteById(1);;
	}

//	
	public void validaciones() {
		try {
			var actor = new Actor("PP", "12345678z");
			if (actor.isInvalid()) {
				System.out.println(">>> Invalid");
				System.err.println(actor.getErrorsMessage());
			} else {
//				daoActors.save(actor);
				System.out.println(">>> Guardado");
			}
		} catch (Exception e) {
			System.err.println("%s: %s".formatted(e.getClass().getSimpleName(), e.getMessage()));
		}

	}

	@Autowired
	PeliculasRepository daoPelis;

	record Pelis(int id, String title) {
	}

	record MiniActor(int actorId, String firstName) {
	}

	public void proyecciones() {
		try {
//			daoActors.findByActorIdGreaterThanEqual(195).forEach(System.out::println);
//			daoActors.findByActorIdGreaterThanEqual(195).forEach(item -> System.out.println(ActorDTO.from(item)));
//			daoActors.readByActorIdGreaterThanEqual(200).forEach(System.out::println);
//			daoActors.queryByActorIdGreaterThanEqual(200).forEach(System.out::println);
//			daoActors.queryByActorIdGreaterThanEqual(200).forEach(item -> System.out.println(item.getId() + " " + item.getNombre()));
			daoActors.findByActorIdGreaterThanEqual(200, ActorDTO.class).forEach(System.out::println);
			daoActors.findByActorIdGreaterThanEqual(200, ActorShort.class)
					.forEach(item -> System.out.println(item.getId() + " " + item.getNombre()));
			daoActors.findByActorIdGreaterThanEqual(200, MiniActor.class).forEach(System.out::println);
//			daoPelis.findAllBy(Pelis.class).forEach(item -> System.out.println(item.id() + " " + item.title()));
		} catch (Exception e) {
			System.err.println("%s: %s".formatted(e.getClass().getSimpleName(), e.getMessage()));
		}
	}

//	
	@Autowired
	ObjectMapper mapper;

	@Transactional
	public void serializacion() {
		try {
//			System.out.println(mapper.writeValueAsString(daoActors.findByActorIdGreaterThanEqual(200, ActorDTO.class)));
//			System.out.println(mapper.writeValueAsString(daoActors.findByActorIdGreaterThanEqual(200, ActorShort.class)));
			System.out.println(mapper.writeValueAsString(daoActors.findNovedadesJPQL(195)));
//			System.out.println(mapper.writeValueAsString(daoActors.findByActorIdGreaterThanEqual(200, ActorShort.class)));
			var objectMapper = new tools.jackson.dataformat.xml.XmlMapper();
//			objectMapper.registerModule(new JavaTimeModule())
//					.configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, false);
			System.out.println(objectMapper.writeValueAsString(daoActors.findNovedadesJPQL(195)));

		} catch (Exception e) {
			System.err.println("%s: %s".formatted(e.getClass().getSimpleName(), e.getMessage()));
		}
	}
}
