package com.example.contracts.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.domain.entities.Actor;

public interface ActoresRepository extends JpaRepository<Actor, Integer>, JpaSpecificationExecutor<Actor> {
}
