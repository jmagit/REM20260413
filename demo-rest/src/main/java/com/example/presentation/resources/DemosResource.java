package com.example.presentation.resources;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.core.contracts.domain.exceptions.InvalidDataException;
import com.example.core.contracts.domain.exceptions.NotFoundException;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.extern.slf4j.Slf4j;
import tools.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import tools.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@Slf4j
@RestController
@RequestMapping("/demos")
public class DemosResource {

	@GetMapping
	public String saluda() {
		return "Hola mundo";
	}

	@GetMapping(path = "/despide")
	public String despide() {
		return "Adios mundo";
	}

	@GetMapping(path = "/despide/{nombre}")
	public String despide(@PathVariable String nombre) {
		return "Adios " + nombre;
	}

	@GetMapping("/params/{id:\\d+}")
	public String cotilla(@PathVariable String id,
			@RequestParam(required = false, defaultValue = "(anonimo)") String nom,
			@RequestHeader("Accept-Language") String language,
			@CookieValue(name = "JSESSIONID", required = false) String cookie) {
		StringBuilder sb = new StringBuilder();
		sb.append("id: " + id + "\n");
		sb.append("nom: " + nom + "\n");
		sb.append("language: " + language + "\n");
		sb.append("cookie: " + cookie + "\n");
		return sb.toString();
	}

	@GetMapping(path = "/params/{id:\\d+}", produces = { "application/json" })
	public ResponseEntity<String> cotillaJSON(@PathVariable String id,
			@RequestParam(required = false, defaultValue = "(anonimo)") String nom,
			@RequestHeader("Accept-Language") String language,
			@CookieValue(name = "JSESSIONID", required = false) String cookie) {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"id\": \"" + id + "\",\n");
		sb.append("\"nom\": \"" + nom + "\",\n");
		sb.append("\"language\": \"" + language + "\",\n");
		sb.append("\"cookie\": \"" + cookie + "\"\n");
		sb.append("}");
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(sb.toString());
	}

	@GetMapping(path = "/params/{id:\\d+}", produces = { "text/xml" })
	public ResponseEntity<String> cotillaXML(@PathVariable String id,
			@RequestParam(required = false, defaultValue = "(anonimo)") String nom,
			@RequestHeader("Accept-Language") String language,
			@CookieValue(name = "JSESSIONID", required = false) String cookie) {
		StringBuilder sb = new StringBuilder();
		sb.append("<root>\n");
		sb.append("<id>" + id + "</id>\n");
		sb.append("<nom>" + nom + "</nom>\n");
		sb.append("<language>" + language + "</language>\n");
		sb.append("<cookie>" + cookie + "</cookie>\n");
		sb.append("</root>\n");
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_XML).body(sb.toString());
	}

	@JsonRootName("raiz") 
	record Produce(String id, String nom, String language, String cookie) {
	}

	@GetMapping("/produces/{id:\\d+}")
	public Produce produces(@PathVariable String id,
			@RequestParam(required = false, defaultValue = "(anonimo)") String nom,
			@RequestHeader("Accept-Language") String language,
			@CookieValue(name = "JSESSIONID", required = false) String cookie) {
		return new Produce(id, nom, language, cookie);
	}

	@GetMapping("/sin-contenido")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void sinContenido() {
		log.warn("petición sin contenido");
	}

	@GetMapping("/estados/{code}")	
	public @JacksonXmlElementWrapper(localName = "raiz", useWrapping = true) Map<String, Integer> status(@PathVariable int code) {
		switch (code) {
		case 400: 
			throw new InvalidDataException("Datos invalidos");
		case 404: 
			throw new NotFoundException();
		default:
			return Map.of("codigo", code);
		}
	}
}
