package com.example;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import com.example.models.Actor;
import com.example.models.ActorShort;

@HttpExchange(url = "/actores/v1", accept = "application/json", contentType = "application/json" )
public interface ActoresProxy {
//	@GetExchange("/?modo=largo")
//	List<Actor> getAll();
	@GetExchange
	List<Actor> getAll(@RequestParam String modo);
	@GetExchange
	List<ActorShort> getAllShort();
	@GetExchange("/{id}")
	Actor getOne(@PathVariable int id);
}
