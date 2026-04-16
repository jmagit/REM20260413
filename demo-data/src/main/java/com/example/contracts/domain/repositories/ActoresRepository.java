package com.example.contracts.domain.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Meta;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;

import com.example.domain.entities.Actor;

public interface ActoresRepository extends JpaRepository<Actor, Integer>, JpaSpecificationExecutor<Actor> {
	List<Actor> findTop5ByFirstNameStartingWithIgnoreCaseOrderByLastNameDesc(String prefijo);
	List<Actor> findTop5ByFirstNameStartingWithIgnoreCase(String prefijo, Sort orderBy);
	
	@Meta(comment = "esta es la de DSL")
	List<Actor> findByActorIdGreaterThanEqual(int primero);
	@Query("from Actor a where a.actorId >= ?1")
	@EntityGraph(attributePaths = {"filmActors.film"})
	List<Actor> findNovedadesJPQL(int primero);
	@NativeQuery("select * from actor a where a.actor_id >= :primero")
	List<Actor> findNovedadesSQL(int primero);

}
