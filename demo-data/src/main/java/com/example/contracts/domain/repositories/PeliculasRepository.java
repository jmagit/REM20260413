package com.example.contracts.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.entities.Film;

public interface PeliculasRepository extends JpaRepository<Film, Integer> {

}
